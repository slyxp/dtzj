package com.tcl.funday.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.tcl.funday.MyApplication;
import com.tcl.funday.R;
import com.tcl.funday.support.adapter.FragmentAdapter;
import com.tcl.funday.utils.CommonUtils;

import shanyao.tabpagerindictor.TabPageIndicator;

public class MainActivity extends BaseActivity {

    private FragmentAdapter mFragmentAdapter;
    private ViewPager mViewPager;

    private TabPageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.app_name);

        initView();
        initData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.main_view_pager);
        mIndicator = (TabPageIndicator) findViewById(R.id.indicator);
    }

    private void initData() {
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mFragmentAdapter);
        mIndicator.setViewPager(mViewPager);
        mIndicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_NOWEIGHT_NOEXPAND_NOSAME);
        mIndicator.setDividerColor(getResources().getColor(R.color.themeColor));
        mIndicator.setDividerPadding(CommonUtils.dip2px(MyApplication.getContext(), 10));
        mIndicator.setIndicatorColor(getResources().getColor(R.color.themeColor));
        mIndicator.setTextColor(getResources().getColor(R.color.navigation_tab_txt_color));
        mIndicator.setTextColorSelected(getResources().getColor(R.color.themeColor));
        mIndicator.setTextSize(CommonUtils.sp2px(MyApplication.getContext(), 16));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAbout:
                showSnackBar(mViewPager, "Funday");
                break;

            case R.id.menuHomePage:
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
