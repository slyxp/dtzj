package com.tcl.funday.ui.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.tcl.funday.R;
import com.tcl.funday.utils.ViewUtils;

/**
 * @author Liyang Sun
 * @Description:
 * @date 2016/10/17 16:57
 * @copyright HAWK
 */

public class BaseActivity extends ActionBarActivity {
    private Toolbar mToolBar;

    public Toolbar getToolBar() {
        return mToolBar;
    }

    public void setToolBar(Toolbar mToolBar) {
        this.mToolBar = mToolBar;
    }

    public void setContentView(int layoutResId) {
        super.setContentView(layoutResId);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.themeColor));
        }
    }

    public void showMessage(CharSequence msg) {
        ViewUtils.showMessage(this, msg);
    }

    public void showMessage(int resId) {
        ViewUtils.showMessage(this, resId);
    }

    public void showSnackBar(View view, CharSequence msg) {
        ViewUtils.showSnackBar(view, msg);
    }

    public void showSnackBar(View view, int resId) {
        ViewUtils.showSnackBar(view, resId);
    }
}
