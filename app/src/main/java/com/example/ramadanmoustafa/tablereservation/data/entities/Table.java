package com.example.ramadanmoustafa.tablereservation.data.entities;

import com.google.gson.annotations.SerializedName;

public class Table {
    @SerializedName("")
    private boolean isAvailable;
    private int tableId;

    public Table(int id, boolean isAvailable){
        this.isAvailable = isAvailable;
        this.tableId = id;
    }
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Table)) {
            return false;
        }

        Table c = (Table) o;

        // Compare the data members and return accordingly
        return (tableId == c.tableId && isAvailable == c.isAvailable);
    }
}
