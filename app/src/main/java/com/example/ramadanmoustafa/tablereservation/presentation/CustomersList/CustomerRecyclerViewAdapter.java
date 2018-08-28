package com.example.ramadanmoustafa.tablereservation.presentation.CustomersList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramadanmoustafa.tablereservation.R;
import com.example.ramadanmoustafa.tablereservation.data.entities.Customer;

import java.util.List;

public class CustomerRecyclerViewAdapter extends RecyclerView.Adapter<CustomerRecyclerViewAdapter.ViewHolder> {

    private List<Customer> mValues;

    public void updateDataSet(List<Customer> customers) {
        mValues = customers;
    }

    public interface OnItemClickListener {
        void onItemClicked(Customer item);

    }
    private OnItemClickListener mListener;

    public CustomerRecyclerViewAdapter(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.customerIdView.setText(mValues.get(position).getId()+"");
        holder.fnameView.setText(mValues.get(position).getCustomerFirstName());
        holder.lnameView.setText(mValues.get(position).getCustomerLastName());

        holder.mView.setOnClickListener(v->{
                if (null != mListener) {
                    mListener.onItemClicked(holder.mItem);
                }

        });
    }

    @Override
    public int getItemCount() {
        return mValues == null ? 0 : mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView customerIdView;
        public final TextView fnameView;
        public final TextView lnameView;
        public Customer mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            customerIdView = view.findViewById(R.id.customer_id);
            fnameView = view.findViewById(R.id.customer_fname);
            lnameView = view.findViewById(R.id.custom_lname);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + fnameView.getText() + "'";
        }
    }
}
