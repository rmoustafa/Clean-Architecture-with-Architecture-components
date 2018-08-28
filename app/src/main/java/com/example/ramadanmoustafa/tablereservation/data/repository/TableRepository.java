package com.example.ramadanmoustafa.tablereservation.data.repository;

import android.arch.lifecycle.LiveData;

import com.example.ramadanmoustafa.tablereservation.data.entities.Table;
import com.example.ramadanmoustafa.tablereservation.base.BaseReactiveResponse;

import java.util.List;

public interface TableRepository {

    LiveData<BaseReactiveResponse<List<Table>>> getTableMap();

}