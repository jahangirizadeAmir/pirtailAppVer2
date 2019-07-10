package com.pirtail.piratilgame.Gamer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.pirtail.piratilgame.R;
import com.pirtail.piratilgame.frag1;
import com.pirtail.piratilgame.frg2;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ViewPager viewPager=findViewById(R.id.view);
       adapter adapter=new adapter(getSupportFragmentManager());
       viewPager.setAdapter(adapter);
    }
    public class adapter  extends FragmentStatePagerAdapter {
        public adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position==0)
            {
                return   new frag1();
            }
            if (position==1)
            {
                return  new frg2();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
