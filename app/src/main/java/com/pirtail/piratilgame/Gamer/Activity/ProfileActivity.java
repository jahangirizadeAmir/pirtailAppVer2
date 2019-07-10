package com.pirtail.piratilgame.Gamer.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pirtail.piratilgame.Gamer.Fragment.FragmentUserFrame;
import com.pirtail.piratilgame.R;

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

        String username = getResources().getString(R.string.username);
        String rank = getResources().getString(R.string.user_rank);
        String dimound_count = getResources().getString(R.string.txt_dimound_count);
        int image = R.drawable.yellow_bg_empty;
        int charracter = R.drawable.ic_user_charracter;

        FragmentUserFrame fragmentUserFrame = FragmentUserFrame.newInstance(username, rank, dimound_count, image, charracter);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragmentUserFrame)
                .commit();

    }

    @OnClick(R.id.ic_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
