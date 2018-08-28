package com.example.ramadanmoustafa.tablereservation.data.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Customers")
public class Customer {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "customerid")
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "fname")
    @SerializedName("customerFirstName")
    private String customerFirstName;

    @ColumnInfo(name = "lname")
    @SerializedName("customerLastName")
    private String customerLastName;


    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
