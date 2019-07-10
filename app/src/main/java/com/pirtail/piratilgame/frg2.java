package com.pirtail.piratilgame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.OnClick;

public class frg2 extends Fragment {
    @BindView(R.id.ic_back)
    ImageView icBack;
    @BindView(R.id.frame_user_info)
    ImageView frameUserInfo;
    @BindView(R.id.txt_username)
    TextView txtUsername;
    @BindView(R.id.txt_user_ranking)
    TextView txtUserRanking;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ranking, container, false);



        return view;
    }

    @OnClick(R.id.ic_back)
    public void onViewClicked() {

    }
}
