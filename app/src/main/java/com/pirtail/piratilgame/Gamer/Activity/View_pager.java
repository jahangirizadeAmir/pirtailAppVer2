package com.pirtail.piratilgame.Gamer.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.pirtail.piratilgame.Gamer.Fragment.FragRace;
import com.pirtail.piratilgame.R;

import java.util.ArrayList;
import java.util.HashMap;

import com.pirtail.piratilgame.Class.vollayRequest;
import com.pirtail.piratilgame.ServerCallback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class View_pager extends AppCompatActivity {

    ViewPager viewPager;

    ArrayList<FragRace> fragments;
    HashMap<String, String> stringStringHashMap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);
        viewPager = findViewById(R.id.ViewPager);
        fragments = new ArrayList<>();


        stringStringHashMap = new HashMap<>();


        SharedPreferences sharedPreferences = getSharedPreferences("Token", MODE_PRIVATE);
        String mobile = sharedPreferences.getString("mobile", null);
        String token = sharedPreferences.getString("token", null);

        stringStringHashMap.put("mobile", mobile);
        stringStringHashMap.put("token", token);

        vollayRequest vollayRequest = new vollayRequest();
        vollayRequest.requester(stringStringHashMap, this, "challengeList", new ServerCallback() {
            @Override
            public void onSuccess(JSONObject result) throws JSONException {
                Log.i("ok", String.valueOf(result));
                JSONArray jsonArray = result.getJSONArray("challenge");
                for(int i = 0;i<jsonArray.length();i++) {
                    if (!jsonArray.getJSONObject(i).getBoolean("userInSide")) {
                        int back;

                        if(i == 0){
                            back = R.drawable.red_bg;
                        }if(i%2==0){
                            back = R.drawable.red_bg;
                        }else{
                            back = R.drawable.green_bg;
                        }

                        Bundle bundle = new Bundle();
                        bundle.putString("name", jsonArray.getJSONObject(i).getString("name"));
                        bundle.putString("price", jsonArray.getJSONObject(i).getString("price"));
                        bundle.putString("desc", jsonArray.getJSONObject(i).getString("desc"));
                        bundle.putString("address", jsonArray.getJSONObject(i).getString("address"));
                        bundle.putString("time", jsonArray.getJSONObject(i).getString("time"));
                        bundle.putString("id", jsonArray.getJSONObject(i).getString("id"));
                        bundle.putString("img", "http://piratil.com/game/img/" + jsonArray.getJSONObject(i).getString("img"));

                        bundle.putInt("back", back);
                        fragments.add(FragRace.newInstance(bundle));
                    }
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                myViewPagerAddapter myViewPagerAddapter = new myViewPagerAddapter(fragmentManager);
                viewPager.setAdapter(myViewPagerAddapter);
            }
        });

    }

    public class myViewPagerAddapter extends FragmentPagerAdapter{

        public myViewPagerAddapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
