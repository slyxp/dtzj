package com.tcl.funday;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author Liyang Sun
 * @Description:
 * @date 2016/10/17 17:00
 * @copyright HAWK
 */

public class MyToolBar extends Toolbar {

    private long lastClickTime = 0L;

    public MyToolBar(Context context) {
        super(context);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
