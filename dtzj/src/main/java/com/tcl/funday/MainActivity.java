package com.tcl.funday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private TextView mTabCharacter, mTabCartoon, mTabAnimal;
    private ImageView mTabLineIv;

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
        initTabLineWidth();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.main_view_pager);

        mTabCharacter = (TextView) findViewById(R.id.navigation_cartoon_txt);
        mTabCartoon = (TextView) findViewById(R.id.navigation_cartoon_txt);
        mTabAnimal = (TextView) findViewById(R.id.navigation_animal_txt);

        mTabLineIv = (ImageView) findViewById(R.id.navigation_tab_indicator);
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
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mTabLineIv.getLayoutParams();
                if (currentIndex == 0 && position == 0) {
                    layoutParams.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 3) + currentIndex * (screenWidth / 3));
                } else if (currentIndex == 1 && position == 0) {
                    layoutParams.leftMargin = (int) (-(1 - positionOffset) * (screenWidth * 1.0 / 3) + currentIndex * (screenWidth / 3));
                } else if (currentIndex == 1 && position == 1) {
                    layoutParams.leftMargin = (int) (-(1 - positionOffset) * (screenWidth * 1.0 / 3) + currentIndex * (screenWidth / 3));
                }

                mTabLineIv.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        mTabCharacter.setTextColor(getResources().getColor(R.color.colorAccent));
                        break;
                    case 1:
                        mTabCartoon.setTextColor(getResources().getColor(R.color.colorAccent));
                        break;
                    case 2:
                        mTabAnimal.setTextColor(getResources().getColor(R.color.colorAccent));
                        break;

                    default:
                        break;
                }

                currentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

    private void initTabLineWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mTabLineIv.getLayoutParams();
        layoutParams.width = screenWidth / 3;
        mTabLineIv.setLayoutParams(layoutParams);
    }

    private void resetTextView() {
        mTabCharacter.setTextColor(getResources().getColor(R.color.themeColor));
        mTabCartoon.setTextColor(getResources().getColor(R.color.themeColor));
        mTabAnimal.setTextColor(getResources().getColor(R.color.themeColor));
    }
}
