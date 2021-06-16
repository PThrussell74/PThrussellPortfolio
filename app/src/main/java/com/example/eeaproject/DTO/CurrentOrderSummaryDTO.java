package com.example.eeaproject.DTO;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author gdm1
 */
public class CurrentOrderSummaryDTO implements Serializable
{

    private int id;
    private Calendar orderdate;
    private CustomerDTO customer;

    public CurrentOrderSummaryDTO()
    {
        this(-1, null, null);
    }

    public CurrentOrderSummaryDTO(int id, Calendar orderdate, CustomerDTO customerid)
    {
        this.id = id;
        this.orderdate = orderdate;
        this.customer = customerid;
    }

    public int getId()
    {
        return id;
    }

    public Calendar getOrderdate()
    {
        return orderdate;
    }

    public CustomerDTO getCustomer()
    {
        return customer;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setOrderdate(Calendar orderdate)
    {
        this.orderdate = orderdate;
    }

    public void setCustomer(CustomerDTO customer)
    {
        this.customer = customer;
    }
}
