package com.example.ramadanmoustafa.tablereservation.presentation.tableReservation;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ramadanmoustafa.tablereservation.R;
import com.example.ramadanmoustafa.tablereservation.base.BaseActivity;
import com.example.ramadanmoustafa.tablereservation.data.entities.Table;
import com.example.ramadanmoustafa.tablereservation.base.BaseReactiveResponse;
import com.example.ramadanmoustafa.tablereservation.base.BaseLiveDataObserver;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReservationActivity extends BaseActivity<ReservationViewModel> {

    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private ReservationRecyclerViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_map);
        setActionBarTitle(getString(R.string.table_map_title));
        ButterKnife.bind(this);
        initRecyclerView();
        mProgressBar.setVisibility(View.VISIBLE);

        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ReservationViewModel.class);
        observeTableMap();

    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter = new ReservationRecyclerViewAdapter(item->{
            mViewModel.setHighlitedTable(item);

            showMessage(getString(R.string.confirmation_message), findViewById(R.id.layout_table_map));
            //TODO: Update table data on server
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void observeTableMap() {
        mViewModel.getTableMap().observe(this, new BaseLiveDataObserver<BaseReactiveResponse<List<Table>>>() {
            @Override
            public void onSuccess(@Nullable BaseReactiveResponse<List<Table>> data) {
                //update ui
                mAdapter.updateDataSet(data.getBody());
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(@Nullable String err) {
                mProgressBar.setVisibility(View.GONE);
                showMessage(err, findViewById(R.id.layout_table_map));

            }

            @Override
            public void onProgress(boolean isShown) {
                mProgressBar.setVisibility(View.VISIBLE);


            }
        });
    }

}
