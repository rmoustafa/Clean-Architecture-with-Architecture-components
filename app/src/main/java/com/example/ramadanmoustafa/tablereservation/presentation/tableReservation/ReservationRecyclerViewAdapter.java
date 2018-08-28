package com.example.ramadanmoustafa.tablereservation.presentation.tableReservation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramadanmoustafa.tablereservation.R;
import com.example.ramadanmoustafa.tablereservation.data.entities.Customer;
import com.example.ramadanmoustafa.tablereservation.data.entities.Table;

import java.util.List;

public class ReservationRecyclerViewAdapter extends RecyclerView.Adapter<ReservationRecyclerViewAdapter.ViewHolder> {

    private List<Table> mValues;

    public void updateDataSet(List<Table> customers) {
        mValues = customers;
    }

    public interface OnItemClickListener {
        void onItemClicked(Table item);

    }
    private OnItemClickListener mListener;

    public ReservationRecyclerViewAdapter(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTable = mValues.get(position);
        holder.tableId.setText(mValues.get(position).getTableId()+"");
        if(!holder.mTable.isAvailable())
            holder.mView.setBackground(holder.mView.getContext().getDrawable(R.drawable.borderbusy));
        else
            holder.mView.setBackground(holder.mView.getContext().getDrawable(R.drawable.border));

        holder.mView.setOnClickListener(v->{
                if (null != mListener && holder.mTable.isAvailable()) {
                        holder.mView.setBackground(holder.mView.getContext().getDrawable(R.drawable.border_reserved));
                    mListener.onItemClicked(holder.mTable);
                }

        });
    }

    @Override
    public int getItemCount() {
        return mValues == null ? 0 : mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tableId;
        public com.example.ramadanmoustafa.tablereservation.data.entities.Table mTable;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tableId = view.findViewById(R.id.table_id);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tableId.getText() + "'";
        }
    }
}
