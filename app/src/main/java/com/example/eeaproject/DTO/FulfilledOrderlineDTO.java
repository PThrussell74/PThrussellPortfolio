package com.example.eeaproject.DTO;

import java.io.Serializable;

/**
 *
 * @author gdm1
 */
public class FulfilledOrderlineDTO implements Serializable
{
    private int id;
    private double unitprice;
    private int quantity;
    private String productName;

    public FulfilledOrderlineDTO()
    {
        this(-1, -1, -1, "??");
    }

    public FulfilledOrderlineDTO(int id, double unitprice, int quantity, String productName)
    {
        this.id = id;
        this.unitprice = unitprice;
        this.quantity = quantity;
        this.productName = productName;
    }

    public int getId()
    {
        return id;
    }

    public double getUnitprice()
    {
        return unitprice;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setUnitprice(double unitprice)
    {
        this.unitprice = unitprice;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }
}
