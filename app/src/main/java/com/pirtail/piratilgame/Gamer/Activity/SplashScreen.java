package com.pirtail.piratilgame.Gamer.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.pirtail.piratilgame.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {

                                          SharedPreferences sharedPreferences = getSharedPreferences("Token", MODE_PRIVATE);
                                          boolean login = sharedPreferences.getBoolean("login", false);
                                          if (login){
                                              Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                                              startActivity(intent);
                                          } else {
                                              Intent intent = new Intent(SplashScreen.this, PhoneEnterActivity.class);
                                              startActivity(intent);
                                          }
                                          Toast.makeText(SplashScreen.this, ""+login, Toast.LENGTH_SHORT).show();
                                      }
                                  }

                ,1000

        );


    }
}
