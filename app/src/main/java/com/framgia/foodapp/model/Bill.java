package com.framgia.foodapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Bill implements Parcelable {
    private int mIdBill;
    private int mTotalBill;
    private String mDateBill;
    private String mStatusBill;

    public Bill(int mIdBill, int mTotalBill, String mDateBill, String mStatusBill) {
        this.mIdBill = mIdBill;
        this.mTotalBill = mTotalBill;
        this.mDateBill = mDateBill;
        this.mStatusBill = mStatusBill;
    }

    public int getmIdBill() {
        return mIdBill;
    }

    public void setmIdBill(int mIdBill) {
        this.mIdBill = mIdBill;
    }

    public int getmTotalBill() {
        return mTotalBill;
    }

    public void setmTotalBill(int mTotalBill) {
        this.mTotalBill = mTotalBill;
    }

    public String getmDateBill() {
        return mDateBill;
    }

    public void setmDateBill(String mDateBill) {
        this.mDateBill = mDateBill;
    }

    public String getmStatusBill() {
        return mStatusBill;
    }

    public void setmStatusBill(String mStatusBill) {
        this.mStatusBill = mStatusBill;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public final class JsonEntity{
        public static final String IDBILL="Id";
        public static final String STATUS="Status";
        public static final String AMOUNT="Amount";
        public static final String CREATED_AT="Crated_at";

    }
}
