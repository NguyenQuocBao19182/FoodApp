package com.framgia.foodapp.screen.ListOrder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.foodapp.R;
import com.framgia.foodapp.model.Bill;
import com.framgia.foodapp.utils.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class AdapterListBills extends RecyclerView.Adapter<AdapterListBills.ItemHolder> {
    private List<Bill> mBills;
    private ItemClickListener mItemClickListener;

    public AdapterListBills(ItemClickListener itemClickListener) {
        mBills = new ArrayList<>();
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.item_bill, viewGroup, false);
        return new ItemHolder(itemView, mItemClickListener);
    }

    public void updateData(List<Bill> bills) {
        if (mBills != null) {
            mBills.clear();
        }
        assert bills != null;
        mBills = bills;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        itemHolder.bindData(mBills.get(i));
    }

    @Override
    public int getItemCount() {
        return mBills != null ? mBills.size() : 0;
    }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewStatusOrders;
        private TextView mTextViewNumberBill;
        private TextView mTextViewDateBill;
        private TextView mTextViewTotal;
        private ImageView mImageViewStatusBill;
        private ItemClickListener mItemClickListener;

        ItemHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            mItemClickListener = itemClickListener;
            mTextViewStatusOrders = itemView.findViewById(R.id.textViewStatusOrder);
            mTextViewNumberBill = itemView.findViewById(R.id.textViewNumberBill);
            mTextViewDateBill = itemView.findViewById(R.id.textViewDateBill);
            mImageViewStatusBill = itemView.findViewById(R.id.imageViewStatusBill);
            mTextViewTotal = itemView.findViewById(R.id.textViewTotal);
            itemView.setOnClickListener(this);
        }

        void bindData(Bill bill) {
            mTextViewStatusOrders.setText(bill.getmStatusBill());
            mTextViewNumberBill.setText(String.valueOf(bill.getmIdBill()));
            mTextViewDateBill.setText(bill.getmDateBill());
            mTextViewTotal.setText(String.valueOf(bill.getmTotalBill()));
            if (mTextViewStatusOrders.getText().equals("0")) {
                mTextViewStatusOrders.setText("Đang giao hàng");
                mImageViewStatusBill.setImageResource(R.drawable.shiping);
            }
            if (mTextViewStatusOrders.getText().equals("1")) {
                mTextViewStatusOrders.setText("Giao hàng thành công");
                mImageViewStatusBill.setImageResource(R.drawable.success);
            }
            if (mTextViewStatusOrders.getText().equals("2")) {
                mTextViewStatusOrders.setText("Đơn hàng đẫ hủy");
                mImageViewStatusBill.setImageResource(R.drawable.cancel);
            }
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClicked(getAdapterPosition());
            }
        }
    }
}
