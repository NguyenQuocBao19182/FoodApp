package com.framgia.foodapp.screen.OrderDetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.foodapp.R;
import com.framgia.foodapp.model.Item;

import java.util.ArrayList;
import java.util.List;

public class AdapterBillDetail extends RecyclerView.Adapter<AdapterBillDetail.ItemHolder> {
    private List<Item> mItems;

    public AdapterBillDetail() {
        mItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.item_view_item, viewGroup, false);
        return new AdapterBillDetail.ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        itemHolder.bindData(mItems.get(i));
    }

    public void updateData(List<Item> items) {
        if (mItems != null) {
            mItems.clear();
        }
        assert items != null;
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewNameItemDetail;
        private TextView mTextViewNameItemNhaCungCap;
        private TextView mTextViewIdItemDetail;
        private TextView mTextViewPriceDetail;
        private TextView mTextViewSize;
        private ImageView mImageViewItem;

        ItemHolder(View itemView) {
            super(itemView);
            mTextViewNameItemDetail = itemView.findViewById(R.id.textViewNameItemDetail);
            mTextViewNameItemNhaCungCap = itemView.findViewById(R.id.textViewNhaCungCap);
            mTextViewIdItemDetail = itemView.findViewById(R.id.textViewIdItem);
            mTextViewPriceDetail = itemView.findViewById(R.id.textViewPrice);
            mTextViewSize = itemView.findViewById(R.id.textViewItemDetailSize);
            mImageViewItem = itemView.findViewById(R.id.imageViewItem);
        }

        public void bindData(Item item) {
            mTextViewNameItemDetail.setText(item.getmNameItem());
//            mTextViewNameItemNhaCungCap.setText(item.ge());
            mTextViewIdItemDetail.setText(String.valueOf(item.getmId()));
            mTextViewPriceDetail.setText(String.valueOf(item.getmCostItem()));
            mTextViewSize.setText(String.valueOf(item.getmSize()));
            Glide.with(itemView.getContext())
                .load(item.getmImage())
                .apply(new RequestOptions().placeholder(R.drawable.bg_food_login))
                .into(mImageViewItem);
        }
    }
}
