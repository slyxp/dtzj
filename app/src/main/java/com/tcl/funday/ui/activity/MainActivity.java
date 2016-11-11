package com.tcl.funday.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.tcl.funday.MyApplication;
import com.tcl.funday.R;
import com.tcl.funday.support.MyToolBar;
import com.tcl.funday.support.adapter.FragmentAdapter;
import com.tcl.funday.utils.CommonUtils;
import com.tcl.funday.utils.Logger;

import shanyao.tabpagerindictor.TabPageIndicator;

public class MainActivity extends BaseActivity {

    private FragmentAdapter mFragmentAdapter;
    private ViewPager mViewPager;
    private MyToolBar myToolBar;

    private long exitTime = 0;

    private TabPageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        Logger.d("SLY", "hahaha");
        Logger.e("SLY", "123456");
    }

    private void initView() {
        getSupportActionBar().setTitle(R.string.app_name);

        myToolBar = (MyToolBar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.main_view_pager);
        mIndicator = (TabPageIndicator) findViewById(R.id.indicator);

        // 通过调整Toolbar高度来设置通知栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            myToolBar.getLayoutParams().height = ViewUtils.getStatusBarHeight() + CommonUtils.dip2px(MyApplication.getContext(), 50);
//            myToolBar.setPadding(myToolBar.getPaddingLeft(), ViewUtils.getStatusBarHeight(),
//                    myToolBar.getPaddingRight(), myToolBar.getPaddingBottom());
//        }

        setSupportActionBar(myToolBar);
    }

    private void initData() {
//        List<Fragment> fragments = new ArrayList<Fragment>();

//        CharacterFragment characterFragment = new CharacterFragment();
//        CartoonFragment cartoonFragment = new CartoonFragment();
//        AnimalFragment animalFragment = new AnimalFragment();
//        FestivalFragment festivalFragment = new FestivalFragment();
//        IntegrateFragment integrateFragment = new IntegrateFragment();
//
//        fragments.add(characterFragment);
//        fragments.add(cartoonFragment);
//        fragments.add(animalFragment);
//        fragments.add(festivalFragment);
//        fragments.add(integrateFragment);

        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setCurrentItem(0);

        mIndicator.setViewPager(mViewPager);
        mIndicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_NOWEIGHT_EXPAND_NOSAME);
        mIndicator.setDividerColor(getResources().getColor(R.color.comm_water));
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
            case R.id.menuSearch:
                Intent intentSearch = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intentSearch);
                break;

            case R.id.menuAbout:
//                showSnackBar(mViewPager, "Funday");
                Intent intentAbout = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intentAbout);
                break;

            case R.id.menuHomePage:
                Intent intentHomePage = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intentHomePage);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 双击返回键退出程序
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                showMessage(getString(R.string.toast_press_again_to_exit));
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
