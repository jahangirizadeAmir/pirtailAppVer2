package com.pirtail.piratilgame.Gamer.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.pirtail.piratilgame.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {


    Intent intent;
    @BindView(R.id.img_user_profile)
    ImageView imgUserProfile;
    @BindView(R.id.img_settings)
    ImageView imgSettings;
    @BindView(R.id.img_report_bug)
    ImageView imgReportBug;
    @BindView(R.id.img_follow_us)
    ImageView imgFollowUs;
    @BindView(R.id.img_faq)
    ImageView imgFaq;
    @BindView(R.id.img_rules)
    ImageView imgRules;
    @BindView(R.id.img_about_us)
    ImageView imgAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        defineObjects();


    }

    private void defineObjects() {

    }

    @OnClick({R.id.img_user_profile, R.id.img_settings, R.id.img_report_bug, R.id.img_follow_us, R.id.img_faq, R.id.img_rules, R.id.img_about_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_user_profile:
                intent = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.img_settings:
                intent = new Intent(MenuActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.img_report_bug:
                intent = new Intent(MenuActivity.this, ReportActivity.class);
                startActivity(intent);
                break;
            case R.id.img_follow_us:
                intent = new Intent(MenuActivity.this, SocialNetworksActivity.class);
                startActivity(intent);
                break;
            case R.id.img_faq:
                intent = new Intent(MenuActivity.this, FaqActivity.class);
                startActivity(intent);
                break;
            case R.id.img_rules:
                intent = new Intent(MenuActivity.this, RulsActivity.class);
                startActivity(intent);
                break;
            case R.id.img_about_us:
                intent = new Intent(MenuActivity.this, AboutUsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
