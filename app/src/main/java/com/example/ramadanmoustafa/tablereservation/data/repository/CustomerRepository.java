package com.example.ramadanmoustafa.tablereservation.data.repository;

import android.arch.lifecycle.LiveData;

import com.example.ramadanmoustafa.tablereservation.base.BaseReactiveResponse;
import com.example.ramadanmoustafa.tablereservation.data.entities.Customer;

import java.util.List;

/**
 * A repository class in Clean architecture pattern is the Single source of truth,
 * it abstracts the diffent data sources(api, cache, db ..)
 */
public interface CustomerRepository {

    LiveData<BaseReactiveResponse<List<Customer>>> getCustomers(boolean force_update);

    //TODO: The other functions should be added here like (updateCustomers, add, delete, getCustomer(id),...)

    /**
     * clean up resources and long running operations here
      */
    void onCleared();

}