package com.tcl.funday.support;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * @author Liyang Sun
 * @Description:
 * @date 2016/11/7 15:53
 * @copyright HAWK
 */

public class MyWebView extends WebView {
    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
