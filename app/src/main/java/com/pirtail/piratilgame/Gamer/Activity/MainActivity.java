package com.pirtail.piratilgame.Gamer.Activity;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pirtail.piratilgame.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.img_challenge)
    ImageView img_challenge;

    @BindView(R.id.img_search)
    ImageView img_search;

    @BindView(R.id.ic_cup)
    ImageView ic_cup;

    @BindView(R.id.ic_menu)
    ImageView ic_menu;

    @BindView(R.id.txt_username)
    TextView txt_username;

    @BindView(R.id.txt_user_ranking)
    TextView txt_user_ranking;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        defineObjects();

    }

    private void defineObjects() {
        img_challenge.setOnClickListener(this);
        img_search.setOnClickListener(this);
        ic_cup.setOnClickListener(this);
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
