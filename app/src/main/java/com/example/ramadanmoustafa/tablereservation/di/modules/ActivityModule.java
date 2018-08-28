package com.example.ramadanmoustafa.tablereservation.di.modules;

import com.example.ramadanmoustafa.tablereservation.presentation.CustomersList.CustomersActivity;
import com.example.ramadanmoustafa.tablereservation.presentation.tableReservation.ReservationActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract public class ActivityModule {

    @ContributesAndroidInjector
    abstract CustomersActivity bindCustomerActivity();

    @ContributesAndroidInjector
    abstract ReservationActivity bindReservationActivity();


}
