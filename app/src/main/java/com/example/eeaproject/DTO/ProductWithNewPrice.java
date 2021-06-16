package com.example.eeaproject.DTO;



/**
 *
 * @author gdm1
 */
public class ProductWithNewPrice
{

    private final ProductDTO product;
    private double newPrice = 0.0;

    public ProductWithNewPrice(ProductDTO product)
    {
        this.product = product;
    }

    public int getId()
    {
        return product.getId();
    }

    public String getName()
    {
        return product.getName();
    }

    public String getDescription()
    {
        return product.getDescription();
    }

    public double getNewPrice()
    {
        return newPrice;
    }

    public double getUnitprice()
    {
        return product.getUnitprice();
    }

    public void setNewPrice(double newPrice)
    {
        this.newPrice = newPrice;
    }
}
