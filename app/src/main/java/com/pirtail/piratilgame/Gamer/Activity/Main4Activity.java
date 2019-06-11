package com.pirtail.piratilgame.Gamer.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pirtail.piratilgame.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Main4Activity extends AppCompatActivity {

    ImageView img_user_character;
    TextView txt_username, txt_user_ranking;
    Button btn_find_diamond, btn_challenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        defineObjects();

        btn_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Main4Activity.this, Main5Activity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    private void defineObjects() {
        img_user_character=(ImageView)findViewById(R.id.img_user_character);
        txt_username=(TextView) findViewById(R.id.txt_username);
        txt_user_ranking=(TextView) findViewById(R.id.txt_user_ranking);
        btn_challenge=(Button) findViewById(R.id.btn_challenge);
        btn_find_diamond=(Button) findViewById(R.id.btn_find_diamond);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
