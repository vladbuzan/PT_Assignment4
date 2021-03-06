package com.company.businessLayer;

import java.io.Serializable;
import java.util.Calendar;

public class Order implements Serializable {
    private int orderID;
    private int tableNo;
    private Calendar date;

    public Order(int orderID, int tableNo) {
        this.orderID = orderID;
        this.tableNo = tableNo;
        this.date = Calendar.getInstance();
    }

    public int getTableNo() {
        return tableNo;
    }

    public int getOrderID() {
        return orderID;
    }

    @Override
    public int hashCode(){
        return orderID;
    }

    @Override
    public String toString() {
        return "Table: " + tableNo + " id = " + orderID + "  DAY:" +
                date.get(Calendar.DAY_OF_MONTH) + "." +date.get(Calendar.MONTH) +
                "  HOUR: " + date.get(Calendar.HOUR_OF_DAY) + ":" + date.get(Calendar.MINUTE);
    }

    @Override
    public boolean equals(Object object) {
        if( object instanceof Order) {
            return ((Order) object).orderID == this.orderID;
        }
        return false;
    }

}
