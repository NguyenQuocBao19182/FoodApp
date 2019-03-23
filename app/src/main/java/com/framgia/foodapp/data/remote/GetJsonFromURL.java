//package com.framgia.foodapp.data.remote;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import com.framgia.foodapp.model.Bill;
//import com.framgia.foodapp.screen.ListOrder.FragmentListBills;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GetJsonFromURL extends AsyncTask<String, Void, List<Bill>> {
//    private List<Bill> mBills = new ArrayList<>();
//    private static final String GET = "GET";
//
//    public GetJsonFromURL(FragmentListBills fragmentListBills) {
//    }
//
//    @Override
//    protected List<Bill> doInBackground(String... strings) {
//        try {
//            String data = getDataFromUrl(strings[0]);
//            mBills = pareJsonToObject(data);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mBills;
//    }
//
//    private String getDataFromUrl(String url) throws Exception {
//        URL link = new URL(url);
//        HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
//        httpURLConnection.setRequestMethod(GET);
//        httpURLConnection.connect();
//        InputStream inputStream = httpURLConnection.getInputStream();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//        String line = "";
//        line = bufferedReader.readLine();
//        httpURLConnection.disconnect();
//        return line;
//    }
//
//    private List<Bill> pareJsonToObject(String data) {
//
////        JSONObject jsonObj = new JSONObject();
////        JSONArray jsonArray = jsonObj.getJSONArray(data);
////        for (int i = 0; i < jsonArray.length(); i++) {
////            JSONObject jsonObject = jsonArray.getJSONObject(i);
////            Bill bill =
////                    new Bill.Builder().setmIdBill(jsonObject.getString(Bill.JsonEntity.IDBILL))
////                            .setmStatusBill(jsonObject.getString(Bill.JsonEntity.STATUS))
////                            .setmDateBill(jsonObject.getString(Bill.JsonEntity.CREATED_AT))
////                            .setmTotalBill(jsonObject.getString(Bill.JsonEntity.AMOUNT))
////                            .build();
////            bills.add(bill);
////        }
////        return bills;
//        List<Bill> bills = new ArrayList<>();
//
//        try {
//            JSONObject jsonObject = new JSONObject(data);
//            JSONArray jsonArray = jsonObject.getJSONArray(data);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                int ID = jsonObject1.getInt(Bill.JsonEntity.IDBILL);
//                String Status = jsonObject1.getString(Bill.JsonEntity.STATUS);
//                String DateBill = jsonObject1.getString(Bill.JsonEntity.CREATED_AT);
//                int TotalBill = jsonObject1.getInt(Bill.JsonEntity.AMOUNT);
//                Bill bill =
//                        new Bill.Builder().setmIdBill(ID)
//                                .setmStatusBill(Status)
//                                .setmDateBill(DateBill)
//                                .setmTotalBill(TotalBill)
//                                .build();
//                bills.add(bill);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return bills;
//    }
//
//    @Override
//    protected void onPostExecute(List<Bill> bills) {
//        super.onPostExecute(bills);
//    }
//}
//
