package com.example.ramadanmoustafa.tablereservation.presentation.CustomersList;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ramadanmoustafa.tablereservation.R;
import com.example.ramadanmoustafa.tablereservation.base.BaseActivity;
import com.example.ramadanmoustafa.tablereservation.data.entities.Customer;
import com.example.ramadanmoustafa.tablereservation.base.DataResponse;
import com.example.ramadanmoustafa.tablereservation.base.BaseLiveDataObserver;
import com.example.ramadanmoustafa.tablereservation.presentation.tableReservation.ReservationActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomersActivity extends BaseActivity<CustomersViewModel> {

    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private CustomerRecyclerViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
        setActionBarTitle(getString(R.string.customer_title));
        ButterKnife.bind(this);
        initRecyclerView();
        mProgressBar.setVisibility(View.VISIBLE);

        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CustomersViewModel.class);
        observeCusomers();

    }

    /**
     * Initiates the recyclerview & adds a divider between items
     */
    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new CustomerRecyclerViewAdapter(item->{
            mViewModel.setSelectedItem(item);
            openTableReservationActivity();
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void openTableReservationActivity() {
            startActivity(new Intent(this, ReservationActivity.class));
    }

    /**
     * Observes the data data change in the viewmodel and then updates the view accordingly
     */
    private void observeCusomers() {
        mViewModel.getCustomers().observe(this, new BaseLiveDataObserver<DataResponse<List<Customer>>>() {
            @Override
            public void onSuccess(@Nullable DataResponse<List<Customer>> data) {
                //update ui
                mAdapter.updateDataSet(data.getData());
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(@Nullable String err) {
                mProgressBar.setVisibility(View.GONE);
                showMessage(err, findViewById(R.id.layout_customers));

            }

            @Override
            public void onProgress(boolean isShown) {
                mProgressBar.setVisibility(isShown? View.VISIBLE: View.GONE);


            }
        });
    }


}
