package com.tcl.funday;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
