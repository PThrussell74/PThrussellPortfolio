package com.example.eeaproject.DTO;

import java.io.Serializable;

/**
 *
 * @author gdm1
 */
public class UserDTO implements Serializable
{
    private String username;
    private boolean isAdmin;
    private int customerId;
    private String customerName;
    private String customerAddress;
    private boolean isLoggedIn;

    public UserDTO()
    {
        this("??", false, -1, "??", "????", false);
    }

    public UserDTO(String username, boolean isAdmin, int customerId, String customerName, String customerAddress, boolean isLoggedIn)
    {
        this.username = username;
        this.isAdmin = isAdmin;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.isLoggedIn = isLoggedIn;
    }

    public String getUsername()
    {
        return username;
    }

    public boolean isIsAdmin()
    {
        return isAdmin;
    }

    public int getCustomerId()
    {
        return customerId;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public String getCustomerAddress()
    {
        return customerAddress;
    }

    public boolean isLoggedIn()
    {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn)
    {
        this.isLoggedIn = isLoggedIn;
    }

}
