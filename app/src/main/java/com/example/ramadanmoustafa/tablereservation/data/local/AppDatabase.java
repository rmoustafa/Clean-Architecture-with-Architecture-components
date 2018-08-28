package com.example.ramadanmoustafa.tablereservation.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.ramadanmoustafa.tablereservation.data.entities.Customer;

/**
 * The Room database that contains the customer table
 */
@Database(entities = {Customer.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CustomerDao customerDao();

}
