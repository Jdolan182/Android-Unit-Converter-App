package mko.cs.stir.ac.uk.jdo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

class PagerAdapter extends FragmentStatePagerAdapter {
    private final int mNumOfTabs;
    public PagerAdapter(FragmentManager fragmentManager, int numOfTabs) {
        super(fragmentManager);this.mNumOfTabs = numOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new
                    Page1Fragment();
            case 1: return new Page2Fragment();
            default: return null;
        }
    }
    @Override
    public int getCount() {return mNumOfTabs;
    }
}