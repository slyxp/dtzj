package com.tcl.funday.support.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

}
