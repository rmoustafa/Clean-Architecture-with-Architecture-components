package com.example.ramadanmoustafa.tablereservation;

import com.example.ramadanmoustafa.tablereservation.di.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class TableReservationApp extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initTimber();

    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
