package com.tcl.funday.support.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tcl.funday.R;
import com.tcl.funday.ui.fragment.AnimalFragment;
import com.tcl.funday.ui.fragment.CartoonFragment;
import com.tcl.funday.ui.fragment.CharacterFragment;
import com.tcl.funday.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liyang Sun
 * @Description:
 * @date 2016/11/7 19:32
 * @copyright HAWK
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragmentList = new ArrayList<Fragment>();
    String[] mTitles;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        mTitles = CommonUtils.getStringArray(R.array.navigation_titles);
    }

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
//        return mFragmentList.get(position);
        return createFragment(position);
    }

    @Override
    public int getCount() {
//        return mFragmentList.size();
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    private Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new CharacterFragment();
                break;
            case 1:
                fragment = new CartoonFragment();
                break;
            case 2:
                fragment = new AnimalFragment();
                break;
        }
        return fragment;
    }

}
