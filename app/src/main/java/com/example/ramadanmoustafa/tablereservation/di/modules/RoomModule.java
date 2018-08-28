package com.example.ramadanmoustafa.tablereservation.di.modules;

import android.arch.persistence.room.Room;

import com.example.ramadanmoustafa.tablereservation.TableReservationApp;
import com.example.ramadanmoustafa.tablereservation.data.local.AppDatabase;
import com.example.ramadanmoustafa.tablereservation.data.local.CustomerDao;
import com.example.ramadanmoustafa.tablereservation.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    @ApplicationScope
    @Provides
    public static AppDatabase provideRoomDB(TableReservationApp application)
    {
        return Room.databaseBuilder(application.getApplicationContext(),
                AppDatabase.class, "customersDb.db")
                .build();
    }

    @ApplicationScope
    @Provides
    public static CustomerDao provideCustomerDao(AppDatabase db){
        return db.customerDao();
    }

}
