package com.pirtail.piratilgame.Gamer.Activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.pirtail.piratilgame.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FaqActivity extends AppCompatActivity {

    @BindView(R.id.ic_back)
    ImageView icBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.ic_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
