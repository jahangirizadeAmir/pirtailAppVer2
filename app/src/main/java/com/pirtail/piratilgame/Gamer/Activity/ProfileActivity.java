package com.pirtail.piratilgame.Gamer.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pirtail.piratilgame.Gamer.Fragment.FragmentUserFrame;
import com.pirtail.piratilgame.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.ic_back)
    ImageView icBack;
    @BindView(R.id.frame_user_info)
    ImageView frameUserInfo;
    @BindView(R.id.txt_username)
    TextView txtUsername;
    @BindView(R.id.txt_user_ranking)
    TextView txtUserRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        defineObjects();
    }

    private void defineObjects() {

        String rank = getResources().getString(R.string.user_rank);
        int image = R.drawable.yellow_bg_empty;
        int charracter;

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);

        //get jender:
        String gender = sharedPreferences.getString("gender", "1");
//        gender = intent.getExtras().getString("gender", "1");
        if (gender.equals("0")){
            charracter = R.drawable.ic_girl_charracter;
        } else {
            charracter = R.drawable.ic_user_charracter;
        }

        //get username:
        String username = sharedPreferences.getString("name", "gust");

        //get dimound count:
        String dimound_count = sharedPreferences.getString("dimound_count", "0");


        FragmentUserFrame fragmentUserFrame = FragmentUserFrame.newInstance(username/*, rank*/, dimound_count, image, charracter);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragmentUserFrame)
                .commit();

    }

    @OnClick(R.id.ic_back)
    public void onViewClicked() {
        onBackPressed();
    }

    //encrypting token
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
