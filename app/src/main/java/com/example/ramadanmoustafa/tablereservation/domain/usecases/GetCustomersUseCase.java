package com.example.ramadanmoustafa.tablereservation.domain.usecases;

import android.arch.lifecycle.LiveData;

import com.example.ramadanmoustafa.tablereservation.data.entities.Customer;
import com.example.ramadanmoustafa.tablereservation.base.BaseReactiveResponse;
import com.example.ramadanmoustafa.tablereservation.data.repository.CustomerRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * A use case or the interactor class represents a single business use case
 * responsible for getting all customers data
 */
public class GetCustomersUseCase {

    private CustomerRepository mRepository;

    @Inject
    public GetCustomersUseCase(CustomerRepository repository){
        mRepository = repository;
    }

    public LiveData<BaseReactiveResponse<List<Customer>>> getCustomers(boolean force_update){
        return mRepository.getCustomers(force_update);
    }
}
