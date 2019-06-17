package com.pirtail.piratilgame.Gamer.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.pirtail.piratilgame.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img_user_profile,
            img_settings,
            img_report_bug,
            img_follow_us,
            img_faq,
            img_rules,
            img_about_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        defineObjects();

        img_user_profile=(ImageView)findViewById(R.id.img_user_profile);
        img_settings=(ImageView)findViewById(R.id.img_settings);
        img_report_bug=(ImageView)findViewById(R.id.img_report_bug);
        img_follow_us=(ImageView)findViewById(R.id.img_follow_us);
        img_faq=(ImageView)findViewById(R.id.img_faq);
        img_rules=(ImageView)findViewById(R.id.img_rules);
        img_about_us=(ImageView)findViewById(R.id.img_about_us);

        img_user_profile.setOnClickListener(this);
        img_settings.setOnClickListener(this);
        img_report_bug.setOnClickListener(this);
        img_follow_us.setOnClickListener(this);
        img_faq.setOnClickListener(this);
        img_rules.setOnClickListener(this);
        img_about_us.setOnClickListener(this);


    }

    private void defineObjects() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_user_profile:

                break;
            case R.id.img_settings:

                break;
            case R.id.img_report_bug:

                break;
            case R.id.img_follow_us:

                break;
            case R.id.img_faq:

                break;
            case R.id.img_rules:

                break;
            case R.id.img_about_us:

                break;
        }
    }
}
