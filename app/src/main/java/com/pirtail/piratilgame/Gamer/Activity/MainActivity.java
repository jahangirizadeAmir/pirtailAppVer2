package com.pirtail.piratilgame.Gamer.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pirtail.piratilgame.Class.vollayRequest;
import com.pirtail.piratilgame.Gamer.Fragment.FragmentUserFrame;
import com.pirtail.piratilgame.R;
import com.pirtail.piratilgame.ServerCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.txt_username)
    TextView txt_username;

    @BindView(R.id.txt_user_ranking)
    TextView txt_user_ranking;

    Intent intent;

    SharedPreferences sharedPreferences;

    AlertDialog.Builder alertDialog;
    LayoutInflater layoutInflater;
    View view;
    AlertDialog dialog;
    @BindView(R.id.img_challenge)
    ImageView imgChallenge;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.img_add_dimound)
    ImageView imgAddDimound;
    @BindView(R.id.ic_menu)
    ImageView icMenu;
    @BindView(R.id.ic_cup)
    ImageView icCup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        defineObjects("");

        SharedPreferences sharedPreferences2 = getSharedPreferences("Token", MODE_PRIVATE);
        sharedPreferences2.edit().putBoolean("login", true).apply();

    }

    private void defineObjects(String D) {
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);

        String dimound_count;
        if(D.equals("")){
             dimound_count = sharedPreferences.getString("dimound_count", "0");
        }else{
            dimound_count = D;
        }

        String rank = getResources().getString(R.string.user_rank);
        int image = R.drawable.green_bg_empty;

        int charracter;



        //get jender:
        String gender = sharedPreferences.getString("gender", "1");
//        gender = intent.getExtras().getString("gender", "1");
        if (gender.equals("0")) {
            charracter = R.drawable.ic_girl_charracter;
        } else {
            charracter = R.drawable.ic_user_charracter;
        }

        //get username:
        String username = sharedPreferences.getString("name", "gust");

        //get dimound count:
//        String dimound_count = sharedPreferences.getString("dimound_count", "0");

        FragmentUserFrame fragmentUserFrame = FragmentUserFrame.newInstance(username/*, rank*/,dimound_count , image, charracter);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragmentUserFrame)
                .commit();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_challenge:
                intent = new Intent(MainActivity.this, View_pager.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.img_search:
                intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.ic_cup:
                intent = new Intent(MainActivity.this, RankingActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.ic_menu:
                intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
//                finish();
                break;

        }
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

    @OnClick({R.id.img_challenge, R.id.img_search, R.id.img_add_dimound, R.id.ic_menu, R.id.ic_cup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_challenge:
                intent = new Intent(MainActivity.this, View_pager.class);
                startActivity(intent);
                break;
            case R.id.img_search:
                intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
                break;
            case R.id.img_add_dimound:

                alertDialog = new AlertDialog.Builder(MainActivity.this);
                layoutInflater = getLayoutInflater();
                view = layoutInflater.inflate(R.layout.dimound_inputing_alert, null);
                ImageView ic_close = view.findViewById(R.id.ic_close);
                EditText edt_dimound_code = view.findViewById(R.id.edt_dimound_code);
                Button btn_ok = view.findViewById(R.id.btn_ok);

                ic_close.setOnClickListener(view1 -> {
                    dialog.dismiss();
                });

                btn_ok.setOnClickListener(view12 -> {
                    String dimoundCode = edt_dimound_code.getText().toString().trim();

                    HashMap<String, String> stringStringHashMap;
                    stringStringHashMap = new HashMap<>();
                    SharedPreferences sharedPreferences = getSharedPreferences("Token", MODE_PRIVATE);
                    String mobile = sharedPreferences.getString("mobile", null);
                    String token = sharedPreferences.getString("token", null);

                    stringStringHashMap.put("mobile", mobile);
                    stringStringHashMap.put("token", token);
                    stringStringHashMap.put("gemCode", dimoundCode);

                    vollayRequest request = new vollayRequest();
                    request.requester(stringStringHashMap, MainActivity.this, "insertGem", new ServerCallback() {
                        @Override
                        public void onSuccess(JSONObject result) throws JSONException {

                            sharedPreferences.edit().putString("dimound_count", result.getString("countGem")).apply();

                            defineObjects(result.getString("countGem"));

                        }
                    });
                });

                alertDialog.setView(view);
                dialog = alertDialog.create();
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                break;
            case R.id.ic_menu:
                intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.ic_cup:
                intent = new Intent(MainActivity.this, RankingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
