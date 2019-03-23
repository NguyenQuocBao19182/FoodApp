package com.framgia.foodapp.model;

public class Item {
    private int mId;
    private String mNameItem;
    private int mSize;
    private int mCostItem;
    private String mImage;

    public Item(int mId, String mNameItem, int mSize, int mCostItem, String mImage) {
        this.mId = mId;
        this.mNameItem = mNameItem;
        this.mSize = mSize;
        this.mCostItem = mCostItem;
        this.mImage = mImage;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmNameItem() {
        return mNameItem;
    }

    public void setmNameItem(String mNameItem) {
        this.mNameItem = mNameItem;
    }

    public int getmSize() {
        return mSize;
    }

    public void setmSize(int mSize) {
        this.mSize = mSize;
    }

    public int getmCostItem() {
        return mCostItem;
    }

    public void setmCostItem(int mCostItem) {
        this.mCostItem = mCostItem;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}
