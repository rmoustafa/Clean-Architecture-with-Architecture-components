package com.example.ramadanmoustafa.tablereservation.presentation.CustomersList;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.ramadanmoustafa.tablereservation.base.BaseReactiveResponse;
import com.example.ramadanmoustafa.tablereservation.data.entities.Customer;
import com.example.ramadanmoustafa.tablereservation.domain.usecases.GetCustomersUseCase;

import java.util.List;

import javax.inject.Inject;

public class CustomersViewModel extends ViewModel {

    private LiveData<BaseReactiveResponse<List<Customer>>> mCustomersLiveData;
    private GetCustomersUseCase mGetCustomersUseCase;

    @Inject
    public CustomersViewModel(GetCustomersUseCase getCustomersUseCase){
        mGetCustomersUseCase = getCustomersUseCase;
    }

    public LiveData<BaseReactiveResponse<List<Customer>>> getCustomers() {
        if(mCustomersLiveData == null)
            mCustomersLiveData = mGetCustomersUseCase.getCustomers(true);
        return mCustomersLiveData;

    }

    public void setSelectedItem(Customer selectedItem) {

    }

    @Override
    protected void onCleared() {

        super.onCleared();
        //TODO: clean up resources and long running operations here
    }
}
