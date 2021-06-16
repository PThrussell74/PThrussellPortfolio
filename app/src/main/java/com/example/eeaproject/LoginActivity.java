package com.example.eeaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eeaproject.DTO.UserDTO;
import com.example.eeaproject.WebServices.BossClient;

import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    UserDTO loggedUser;

    Button login_button;
    Button signup_button;

    EditText username_text;
    EditText password_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_button = findViewById(R.id.login_Button);
        username_text = findViewById(R.id.username_Text);
        password_text = findViewById(R.id.password_Text);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();



            }
        });
    }

    public void login()
    {

        int cacheSize = 10 * 1024* 1024;
        Cache cache = new Cache(getCacheDir(), cacheSize);
        Call<UserDTO> theResponse = BossClient.getUserService(cache).checkCredentials(username_text.getText().toString(), password_text.getText().toString());

        theResponse.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response)
            {

                if (response.isSuccessful()) //If a 200 response
                {
                    loggedUser = response.body();
                    if (response.body().isLoggedIn())
                    {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (loggedUser.isIsAdmin()) //For future admin page
                                {
                                    startActivity(new Intent(LoginActivity.this, AdminMainActivity.class).putExtra("userData", loggedUser));
                                }
                                else
                                {
                                    startActivity(new Intent(LoginActivity.this, UserMainActivity.class).putExtra("userData", loggedUser));
                                }
                            }
                        },  700);
                    }
                    else
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Oops! We couldn't find a user with these credentials!", Toast.LENGTH_SHORT); toast.show();
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "My error:-"+" "+response.toString(), Toast.LENGTH_SHORT); toast.show();
                }

            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),"Error: "+t,Toast.LENGTH_SHORT);

                toast.show();

            }
        });

    }
}
