package com.example.ramadanmoustafa.tablereservation.data.repository;

import com.example.ramadanmoustafa.tablereservation.data.entities.Customer;
import com.example.ramadanmoustafa.tablereservation.data.local.CustomerDao;
import com.example.ramadanmoustafa.tablereservation.data.remote.ServiceApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRepositoryImpTest {
    private  Single<List<Customer>> customersObservable;
    private CustomerRepositoryImp customerRepository;
    @Mock
    private ServiceApi mockServiceApi;
    @Mock
    private CustomerDao mockCustomerDao;
    @Before
    public void setUp() throws Exception {
        customerRepository = new CustomerRepositoryImp(mockServiceApi, mockCustomerDao);
        List<Customer> customers = Arrays.asList(new Customer(1,"A", "B"),
                new Customer(2,"AB", "BC"));
        customersObservable = Single.just(customers);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());

    }

    @Test
    public void validateGetCustomersFromRemoteApi() {
        Mockito.doReturn(customersObservable).when(mockServiceApi).getCustomerList();
        customerRepository.getCustomers(true);
        Mockito.verify(mockServiceApi).getCustomerList();

    }

    @Test
    public void validateGetCustomersFromDB() {
        Mockito.doReturn(customersObservable).when(mockCustomerDao).getCustomers();
        customerRepository.getCustomers(false);
        Mockito.verify(mockCustomerDao).getCustomers();

    }
}