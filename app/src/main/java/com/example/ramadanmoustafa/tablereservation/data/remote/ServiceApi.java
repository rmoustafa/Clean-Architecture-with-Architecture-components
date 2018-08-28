package com.example.ramadanmoustafa.tablereservation.data.remote;


import com.example.ramadanmoustafa.tablereservation.data.entities.Customer;
import com.example.ramadanmoustafa.tablereservation.data.entities.Table;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface ServiceApi {
    //TODO: add different environment service urls below
    public static final String PRODUCTION_URL = "https://s3-eu-west-1.amazonaws.com/quandoo-assessment/";
    public static final String PRE_PRODUCTION_URL = "https://s3-eu-west-1.amazonaws.com/quandoo-assessment/";

    @GET("customer-list.json")
    Single<List<Customer>> getCustomerList();

    @GET("table-map.json")
    Observable<List<Boolean>> getTableMap();


    //below method is missing in the backend, but I added it for future handling
    @PUT("update_table")
    Completable updateTable(@Body Table table);

}
