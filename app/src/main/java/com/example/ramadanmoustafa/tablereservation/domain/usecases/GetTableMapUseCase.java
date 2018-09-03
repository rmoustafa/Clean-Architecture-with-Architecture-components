package com.example.ramadanmoustafa.tablereservation.domain.usecases;

import android.arch.lifecycle.LiveData;

import com.example.ramadanmoustafa.tablereservation.data.entities.Table;
import com.example.ramadanmoustafa.tablereservation.base.DataResponse;
import com.example.ramadanmoustafa.tablereservation.data.repository.TableRepository;

import java.util.List;

import javax.inject.Inject;

public class GetTableMapUseCase {

    private TableRepository mRepository;

    @Inject
    public GetTableMapUseCase(TableRepository repository){
        mRepository = repository;
    }

    public LiveData<DataResponse<List<Table>>> getTableMap(){
        return mRepository.getTableMap();
    }
}
