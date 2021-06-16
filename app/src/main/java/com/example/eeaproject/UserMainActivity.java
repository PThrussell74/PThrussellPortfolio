package com.example.eeaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eeaproject.DTO.CurrentOrderDTO;
import com.example.eeaproject.DTO.CustomerDTO;
import com.example.eeaproject.DTO.FulfilledOrderDTO;
import com.example.eeaproject.DTO.OrderlineDTO;
import com.example.eeaproject.DTO.ProductDTO;
import com.example.eeaproject.DTO.ProductWithNewPrice;
import com.example.eeaproject.DTO.ProductWithQuantity;
import com.example.eeaproject.DTO.UserDTO;
import com.example.eeaproject.WebServices.BossClient;
import com.example.eeaproject.adapters.ProductListAdapter;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserMainActivity extends AppCompatActivity {

    Button logout;
    Button checkout;
    LinearLayout layout;
    ListView listView;
    TextView cartTotal;

    List<ProductDTO> booksList;
    private ArrayList<ProductDTO> books;

    Context context;

    HashMap<Integer, ProductWithQuantity> cart = new HashMap<>();
    int cacheSize = 10 * 1024* 1024;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        Intent intent = getIntent();
        context = this;

        logout = (Button)findViewById(R.id.logout);
        layout = (LinearLayout)findViewById(R.id.layout);
        listView = (ListView)findViewById(R.id.items);
        cartTotal = (TextView)findViewById(R.id.cartView);
        checkout = (Button)findViewById(R.id.checkout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                checkout();
            }
        });

        loadProductList();
    }

    private void loadProductList()
    {
        Cache cache = new Cache(getCacheDir(), cacheSize);
        Call<Collection<ProductDTO>> getProducts = BossClient.getUserService(cache).getAllProducts();

        getProducts.enqueue(new Callback<Collection<ProductDTO>>() {
            @Override
            public void onResponse(Call<Collection<ProductDTO>> call, Response<Collection<ProductDTO>> response)
            {

                if (response.isSuccessful()) //If a 200 response
                {
                    Collection<ProductDTO> products = response.body();
                    ProductListAdapter productAdapter = new ProductListAdapter(getApplicationContext(), context, new ArrayList<>(products));
                    listView.setAdapter(productAdapter);
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "My error:-"+" "+response.toString(), Toast.LENGTH_SHORT); toast.show();
                }

            }

            @Override
            public void onFailure(Call<Collection<ProductDTO>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),"Error: "+t,Toast.LENGTH_SHORT);toast.show();

            }
        });


    }

    public void addToCart(View theView)
    {
        ProductDTO product = (ProductDTO)theView.getTag();
        ProductWithQuantity cartItem = cart.get(product.getId());

        if (cartItem == null)
        {
            cartItem = new ProductWithQuantity(product);
            cart.put(cartItem.getId(), cartItem);
        }

        cartItem.setQuantity(cartItem.getQuantity() + 1);


        changeCartTotal();
    }

    public void removeFromCart(View theView)
    {
        ProductDTO product = (ProductDTO)theView.getTag();
        ProductWithQuantity cartItem = cart.get(product.getId());

        if (cartItem == null)
        {
            cartItem = new ProductWithQuantity(product);
            cart.remove(cartItem);

        }

        cartItem.setQuantity(cartItem.getQuantity() - 1);


        changeCartTotal();
    }

    public void checkout()
    {
        UserDTO cust;
        Bundle extras = getIntent().getExtras();
        if(extras == null)
        {
            cust= null;
        }
            else
        {
            cust= (UserDTO) extras.get("userData");
        }
        CustomerDTO customer= new CustomerDTO(cust.getCustomerId(), cust.getCustomerName(),cust.getCustomerAddress());

        ArrayList<OrderlineDTO> orderlines = new ArrayList<>(cart.size());

        for (ProductWithQuantity cartItem : cart.values())
        {
            orderlines.add(new OrderlineDTO(-1,cartItem.getUnitprice(),cartItem.getQuantity(),cartItem.getName()));
        }
        CurrentOrderDTO order= new CurrentOrderDTO(-1,Calendar.getInstance(),customer,orderlines);
        Gson gson = new Gson();

        Cache cache = new Cache(getCacheDir(), cacheSize);
        Call<String> received = BossClient.getUserService(cache).createCurrentOrder(gson.toJson(customer), gson.toJson(order));
        received.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response)
            {

                if (response.isSuccessful()) //If a 200 response
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Order sent!: ",Toast.LENGTH_SHORT); toast.show();


                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "My error:-"+" "+response.code(), Toast.LENGTH_SHORT); toast.show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),"Error: "+t,Toast.LENGTH_SHORT);

                toast.show();

            }
        });

    }

    private void changeCartTotal()
    {
        cartTotal.setText("Cart("+getNumberOfItemsInCart()+")  £"+getCost());
    }

    public int getNumberOfItemsInCart()
    {
        int totalQuantity = 0;
        for (ProductWithQuantity cartItem : cart.values())
        {
            totalQuantity += cartItem.getQuantity();

        }
        return totalQuantity;
    }

    public double getCost()
    {
        double totalQuantity = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        for (ProductWithQuantity cartItem : cart.values())
        {

            totalQuantity += cartItem.getUnitprice() * cartItem.getQuantity();

        }
        return Double.parseDouble(df.format(totalQuantity));
    }


}
