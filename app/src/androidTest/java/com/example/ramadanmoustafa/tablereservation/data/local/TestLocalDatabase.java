package com.example.ramadanmoustafa.tablereservation.data.local;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.ramadanmoustafa.tablereservation.data.entities.Customer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class TestLocalDatabase {
    private CustomerDao mCustomerDAO;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mCustomerDAO = mDb.customerDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void testInsertDeleteLoadFromDB() throws Exception {
        Customer customer = new Customer(1,"fname","lname");
        Single<List<Customer>> savedCustomers;
        Customer[] customers = new Customer[]{customer, new Customer(2,"fname2","lname2")};
        mCustomerDAO.deleteAllCustomers();
        savedCustomers = mCustomerDAO.getCustomers();
        assertThat(savedCustomers.blockingGet(), hasSize(0));
        mCustomerDAO.saveCustomers(customers);
        savedCustomers = mCustomerDAO.getCustomers();
        assertNotNull(savedCustomers);
        assertThat(savedCustomers.blockingGet(), hasSize(2));
    }


}