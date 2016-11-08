package com.tcl.funday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.tcl.funday.support.adapter.FragmentAdapter;
import com.tcl.funday.ui.fragment.AnimalFragment;
import com.tcl.funday.ui.fragment.CartoonFragment;
import com.tcl.funday.ui.fragment.CharacterFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    private ViewPager mViewPager;

    private CharacterFragment mCharacterFragment;
    private CartoonFragment mCartoonFragment;
    private AnimalFragment mAnimalFragment;

    private int currentIndex;
    private int screenWidth;

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

    }

    private void initData() {
        mCharacterFragment = new CharacterFragment();
        mCartoonFragment = new CartoonFragment();
        mAnimalFragment = new AnimalFragment();

        mFragmentList.add(mCharacterFragment);
        mFragmentList.add(mCartoonFragment);
        mFragmentList.add(mAnimalFragment);

        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setCurrentItem(0);
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
