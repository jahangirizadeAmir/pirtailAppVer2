package com.pirtail.piratilgame.Gamer.Activity;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pirtail.piratilgame.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img_challenge, img_search, ic_cup, ic_menu;
    TextView txt_username, txt_user_ranking;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defineObjects();

        img_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, View_pager.class);
                startActivity(intent);
//                finish();
            }
        });

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
//                finish();
            }
        });




    }

    private void defineObjects() {
        txt_username=(TextView) findViewById(R.id.txt_username);
        txt_user_ranking=(TextView) findViewById(R.id.txt_user_ranking);

        img_challenge=(ImageView) findViewById(R.id.img_challenge);
        img_challenge.setOnClickListener(this);

        img_search=(ImageView) findViewById(R.id.img_search);
        img_search.setOnClickListener(this);

        ic_cup=(ImageView) findViewById(R.id.ic_cup);
        ic_cup.setOnClickListener(this);

        ic_menu=(ImageView) findViewById(R.id.ic_menu);
        ic_menu.setOnClickListener(this);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_challenge :
                intent= new Intent(MainActivity.this, View_pager.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.img_search:
                intent= new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.ic_cup:
                intent= new Intent(MainActivity.this, RankingActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.ic_menu:
                intent= new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
//                finish();
                break;
        }
    }
}
