package com.pirtail.piratilgame;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

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
