package com.framgia.foodapp.screen.OrderDetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.framgia.foodapp.R;
import com.framgia.foodapp.model.Bill;
import com.framgia.foodapp.model.Item;
import com.framgia.foodapp.screen.ListOrder.FragmentListBills;
import com.framgia.foodapp.screen.ShipInfor.FragmentShipInfor;
import com.framgia.foodapp.utils.Constant;
import com.framgia.foodapp.utils.Navigator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmenBillDetail extends Fragment implements View.OnClickListener {
    private static String LINK_PRODUCTS =
        "http://" + Constant.LINK + "/runshop/getdataProducts.php";
    private static final String BUNDLE_POSITION = "BUNDLE_POSITION";
    private static final String BUNDLE_LIST = "BUNDLE_LIST";
    private List<Bill> mBills;
    private List<Item> mItems;
    private int mPosition;
    private AdapterBillDetail mAdapterBillDetail;
    private Navigator mNavigator;
    private TextView mTextViewIdOrdersDetail;
    private TextView mTextViewDateOrdersDetail;
    private TextView mTextViewStatus;
    private TextView mTextViewThanhTien;
    private TextView mTextViewThongTinVanChuyen;
    private Button mButtonBackDetail;

    public static FragmenBillDetail newInstance(int position, ArrayList<Bill> bills) {
        FragmenBillDetail fragmenBillDetail = new FragmenBillDetail();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_POSITION, position);
        bundle.putParcelableArrayList(BUNDLE_LIST, bills);
        fragmenBillDetail.setArguments(bundle);
        return fragmenBillDetail;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_oder_detail, container, false);
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
        getDataProducts(LINK_PRODUCTS);
        mTextViewIdOrdersDetail = rootView.findViewById(R.id.textViewMaDonNumber);
        mTextViewDateOrdersDetail = rootView.findViewById(R.id.textViewDateOrderDate);
        mTextViewStatus = rootView.findViewById(R.id.textViewStatusBillDetail);
        mTextViewThanhTien = rootView.findViewById(R.id.textViewThanhTienData);
        mNavigator = new Navigator();
        mAdapterBillDetail = new AdapterBillDetail();
        RecyclerView recyclerViewListItemDetail = rootView.findViewById(R.id.recyclerViewListItems);
        mAdapterBillDetail = new AdapterBillDetail();
        recyclerViewListItemDetail.setHasFixedSize(true);
        recyclerViewListItemDetail.setAdapter(mAdapterBillDetail);
        mButtonBackDetail = rootView.findViewById(R.id.tuttonBackDetail);
        mButtonBackDetail.setOnClickListener(this);
//        mButtonBackDetail
//            .setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mNavigator
//                        .addFragment(getActivity(), new FragmentListBills(), R.id.frameContainer);
//                }
//            });
        mTextViewThongTinVanChuyen = rootView.findViewById(R.id.textViewThongTinVanChuyen);
        mTextViewThongTinVanChuyen.setOnClickListener(this);
        mTextViewIdOrdersDetail.setText(String.valueOf(mBills.get(mPosition).getmIdBill()));
        mTextViewDateOrdersDetail.setText(mBills.get(mPosition).getmDateBill());
        mTextViewStatus.setText(mBills.get(mPosition).getmStatusBill());
        mTextViewThanhTien.setText(String.valueOf(mBills.get(mPosition).getmTotalBill()));
        if (mTextViewStatus.getText().equals("0")) {
            mTextViewStatus.setText("Đang giao hàng");
        }
        if (mTextViewStatus.getText().equals("1")) {
            mTextViewStatus.setText("Giao hàng thành công");
        }
        if (mTextViewStatus.getText().equals("2"))
            mTextViewStatus.setText("Đơn hàng đã hủy");
    }

    private void getDataProducts(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        //GET để lấy xuống
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONArray>() {
                @Override
                //khi doc duoc json
                public void onResponse(JSONArray response) {
                    mItems = new ArrayList<>();
                    mItems.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            int Id = object.getInt("Id");
                            String Name = object.getString("Name");
                            int Size = object.getInt("Size");
                            int mCostItem = object.getInt("Price");
                            String Picture = object.getString("Picture");
                            mItems.add(new Item(Id, Name, Size, mCostItem, Picture));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mAdapterBillDetail.updateData(mItems);
                }
            },
            //khi doc json bi loi
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textViewThongTinVanChuyen:
                FragmentShipInfor fragmentShipInfor =
                    FragmentShipInfor.newInstance(mPosition, (ArrayList<Bill>) mBills);
                mNavigator.addFragment(getActivity(), fragmentShipInfor, R.id.frameContainer);
                break;
            case R.id.tuttonBackDetail:
                mNavigator
                    .addFragment(getActivity(), new FragmentListBills(), R.id.frameContainer);
                break;
        }
    }
}
