package com.tcl.funday.ui.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.tcl.funday.R;
import com.tcl.funday.support.MyToolBar;

/**
 * @author Liyang Sun
 * @Description:
 * @date 2016/11/11 16:35
 * @copyright HAWK
 */

public class SearchActivity extends BaseActivity {

    private EditText mSearchEt;
    private MyToolBar myToolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        initView();
        initData();
    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        // 设置返回按钮为白色
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.commonWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        myToolBar = (MyToolBar) findViewById(R.id.toolbar);
        mSearchEt = (EditText) findViewById(R.id.search_et);
    }

    private void initData() {
        myToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        mSearchEt.requestFocus();
    }
}
