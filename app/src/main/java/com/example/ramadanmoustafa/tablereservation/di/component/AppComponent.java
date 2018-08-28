package com.example.ramadanmoustafa.tablereservation.di.component;


import com.example.ramadanmoustafa.tablereservation.TableReservationApp;
import com.example.ramadanmoustafa.tablereservation.di.modules.ActivityModule;
import com.example.ramadanmoustafa.tablereservation.di.modules.ApplicationModule;
import com.example.ramadanmoustafa.tablereservation.di.modules.NetworkModule;
import com.example.ramadanmoustafa.tablereservation.di.modules.RepositoryModule;
import com.example.ramadanmoustafa.tablereservation.di.modules.RoomModule;
import com.example.ramadanmoustafa.tablereservation.di.modules.ViewModelModule;
import com.example.ramadanmoustafa.tablereservation.di.scopes.ApplicationScope;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@ApplicationScope
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        NetworkModule.class,
        ActivityModule.class,
        ViewModelModule.class,
        RepositoryModule.class,
        RoomModule.class
})
public interface AppComponent extends AndroidInjector<TableReservationApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<TableReservationApp>{}
}

