package com.framgia.foodapp.screen.ListOrder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.framgia.foodapp.screen.Login.FragmentLogin;
import com.framgia.foodapp.screen.OrderDetail.FragmenBillDetail;
import com.framgia.foodapp.utils.Constant;
import com.framgia.foodapp.utils.ItemClickListener;
import com.framgia.foodapp.utils.Navigator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentListBills extends Fragment implements ItemClickListener, View.OnClickListener {
    private static String LINK_ORDERS = "http://" + Constant.LINK + "/runshop/getdataOrders.php";
    private AdapterListBills mAdapterListOrders;
    private List<Bill> mBills;
    private int mPosition;
    private Navigator mNavigator;

    public static FragmentListBills newInstance(String textViewUser) {
        FragmentListBills fragmentListBills = new FragmentListBills();
        Bundle bundle = new Bundle();
        bundle.putString("2", textViewUser);
        fragmentListBills.setArguments(bundle);
        return fragmentListBills;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_order, container, false);
        initData();
        initView(rootView);
        return rootView;
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (getArguments() != null) {
            String user = bundle.getString("2");
            Toast.makeText(getContext(), "Xin Chao " + user, Toast.LENGTH_SHORT).show();
        }
    }

    private void initView(View rootView) {
        rootView.findViewById(R.id.tuttonBackListOrder).setOnClickListener(this);
        mNavigator = new Navigator();
        RecyclerView recyclerViewListOrders = rootView.findViewById(R.id.recyclerViewListOrders);
        mAdapterListOrders = new AdapterListBills(this);
        recyclerViewListOrders.setHasFixedSize(true);
        recyclerViewListOrders.setAdapter(mAdapterListOrders);
        getDataBaseTable(LINK_ORDERS);
    }

    private void getDataBaseTable(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        //GET để lấy xuống
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONArray>() {
                @Override
                //khi doc duoc json
                public void onResponse(JSONArray response) {
                    mBills = new ArrayList<>();
                    mBills.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            mBills.add(new Bill(
                                object.getInt(Bill.JsonEntity.IDBILL),
                                object.getInt(Bill.JsonEntity.AMOUNT),
//trùng với định nghĩa contructor của php $this->ID
                                object.getString(Bill.JsonEntity.CREATED_AT),
                                object.getString(Bill.JsonEntity.STATUS)
                            ));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mAdapterListOrders.updateData(mBills);
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
    public void onItemClicked(int position) {
        mPosition = position;
        moveFragment();
    }

    private void moveFragment() {
        FragmenBillDetail fragmenBillDetail =
            FragmenBillDetail.newInstance(mPosition, (ArrayList<Bill>) mBills);
        mNavigator.addFragment(getActivity(), fragmenBillDetail,
            R.id.frameContainer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tuttonBackListOrder:
                mNavigator.addFragment(getActivity(), new FragmentLogin(),
                    R.id.frameContainer);
                break;
        }
    }
}
