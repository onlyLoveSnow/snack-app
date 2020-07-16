package com.shuyue.snack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.shuyue.snack.MainActivity;
import com.shuyue.snack.R;
import com.shuyue.snack.model.User;
import com.shuyue.snack.network.TestService;
import com.shuyue.snack.network.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, 500);
    }

    private void getRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}