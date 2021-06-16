package com.example.eeaproject.DTO;

import java.io.Serializable;

/**
 *
 * @author gdm1
 */
public class ProductDTO implements Serializable
{
    private int id;
    private String name;
    private String description;
    private double unitprice;

    public ProductDTO()
    {
        this(-1, "??", "????", -1);
    }

    public ProductDTO(int id, String name, String description, double unitprice)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitprice = unitprice;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public double getUnitprice()
    {
        return unitprice;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setUnitprice(double unitprice)
    {
        this.unitprice = unitprice;
    }
}
