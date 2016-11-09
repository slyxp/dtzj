package com.tcl.funday.ui.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tcl.funday.support.MyToolBar;
import com.tcl.funday.R;

/**
 * @author Liyang Sun
 * @Description: "主页"页面
 * @date 2016/11/7 15:57
 * @copyright HAWK
 */

public class WebViewActivity extends BaseActivity {
    private WebView myWebView;
    private MyToolBar myToolBar;
    private static final String HOMEPAGE_URL = "http://www.dtzhuanjia.com";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.menu_homepage);

        // 设置返回按钮为白色
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.commonWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        initView();
        initData();
    }

    private void initView() {
        myWebView = (WebView) findViewById(R.id.myWebView);
        myToolBar = (MyToolBar) findViewById(R.id.toolbar);
    }

    private void initData() {
        myToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebChromeClient(new WebChromeClient());
        myWebView.loadUrl(HOMEPAGE_URL);
    }

}
