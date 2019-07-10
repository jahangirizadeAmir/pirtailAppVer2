package com.pirtail.piratilgame.Gamer.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pirtail.piratilgame.Gamer.Activity.MenuActivity;
import com.pirtail.piratilgame.Gamer.Activity.ProfileActivity;
import com.pirtail.piratilgame.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUserFrame extends Fragment {

    static String username;
    static String rank;
    static int image;
    Bundle arguments;

    public static final String USERNAME_KEY = "USERNAME_KEY";
    public static final String RANK_KEY = "RANK_KEY";
    public static final String IMAGE_KEY = "IMAGE_KEY";
    public static final String DIMOUND_COUNT = "DIMOUND_COUNT";
    public static final String CHARRACTER_KEY = "CHARRACTER_COUNT";


//    @BindView(R.id.img_frame)
//    ImageView imgFrame;
//    @BindView(R.id.txt_username)
//    TextView txtUsername;
//    @BindView(R.id.txt_user_rank)
//    TextView txtUserRank;

    public static FragmentUserFrame newInstance(String username, String rank, String dimound_count, int image, int charracter) {

        Bundle args = new Bundle();

        args.putString(USERNAME_KEY, username);
        args.putString(RANK_KEY, rank);
        args.putString(DIMOUND_COUNT, dimound_count);
        args.putInt(IMAGE_KEY, image);
        args.putInt(CHARRACTER_KEY, charracter);

        FragmentUserFrame fragment = new FragmentUserFrame();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arguments = new Bundle();
        arguments = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_user_frame, container, false);

        String username = arguments.getString(USERNAME_KEY);
        String rank = arguments.getString(RANK_KEY);
        String dimound_count = arguments.getString(DIMOUND_COUNT);
        int bg_image = arguments.getInt(IMAGE_KEY);
        int charracte_image = arguments.getInt(CHARRACTER_KEY);

        TextView txtUsername = (TextView) view.findViewById(R.id.txt_username);
        TextView txtUserRank = (TextView) view.findViewById(R.id.txt_user_rank);
        TextView txt_dimound_count = (TextView) view.findViewById(R.id.txt_dimound_count);
        ImageView imgFrame = (ImageView) view.findViewById(R.id.img_user_frame);
        ImageView img_charracter = (ImageView) view.findViewById(R.id.img_charracter);

        txtUsername.setText(username);
        txtUserRank.setText(rank);
        txt_dimound_count.setText(dimound_count);
        imgFrame.setImageResource(bg_image);
        img_charracter.setImageResource(charracte_image);

        RelativeLayout root_layout = (RelativeLayout) view.findViewById(R.id.root_layout);
        root_layout.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            startActivity(intent);
        });

        return view;
    }

}
