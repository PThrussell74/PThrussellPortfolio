package com.example.eeaproject.DTO;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

/**
 *
 * @author gdm1
 */
public class FulfilledOrderDTO implements Serializable
{

    private int id;
    private String customerName;
    private String customerAddress;
    private Calendar orderDate;
    private Calendar dateSent;
    private Collection<FulfilledOrderlineDTO> orderlines;

    public FulfilledOrderDTO()
    {
        this(-1, "??", "????", null, null, null);
    }

    public FulfilledOrderDTO(int id, String customerName, String customerAddress, Calendar orderDate, Calendar dateSent, Collection<FulfilledOrderlineDTO> orderlines)
    {
        this.id = id;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.orderDate = orderDate;
        this.dateSent = dateSent;
        this.orderlines = orderlines;
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

    public Collection<FulfilledOrderlineDTO> getOrderlines()
    {
        return orderlines;
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

    public void setOrderlines(Collection<FulfilledOrderlineDTO> orderlines)
    {
        this.orderlines = orderlines;
    }
}
