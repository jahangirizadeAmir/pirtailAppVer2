package com.pirtail.piratilgame.Gamer.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pirtail.piratilgame.R;

public class FragBowling extends Fragment {

      static String Title;
       static String countGem;
      static int Img;
      static int Back;


//    public static FragBowling myFragment(String title, String countGeml, int img, int BackG) {
//        Title = title;
//        countGem = countGeml;
//        Img = img;
//        Back = BackG;
//
//
//        return new FragBowling();
//
//    }
public static FragBowling newInstance(String title, String countGeml, int img, int BackG) {



    FragBowling fragment = new FragBowling();
    Title = title;
    countGem = countGeml;
    Img = img;
    Back = BackG;
    return fragment;
}

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bowling_frag, null, false);

        ImageView imageView = view.findViewById(R.id.img_challenge);
        imageView.setImageResource(Img);

        TextView textView = view.findViewById(R.id.textChallenge);
        textView.setText(Title);

        TextView textView1 = view.findViewById(R.id.txt_Entrance);

        textView1.setText(countGem);

        view.setBackgroundResource(Back);

        return view;
    }
}
