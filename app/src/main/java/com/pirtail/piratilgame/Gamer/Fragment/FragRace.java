package com.pirtail.piratilgame.Gamer.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pirtail.piratilgame.R;

public class FragRace extends Fragment {

      static String Title;
       static String countGem;
      static int Img;
      static int Back;


//    public static FragRace myFragment(String title, String countGeml, int img, int BackG) {
//        Title = title;
//        countGem = countGeml;
//        Img = img;
//        Back = BackG;
//
//
//        return new FragRace();
//
//    }
public static FragRace newInstance(String title, String countGeml, int img, int BackG) {



    FragRace fragment = new FragRace();
    Title = title;
    countGem = countGeml;
    Img = img;
    Back = BackG;
    return fragment;
}

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.race_frag, null, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.img_challenge);
        imageView.setImageResource(Img);

        TextView textView = (TextView)view.findViewById(R.id.textChallenge);
        textView.setText(Title);

        TextView textView1 = (TextView)view.findViewById(R.id.txt_Entrance);

        textView1.setText(countGem);

        view.setBackgroundResource(Back);

        return view;
    }
}
