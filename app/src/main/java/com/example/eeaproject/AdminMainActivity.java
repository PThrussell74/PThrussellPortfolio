package com.example.eeaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eeaproject.DTO.CurrentOrderDTO;
import com.example.eeaproject.DTO.CurrentOrderSummaryDTO;
import com.example.eeaproject.DTO.FulfilledOrderDTO;
import com.example.eeaproject.DTO.OrderlineDTO;
import com.example.eeaproject.DTO.ProductDTO;
import com.example.eeaproject.DTO.ProductWithQuantity;
import com.example.eeaproject.Fragments.CurrentFragment;
import com.example.eeaproject.Fragments.FulfilledFragment;
import com.example.eeaproject.Fragments.ProductFragment;
import com.example.eeaproject.Fragments.SingleOrderFragment;
import com.example.eeaproject.WebServices.BossClient;
import com.example.eeaproject.adapters.AdminProductListAdapter;
import com.example.eeaproject.adapters.CurrentListAdapter;
import com.example.eeaproject.adapters.FulfilledDetailsAdapter;
import com.example.eeaproject.adapters.FulfilledListAdapter;
import com.example.eeaproject.adapters.OrderDetailsAdapter;
import com.example.eeaproject.adapters.ProductListAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminMainActivity extends AppCompatActivity {

    Button logout;
    LinearLayout layout;
    ListView listView;

    List<ProductDTO> booksList;
    private ArrayList<ProductDTO> books;

    Context context;

    HashMap<Integer, ProductWithQuantity> cart = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Intent intent = getIntent();
        context = this;

        logout = (Button) findViewById(R.id.logout);
        layout = (LinearLayout) findViewById(R.id.layout);
        listView = (ListView)findViewById(R.id.productView);




        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void addClickHandler(View view) {

        ProductFragment fragment = new ProductFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                //.add(R.id.fragment_container, fragment, FRAGMENT_TAG)
                .replace(R.id.fragment_container, fragment)
                .commit();
        int cacheSize = 10 * 1024* 1024;
        Cache cache = new Cache(getCacheDir(), cacheSize);
        Call<Collection<ProductDTO>> getProducts = BossClient.getUserService(cache).getAllProducts();

        getProducts.enqueue(new Callback<Collection<ProductDTO>>() {
            @Override
            public void onResponse(Call<Collection<ProductDTO>> call, Response<Collection<ProductDTO>> response)
            {

                if (response.isSuccessful()) //If a 200 response
                {
                    listView = (ListView)findViewById(R.id.productView);
                    Collection<ProductDTO> products = response.body();
                    AdminProductListAdapter productAdapter = new AdminProductListAdapter(getApplicationContext(), context, new ArrayList<>(products));
                    listView.setAdapter(productAdapter);
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "My error:-"+" "+response.toString(), Toast.LENGTH_SHORT); toast.show();
                }

            }

            @Override
            public void onFailure(Call<Collection<ProductDTO>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),"Error: "+t,Toast.LENGTH_SHORT);

                toast.show();

            }
        });



    }

    public void addClickHandler2(View view) {

        CurrentFragment fragment = new CurrentFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                //.add(R.id.fragment_container, fragment, FRAGMENT_TAG)
                .replace(R.id.fragment_container, fragment)
                .commit();
        int cacheSize = 10 * 1024* 1024;
        Cache cache = new Cache(getCacheDir(), cacheSize);
        Call<Collection<CurrentOrderSummaryDTO>> getProducts = BossClient.getUserService(cache).getCurrentOrders();

        getProducts.enqueue(new Callback<Collection<CurrentOrderSummaryDTO>>() {
            @Override
            public void onResponse(Call<Collection<CurrentOrderSummaryDTO>> call, Response<Collection<CurrentOrderSummaryDTO>> response)
            {

                if (response.isSuccessful()) //If a 200 response
                {
                    listView = (ListView)findViewById(R.id.currentView);
                    Collection<CurrentOrderSummaryDTO> products = response.body();
                    CurrentListAdapter productAdapter = new CurrentListAdapter(getApplicationContext(), context, new ArrayList<>(products));
                    listView.setAdapter(productAdapter);
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "My error:-"+" "+response.toString(), Toast.LENGTH_SHORT); toast.show();
                }

            }

            @Override
            public void onFailure(Call<Collection<CurrentOrderSummaryDTO>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),"Error: "+t,Toast.LENGTH_SHORT);

                toast.show();

            }
        });
    }





    public void addClickHandler3(View view) {

        FulfilledFragment fragment = new FulfilledFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                //.add(R.id.fragment_container, fragment, FRAGMENT_TAG)
                .replace(R.id.fragment_container, fragment)
                .commit();
        int cacheSize = 10 * 1024* 1024;
        Cache cache = new Cache(getCacheDir(), cacheSize);
        Call<Collection<FulfilledOrderDTO>> getProducts = BossClient.getUserService(cache).getFulfilledOrders();

        getProducts.enqueue(new Callback<Collection<FulfilledOrderDTO>>() {
            @Override
            public void onResponse(Call<Collection<FulfilledOrderDTO>> call, Response<Collection<FulfilledOrderDTO>> response)
            {

                if (response.isSuccessful()) //If a 200 response
                {
                    listView = (ListView)findViewById(R.id.currentView);
                    Collection<FulfilledOrderDTO> products = response.body();
                    FulfilledListAdapter productAdapter = new FulfilledListAdapter(getApplicationContext(), context, new ArrayList<>(products));
                    listView.setAdapter(productAdapter);
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "My error:-"+" "+response.toString(), Toast.LENGTH_SHORT); toast.show();
                }

            }

            @Override
            public void onFailure(Call<Collection<FulfilledOrderDTO>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),"Error: "+t,Toast.LENGTH_SHORT);

                toast.show();

            }
        });
    }

    private void loadProductList()
    {
    }

    public void changePrice(View theView, double price, int productID)
    {
        if (price <= 0)
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Please enter a number above zero!",Toast.LENGTH_SHORT); toast.show();
        }
        else
        {
            int cacheSize = 10 * 1024* 1024;
            Cache cache = new Cache(getCacheDir(), cacheSize);
            Call<String> received = BossClient.getUserService(cache).changeProductPrice(productID, price);
            received.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response)
                {

                    if (response.isSuccessful()) //If a 200 response
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Changed price: "+price,Toast.LENGTH_SHORT); toast.show();
                        Fragment fragment =
                                getSupportFragmentManager()
                                        //.findFragmentByTag(FRAGMENT_TAG);
                                        .findFragmentById(R.id.fragment_container);
                        if (fragment != null) {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .remove(fragment)
                                    .commit();
                        }
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
        for (ProductWithQuantity cartItem : cart.values())
        {
            totalQuantity += cartItem.getUnitprice() * cartItem.getQuantity();

        }
        return totalQuantity;
    }


    public void viewEntry(View v, int theDTO)
    {
        SingleOrderFragment fragment = new SingleOrderFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                //.add(R.id.fragment_container, fragment, FRAGMENT_TAG)
                .replace(R.id.fragment_container, fragment)
                .commit();
        int cacheSize = 10 * 1024* 1024;
        Cache cache = new Cache(getCacheDir(), cacheSize);
        Call<CurrentOrderDTO> getProducts = BossClient.getUserService(cache).getOrderlinesByOrderID(theDTO);

        getProducts.enqueue(new Callback<CurrentOrderDTO>() {
            @Override
            public void onResponse(Call<CurrentOrderDTO> call, Response<CurrentOrderDTO> response)
            {

                if (response.isSuccessful()) //If a 200 response
                {
                    listView = (ListView)findViewById(R.id.currentView);
                    CurrentOrderDTO products = response.body();
                    OrderDetailsAdapter productAdapter = new OrderDetailsAdapter(getApplicationContext(), context, new ArrayList<>(products.getOrderlines()));
                    listView.setAdapter(productAdapter);
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "My error:-"+" "+response.toString(), Toast.LENGTH_SHORT); toast.show();
                }

            }

            @Override
            public void onFailure(Call<CurrentOrderDTO> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),"Error: "+t,Toast.LENGTH_SHORT);

                toast.show();

            }
        });



    }

    public void sendEntry(View v, int theDTO)
    {
        int cacheSize = 10 * 1024* 1024;
        Cache cache = new Cache(getCacheDir(), cacheSize);
        Call<String> received = BossClient.getUserService(cache).sendCurrentOrder(theDTO);
        received.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response)
            {

                if (response.isSuccessful()) //If a 200 response
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Confirmed for ID "+theDTO,Toast.LENGTH_SHORT); toast.show();
                    Fragment fragment =
                            getSupportFragmentManager()
                                    //.findFragmentByTag(FRAGMENT_TAG);
                                    .findFragmentById(R.id.fragment_container);
                    if (fragment != null) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .remove(fragment)
                                .commit();
                    }
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

    public void viewEntry2(View v, FulfilledOrderDTO theDTO)
    {
        SingleOrderFragment fragment = new SingleOrderFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                //.add(R.id.fragment_container, fragment, FRAGMENT_TAG)
                .replace(R.id.fragment_container, fragment)
                .commit();

        int cacheSize = 10 * 1024* 1024;
        Cache cache = new Cache(getCacheDir(), cacheSize);
        Call<FulfilledOrderDTO> getProducts = BossClient.getUserService(cache).getFulfilledByOrderID(theDTO.getId());

        getProducts.enqueue(new Callback<FulfilledOrderDTO>() {
            @Override
            public void onResponse(Call<FulfilledOrderDTO> call, Response<FulfilledOrderDTO> response)
            {

                if (response.isSuccessful()) //If a 200 response
                {
                    listView = (ListView)findViewById(R.id.currentView);
                    FulfilledOrderDTO products = response.body();
                    FulfilledDetailsAdapter productAdapter = new FulfilledDetailsAdapter(getApplicationContext(), context, new ArrayList<>(products.getOrderlines()));
                    listView.setAdapter(productAdapter);
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "My error:-"+" "+response.toString(), Toast.LENGTH_SHORT); toast.show();
                }

            }

            @Override
            public void onFailure(Call<FulfilledOrderDTO> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),"Error: "+t,Toast.LENGTH_SHORT);

                toast.show();

            }
        });
    }
}