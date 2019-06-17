package com.pirtail.piratilgame.Gamer.Activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.pirtail.piratilgame.Gamer.Fragment.FragRace;
import com.pirtail.piratilgame.R;

import java.util.ArrayList;

public class View_pager extends AppCompatActivity {

    ViewPager viewPager;

    ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);
        viewPager = (ViewPager) findViewById(R.id.ViewPager);
        fragments = new ArrayList<>();
        fragments.add(FragRace.newInstance("GameOne","400",R.drawable.img_bowling_game,R.drawable.green_bg));
        fragments.add(FragRace.newInstance("GameOne","400",R.drawable.img_bowling_game,R.drawable.red_bg));
        fragments.add(FragRace.newInstance("GameOne","400",R.drawable.img_bowling_game,R.drawable.red_bg));
        fragments.add(FragRace.newInstance("GameOne","400",R.drawable.img_bowling_game,R.drawable.green_bg));
        fragments.add(FragRace.newInstance("GameOne","400",R.drawable.img_bowling_game,R.drawable.red_bg));
        fragments.add(FragRace.newInstance("GameOne","400",R.drawable.img_bowling_game,R.drawable.green_bg));
        fragments.add(FragRace.newInstance("GameOne","400",R.drawable.img_bowling_game,R.drawable.red_bg));
        fragments.add(FragRace.newInstance("GameOne","400",R.drawable.img_bowling_game,R.drawable.green_bg));
        fragments.add(FragRace.newInstance("GameTwo","500",R.drawable.img_bowling_game,R.drawable.red_bg));

        FragmentManager fragmentManager = getSupportFragmentManager();
        myViewPagerAddapter myViewPagerAddapter = new myViewPagerAddapter(fragmentManager);
        viewPager.setAdapter(myViewPagerAddapter);
        myViewPagerAddapter.notifyDataSetChanged();

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
}
