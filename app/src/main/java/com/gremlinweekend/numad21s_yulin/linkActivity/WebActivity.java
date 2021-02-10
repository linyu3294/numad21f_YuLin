package com.gremlinweekend.numad21s_yulin.linkActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.gremlinweekend.numad21s_yulin.R;
// SSL Error Tolerant Web View Client

public class WebActivity extends AppCompatActivity {
    private WebView web;

    // SSL Error Tolerant Web View Client
    private class SSLWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        String urlString = getIntent().getStringExtra("urlString");
        WebView web = (WebView) findViewById(R.id.view_web);

        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setBuiltInZoomControls(true);
        final Activity activity = this;
        web.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });
        web.loadUrl(urlString);
    }
}