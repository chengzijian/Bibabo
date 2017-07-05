package com.bibabo.base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bibabo.R;
import com.bibabo.framework.fragmentation.SupportActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * Created by zijian.cheng on 2017/5/08.
 */

public abstract class BaseActivity extends SupportActivity {

    protected Unbinder unbinder;

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());
        unbinder = ButterKnife.bind(this);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        if(needWebViewService()){
            initWebView();
        }
    }

    /**
     * 该activity是否需要webview服务 否则就不进行初始化
     * @return
     */
    protected boolean needWebViewService(){
        return false;
    }

    private void initWebView() {
        mWebView = new WebView(this);
        //下面三行设置主要是为了待webView成功加载html网页之后，我们能够通过webView获取到具体的html字符串
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:window.local_obj.showSource('<body>'+"
                        + "document.getElementsByTagName('body')[0].innerHTML+'</body>');");
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

        });
    }

    protected void loadLocalUrl(String url, InJavaScriptLocalObj obj){
        if(mWebView == null){
            initWebView();
        }
        mWebView.removeJavascriptInterface("local_obj");
        mWebView.addJavascriptInterface(obj, "local_obj");
        mWebView.loadUrl(url);
    }

    protected abstract class InJavaScriptLocalObj {
        @JavascriptInterface
        public abstract void showSource(String html);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setNavigationIconColor(Color.WHITE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }

    protected abstract int provideContentViewId();

    protected void setToolBarTitle(CharSequence title) {
        if (toolbar != null) {
            toolbar.setTitle(title);
        }
    }

    protected void setDisplayShowTitleEnabled(boolean enabled) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(enabled);
        }
    }

    protected void setNavigationIconColor(@ColorInt int color) {
        if (toolbar != null) {
            Drawable icon = toolbar.getNavigationIcon();
            if (icon != null) {
                DrawableCompat.setTint(icon, color);
            }
        }
    }
}
