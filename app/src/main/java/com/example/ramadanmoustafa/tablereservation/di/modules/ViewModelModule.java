package com.example.ramadanmoustafa.tablereservation.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.ramadanmoustafa.tablereservation.base.ViewModelFactory;
import com.example.ramadanmoustafa.tablereservation.di.keys.ViewModelKey;
import com.example.ramadanmoustafa.tablereservation.presentation.CustomersList.CustomersViewModel;
import com.example.ramadanmoustafa.tablereservation.presentation.tableReservation.ReservationViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(CustomersViewModel.class)
    abstract ViewModel bindCustomersViewModel(CustomersViewModel customersViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(ReservationViewModel.class)
    abstract ViewModel bindReservationViewModel(ReservationViewModel reservationViewModel);

}
