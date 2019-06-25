package com.pirtail.piratilgame.Gamer.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.pirtail.piratilgame.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.img_user_profile)
    ImageView img_user_profile;

    @BindView(R.id.img_settings)
    ImageView img_settings;

    @BindView(R.id.img_report_bug)
    ImageView img_report_bug;

    @BindView(R.id.img_follow_us)
    ImageView img_follow_us;

    @BindView(R.id.img_faq)
    ImageView img_faq;

    @BindView(R.id.img_rules)
    ImageView img_rules;

    @BindView(R.id.img_about_us)
    ImageView img_about_us;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        defineObjects();



    }

    private void defineObjects() {
        img_user_profile.setOnClickListener(this);
        img_settings.setOnClickListener(this);
        img_report_bug.setOnClickListener(this);
        img_follow_us.setOnClickListener(this);
        img_faq.setOnClickListener(this);
        img_rules.setOnClickListener(this);
        img_about_us.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_user_profile:
                intent = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.img_settings:
                intent = new Intent(MenuActivity.this, RankingActivity.class);
                startActivity(intent);
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
