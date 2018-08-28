package com.example.ramadanmoustafa.tablereservation.di.modules;

import com.example.ramadanmoustafa.tablereservation.data.repository.CustomerRepository;
import com.example.ramadanmoustafa.tablereservation.data.repository.CustomerRepositoryImp;
import com.example.ramadanmoustafa.tablereservation.data.repository.TableRepositoryImp;
import com.example.ramadanmoustafa.tablereservation.data.repository.TableRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Binds
    abstract public CustomerRepository bindCustomerRepository(CustomerRepositoryImp repository);

    @Binds
    abstract public TableRepository bindTableRepository(TableRepositoryImp repository);
}
