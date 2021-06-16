package com.example.eeaproject.DTO;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

/**
 *
 * @author gdm1
 */
public class CurrentOrderDTO implements Serializable
{

    private int id;
    private Calendar orderdate;
    private CustomerDTO customer;
    private Collection<OrderlineDTO> orderlines;

    public CurrentOrderDTO()
    {
        this(-1, null, null, null);
    }

    public CurrentOrderDTO(int id, Calendar orderdate, CustomerDTO customer, Collection<OrderlineDTO> orderlines)
    {
        this.id = id;
        this.orderdate = orderdate;
        this.customer = customer;
        this.orderlines = orderlines;
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

    public Collection<OrderlineDTO> getOrderlines()
    {
        return orderlines;
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

    public void setOrderlines(Collection<OrderlineDTO> orderlines)
    {
        this.orderlines = orderlines;
    }
}
