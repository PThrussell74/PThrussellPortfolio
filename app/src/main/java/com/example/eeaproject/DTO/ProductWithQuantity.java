package com.example.eeaproject.DTO;

/**
 *
 * @author gdm1
 */
public class ProductWithQuantity
{

    private final ProductDTO product;
    private int quantity = 0;

    public ProductWithQuantity(ProductDTO product)
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

    public int getQuantity()
    {
        return quantity;
    }

    public double getUnitprice()
    {
        return product.getUnitprice();
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}

