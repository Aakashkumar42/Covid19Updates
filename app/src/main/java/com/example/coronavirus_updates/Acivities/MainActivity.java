package com.example.coronavirus_updates.Acivities;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.coronavirus_updates.R;


public class MainActivity extends AppCompatActivity {

    WebView webView;

    ProgressBar progressBarWeb;
    ProgressDialog progressDialogWeb;
     Button backBtn;
     Button forwordBtn;
    RelativeLayout relativeLayout;
    private String webUrl = "https://www.worldometers.info/coronavirus/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        backBtn=(Button) findViewById(R.id.backButton);
        forwordBtn=(Button) findViewById(R.id.forwordButton);

        progressDialogWeb = new ProgressDialog(this);
        progressDialogWeb.setMessage("Loading Please Wait");
        progressBarWeb = (ProgressBar) findViewById(R.id.progressBar);
        webView = (WebView) findViewById(R.id.myWebView);

        webView.loadUrl(webUrl);



        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoBack()){
                    webView.goBack();
                }
            }
        });

        forwordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoForward()){
                    webView.goForward();
                }
            }
        });


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

    public void checkConnection(){
        ConnectivityManager connectivityManager=(ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetwork=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);


        if (wifi.isConnected())
        {
            webView.loadUrl(webUrl);
            webView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
        }

        else if(mobileNetwork.isConnected())
        {
            webView.loadUrl(webUrl);
            webView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
        }



    }



}