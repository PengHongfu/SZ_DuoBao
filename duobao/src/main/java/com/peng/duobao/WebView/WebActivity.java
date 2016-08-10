package com.peng.duobao.WebView;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.peng.duobao.R;

/**
 * Created by Peng on 2016/8/7.
 */
public class WebActivity extends FragmentActivity {
    private WebView webview;
    private boolean isFirst = true;
    private boolean isInIndex = true;
    private long first, second;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        webview = (WebView) findViewById(R.id.webview);

        //加载需要显示的网页
        webview.loadUrl("http://indiana.smarter-wireless.net//");

        initview();
    }

    private void initview() {
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

       /* settings.setBuiltInZoomControls(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setAllowFileAccess(true);
        settings.setSaveFormData(false);
        webview.setWebChromeClient(new MyWebChromeClient());
        settings.setJavaScriptCanOpenWindowsAutomatically(true);*/

        webview.setWebViewClient(new MyWebViewClient());


    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (isInIndex) {
                onDoubleBackExist(keyCode, event);
            } else if (webview != null && webview.canGoBack()) {
                webview.goBack();
                return false;
            }
        }
        return true;
    }



        //Web视图
        private class MyWebViewClient extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        }
    /**
     * 双击退出
     */
    private void onDoubleBackExist(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isFirst) {
                first = System.currentTimeMillis();
                Toast.makeText(this, "Click again to exit.", Toast.LENGTH_SHORT).show();
                isFirst = false;
            } else {
                isFirst = true;
                second = System.currentTimeMillis();
                if (second - first < 2000) {
                    finish();
                }
            }
        }
    }
}
