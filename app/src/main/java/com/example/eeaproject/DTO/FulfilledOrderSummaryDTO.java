package com.example.eeaproject.DTO;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author gdm1
 */
public class FulfilledOrderSummaryDTO implements Serializable
{

    private int id;
    private String customerName;
    private String customerAddress;
    private Calendar orderDate;
    private Calendar dateSent;

    public FulfilledOrderSummaryDTO()
    {
        this(-1, "??", "????", null, null);
    }

    public FulfilledOrderSummaryDTO(int id, String customerName, String customerAddress, Calendar orderDate, Calendar dateSent)
    {
        this.id = id;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.orderDate = orderDate;
        this.dateSent = dateSent;
    }

    public int getId()
    {
        return id;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public String getCustomerAddress()
    {
        return customerAddress;
    }

    public Calendar getOrderDate()
    {
        return orderDate;
    }

    public Calendar getDateSent()
    {
        return dateSent;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress)
    {
        this.customerAddress = customerAddress;
    }

    public void setOrderDate(Calendar orderDate)
    {
        this.orderDate = orderDate;
    }

    public void setDateSent(Calendar dateSent)
    {
        this.dateSent = dateSent;
    }
}
