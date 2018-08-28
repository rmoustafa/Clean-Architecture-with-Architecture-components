package com.example.ramadanmoustafa.tablereservation.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.ramadanmoustafa.tablereservation.data.entities.Customer;

import java.util.List;
import io.reactivex.Single;

/**
 * Data Access Object for the customers table.
 */
@Dao
public interface CustomerDao {

    /**
     * Get customer list from the table,
     * this query gets all customers from the table
     *
     * @return
     */
    @Query("SELECT * FROM customers")
    Single<List<Customer>> getCustomers();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCustomers(Customer... customers);

    /**
     * Delete all customers.
     */
    @Query("DELETE FROM Customers")
    void deleteAllCustomers();

}
