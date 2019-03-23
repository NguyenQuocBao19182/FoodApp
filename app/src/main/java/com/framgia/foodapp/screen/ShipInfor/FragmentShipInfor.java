package com.framgia.foodapp.screen.ShipInfor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.foodapp.R;
import com.framgia.foodapp.model.Bill;
import com.framgia.foodapp.screen.ListOrder.FragmentListBills;
import com.framgia.foodapp.screen.OrderDetail.FragmenBillDetail;
import com.framgia.foodapp.utils.Navigator;

import java.util.ArrayList;
import java.util.List;

public class FragmentShipInfor extends Fragment implements View.OnClickListener {
    private static final String BUNDLE_POSITION = "BUNDLE_POSITION";
    private static final String BUNDLE_LIST = "BUNDLE_LIST";
    private List<Bill> mBills;
    private int mPosition;
    private Navigator mNavigator;
    private TextView mTextViewDateStart;
    private TextView mTextViewSuccessOrder;
    private TextView mTextViewChuanBiOrders;
    private TextView mTextViewShipping;
    private TextView mTextViewSuccessGive;
    private ImageView mImageViewPoint;
    private ImageView mImageViewPoint1;
    private ImageView mImageViewPoint2;
    private ImageView mImageViewPoint3;
    private TextView mTextViewBill;

    public static FragmentShipInfor newInstance(int position, ArrayList<Bill> bills) {
        FragmentShipInfor fragmentShipInfor = new FragmentShipInfor();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_POSITION, position);
        bundle.putParcelableArrayList(BUNDLE_LIST, bills);
        fragmentShipInfor.setArguments(bundle);
        return fragmentShipInfor;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_check_donhang, container, false);
        initData();
        initView(rootView);
        return rootView;
    }

    private void initData() {
        mBills = new ArrayList<>();
        Bundle bundle = getArguments();
        if (getArguments() != null) {
            mPosition = bundle.getInt(BUNDLE_POSITION);
            mBills = bundle.getParcelableArrayList(BUNDLE_LIST);
        }
    }

    private void initView(View rootView) {
        mNavigator = new Navigator();
        mTextViewDateStart = rootView.findViewById(R.id.textViewDateStart);
        mTextViewSuccessOrder = rootView.findViewById(R.id.textViewOrderSuccess);
        mTextViewChuanBiOrders = rootView.findViewById(R.id.textViewChuanBiMon);
        mTextViewShipping = rootView.findViewById(R.id.textViewDangGiaoToi);
        mTextViewSuccessGive = rootView.findViewById(R.id.textViewGiaoHangThanhCong);
        mTextViewBill = rootView.findViewById(R.id.textViewGiaTienMat);
        mImageViewPoint = rootView.findViewById(R.id.imageViewPoint);
        mImageViewPoint1 = rootView.findViewById(R.id.imageViewPoint1);
        mImageViewPoint2 = rootView.findViewById(R.id.imageViewPoint2);
        mImageViewPoint3 = rootView.findViewById(R.id.imageViewPoint3);
        mTextViewDateStart.setText(mBills.get(mPosition).getmDateBill());
        rootView.findViewById(R.id.tuttonBackCheckInfor).setOnClickListener(this);
        rootView.findViewById(R.id.tuttonCall).setOnClickListener(this);
        rootView.findViewById(R.id.tuttonContact).setOnClickListener(this);
        rootView.findViewById(R.id.textViewChiTiet).setOnClickListener(this);
        mTextViewBill.setText(String.valueOf(mBills.get(mPosition).getmTotalBill()));
        if (mBills.get(mPosition).getmStatusBill().equals("0")) {
            mTextViewSuccessGive.setText("");
            mImageViewPoint3.setImageResource(R.color.color_white);
        }
        if (mBills.get(mPosition).getmStatusBill()
            .equals("2")) {
            mTextViewChuanBiOrders.setText("Đơn hàng đã hủy");
            mTextViewShipping.setText("");
            mTextViewSuccessGive.setText("");
            mImageViewPoint2.setImageResource(R.color.color_white);
            mImageViewPoint3.setImageResource(R.color.color_white);
        }
    }

    private void intentFaceBook() {
        String url = "https://www.facebook.com/messages/t/2194946407293620";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void intentCall() {
        String posted_by = "111-333-222-4";
        String uri = "tel:" + posted_by.trim();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    private void intentFaceBookChiTiet() {
        String url =
            "https://www.google.com/maps/place/20+Phan+%C4%90%C4%83ng+L%C6%B0u,+Ho%C3%A0+C%C6%B0%E1%BB%9Dng+B%E1%BA%AFc,+H%E1%BA%A3i+Ch%C3%A2u,+%C4%90%C3%A0+N%E1%BA%B5ng,+Vi%E1%BB%87t+Nam/@16.0374843,108.2195748,17z/data=!3m1!4b1!4m5!3m4!1s0x314219e96b3030bb:0x92c30fe166cb1c33!8m2!3d16.037479!4d108.221763";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tuttonBackCheckInfor:
                mNavigator
                    .addFragment(getActivity(), new FragmentListBills(), R.id.frameContainer);
                break;
            case R.id.tuttonCall:
                intentCall();
                break;
            case R.id.tuttonContact:
                intentFaceBook();
                break;
            case R.id.textViewChiTiet:
                intentFaceBookChiTiet();
                break;
        }
    }
}
