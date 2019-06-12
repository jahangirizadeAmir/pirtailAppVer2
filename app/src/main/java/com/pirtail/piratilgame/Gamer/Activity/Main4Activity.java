package com.pirtail.piratilgame.Gamer.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pirtail.piratilgame.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Main4Activity extends AppCompatActivity {

    ImageView img_challenge;
    TextView txt_username, txt_user_ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        defineObjects();

        img_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Main4Activity.this, View_pager.class);
                startActivity(intent);
//                finish();
            }
        });


    }

    private void defineObjects() {
        txt_username=(TextView) findViewById(R.id.txt_username);
        txt_user_ranking=(TextView) findViewById(R.id.txt_user_ranking);
        img_challenge=(ImageView) findViewById(R.id.img_challenge);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
