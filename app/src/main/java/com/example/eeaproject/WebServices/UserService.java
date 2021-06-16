package com.example.eeaproject.WebServices;


import com.example.eeaproject.DTO.CurrentOrderDTO;
import com.example.eeaproject.DTO.CurrentOrderSummaryDTO;
import com.example.eeaproject.DTO.FulfilledOrderDTO;
import com.example.eeaproject.DTO.ProductDTO;
import com.example.eeaproject.DTO.UserDTO;

import java.util.Collection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    @GET("getUser/{username}/{password}") //READ function
    Call<UserDTO> checkCredentials(@Path("username") String username, @Path("password") String password);

    @GET("getAllProducts") //READ function
    Call<Collection<ProductDTO>> getAllProducts();

    @GET("getCurrentOrders") //READ function
    Call<Collection<CurrentOrderSummaryDTO>> getCurrentOrders();

    @GET("getFulfilledOrders") //READ function
    Call<Collection<FulfilledOrderDTO>> getFulfilledOrders();

    @GET("getOrderlinesByOrderID/{id}") //READ function
    Call<CurrentOrderDTO> getOrderlinesByOrderID(@Path("id") int id);

    @GET("getFulfilledByOrderID/{id}") //READ function
    Call<FulfilledOrderDTO> getFulfilledByOrderID(@Path("id") int id);

    @PUT("changeProductPrice/{id}/{price}") //UPDATE function
    Call<String> changeProductPrice(@Path("id") int id, @Path("price") Double price);

    @DELETE("sendCurrentOrder/{id}") //DELETE function
    Call<String> sendCurrentOrder(@Path("id") int id);

    @POST("createCurrentOrder") //CREATE function
    Call<String> createCurrentOrder(@Query("userJSON") String customerJson, @Query("orderJSON") String orderJson);
}
