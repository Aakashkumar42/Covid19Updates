package com.example.officail_ignou;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    WebView webView;

    ProgressBar progressBarWeb;
    ProgressDialog progressDialogWeb;
    private String webUrl = "http://ignou.ac.in/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressDialogWeb = new ProgressDialog(this);
        progressDialogWeb.setMessage("Loading Please Wait");
        progressBarWeb = (ProgressBar) findViewById(R.id.progressBar);
        webView = (WebView) findViewById(R.id.myWebView);
        webView.loadUrl(webUrl);



        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);

       

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


      webView.setDownloadListener(new DownloadListener() {
          @Override
          public void onDownloadStart(String Url, String userAgent, String contentDisposition, String nimetype, long contentlenght) {

              DownloadManager.Request myRequest=new DownloadManager.Request(Uri.parse(Url));

              myRequest.allowScanningByMediaScanner();
              myRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

              DownloadManager myManager=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
              myManager.enqueue(myRequest);

              Toast.makeText(MainActivity.this, "Your File are Downloading...", Toast.LENGTH_SHORT).show();

          }
      });
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                progressBarWeb.setVisibility(View.VISIBLE);
                progressBarWeb.setProgress(newProgress);
                setTitle("Loading...");
                progressDialogWeb.show();
                if (newProgress == 100) {

                    progressBarWeb.setVisibility(View.GONE);
                    setTitle(view.getTitle());
                    progressDialogWeb.dismiss();
                }
                 super.onProgressChanged(view, newProgress);
                                       }
                                   }

        );

    }

    @Override
    public void onBackPressed() {

        if (webView.canGoBack()) {
            webView.goBack();

        } else
            super.onBackPressed();
    }



}