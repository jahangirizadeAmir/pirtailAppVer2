package com.pirtail.piratilgame.Gamer.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pirtail.piratilgame.Class.CustomToast;
import com.pirtail.piratilgame.Class.vollayRequest;
import com.pirtail.piratilgame.R;
import com.pirtail.piratilgame.ServerCallback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;
import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

public class FragRace extends Fragment {

      static String Title;
       static String countGem;
      static String Img;
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
public static FragRace newInstance(Bundle bundle) {
    FragRace fragment = new FragRace();
    fragment.setArguments(bundle);
    return fragment;
}
//public static FragRace newInstance(String title, String countGeml, String img, int BackG) {
//
//    FragRace fragment = new FragRace();
//    Title = title;
//    countGem = countGeml;
//    Img = img;
//    Back = BackG;
//    return fragment;
//}

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.race_frag, null, false);

        String username = getResources().getString(R.string.username);
        String rank = getResources().getString(R.string.user_rank);
        String dimound_count = getResources().getString(R.string.txt_dimound_count);
//        int image = R.drawable.yellow_bg_empty;
        int charracter = R.drawable.ic_user_charracter;

        Bundle bundle = this.getArguments();


//        FragmentUserFrame fragmentUserFrame = FragmentUserFrame.newInstance(username, rank, dimound_count, image, charracter);
//        getChildFragmentManager()
//                .beginTransaction()
//                .add(R.id.fragment_container, fragmentUserFrame)
//                .commit();

        ImageView imageView = view.findViewById(R.id.img_challenge);
        if (bundle != null) {
            Picasso.with(getActivity()).load((Objects.requireNonNull(bundle.getString("img")))).error(R.drawable.img_bowling_game).into(imageView);
        }

        TextView textextChallenget = view.findViewById(R.id.textChallenge);
        textextChallenget.setText(Objects.requireNonNull(bundle).getString("name"));
        textextChallenget.setOnClickListener(view1 -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            LayoutInflater layoutInflater = getLayoutInflater();
            View alertView = layoutInflater.inflate(R.layout.race_info_dialog, null);

            TextView textView =alertView.findViewById(R.id.desc);
            textView.setText(bundle.getString("desc"));

            TextView textView2=alertView.findViewById(R.id.address);
            textView2.setText(bundle.getString("address"));

            TextView textView3 =alertView.findViewById(R.id.time);
            textView3.setText(bundle.getString("time"));

            alertDialog.setView(alertView);
            Dialog dialog = alertDialog.create();
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });
        ImageView imageView1 = view.findViewById(R.id.ic_back);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getActivity()).finish();
            }
        });
        TextView textView1 = view.findViewById(R.id.txt_Entrance);
        textView1.setText(" ورودی "+bundle.getString("price")+" الماس ");

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vollayRequest vollayRequest = new vollayRequest();
                HashMap<String, String> stringStringHashMap = new HashMap<>();

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Token", MODE_PRIVATE);
                String mobile = sharedPreferences.getString("mobile", null);
                String token = sharedPreferences.getString("token", null);

                stringStringHashMap.put("mobile", mobile);
                stringStringHashMap.put("token", token);
                stringStringHashMap.put("challengeId",bundle.getString("id"));
                vollayRequest.requester(
                        stringStringHashMap,
                        getContext(),
                        "acceptChallenge",
                        new ServerCallback() {
                            @Override
                            public void onSuccess(JSONObject result) throws JSONException {
                                CustomToast customToast=new CustomToast(getApplicationContext(), "ثبت نام با موفقیت انجام شد" , CustomToast.succ, CustomToast.Top);
                                customToast.getToast().show();
                            }
                        }
                );
            }
        });

        view.setBackgroundResource(bundle.getInt("back"));
        return view;
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
