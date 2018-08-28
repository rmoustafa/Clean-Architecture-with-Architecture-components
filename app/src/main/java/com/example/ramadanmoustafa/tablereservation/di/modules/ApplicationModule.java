package com.example.ramadanmoustafa.tablereservation.di.modules;

import android.app.Application;
import android.content.Context;

import com.example.ramadanmoustafa.tablereservation.di.scopes.ApplicationScope;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ApplicationModule {

    // same as provides but this returns injected parameter
    @Binds
    @ApplicationScope
    abstract Context bindContext(Application application);

}
