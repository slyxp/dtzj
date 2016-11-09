package com.tcl.funday.ui.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tcl.funday.support.MyToolBar;
import com.tcl.funday.R;

/**
 * @author Liyang Sun
 * @Description: "关于"页面
 * @date 2016/11/9 10:57
 * @copyright HAWK
 */

public class AboutActivity extends BaseActivity {
    private MyToolBar myToolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.about);

        // 设置返回按钮为白色
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.commonWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        initView();
        initData();
    }

    private void initView() {
        myToolBar = (MyToolBar) findViewById(R.id.toolbar);
    }

    private void initData() {
        myToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
