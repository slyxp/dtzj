package com.tcl.funday.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tcl.funday.R;

/**
 * @author Liyang Sun
 * @Description:
 * @date 2016/11/7 15:57
 * @copyright HAWK
 */

public class WebViewActivity extends BaseActivity {
    private WebView mMyWebView;
    private static final String HOMEPAGE_URL = "http://www.baidu.com";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webview);
        initView();
        initData();
    }

    private void initView() {
        mMyWebView = (WebView) findViewById(R.id.myWebView);
    }

    private void initData() {
        mMyWebView.getSettings().setJavaScriptEnabled(true);
        mMyWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings webSettings = mMyWebView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        mMyWebView.setWebViewClient(new WebViewClient());
        mMyWebView.setWebChromeClient(new WebChromeClient());
        mMyWebView.loadUrl(HOMEPAGE_URL);
    }

}
