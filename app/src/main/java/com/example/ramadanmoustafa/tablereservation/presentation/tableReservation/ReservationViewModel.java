package com.example.ramadanmoustafa.tablereservation.presentation.tableReservation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.ramadanmoustafa.tablereservation.data.entities.Table;
import com.example.ramadanmoustafa.tablereservation.base.BaseReactiveResponse;
import com.example.ramadanmoustafa.tablereservation.domain.usecases.GetTableMapUseCase;

import java.util.List;

import javax.inject.Inject;

public class ReservationViewModel extends ViewModel {

    private LiveData<BaseReactiveResponse<List<Table>>> mTableMapLiveData;
    private GetTableMapUseCase mGetTableMapUseCase;

    @Inject
    public ReservationViewModel(GetTableMapUseCase getTableMapUseCase){
        mGetTableMapUseCase = getTableMapUseCase;
    }

    public LiveData<BaseReactiveResponse<List<Table>>> getTableMap() {
        if(mTableMapLiveData == null)
            mTableMapLiveData = mGetTableMapUseCase.getTableMap();
        return mTableMapLiveData;

    }

    public void setHighlitedTable(Table selectedItem) {

    }

    @Override
    protected void onCleared() {

        super.onCleared();
    }
}
