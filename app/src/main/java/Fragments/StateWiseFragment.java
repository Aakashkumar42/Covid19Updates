package Fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.coronavirus_updates.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class StateWiseFragment extends Fragment {


      private WebView webView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_state_wise, container, false);


        webView=(WebView) v.findViewById(R.id.myWebViewstate);


        webView.loadUrl("https://www.worldometers.info/coronavirus/");

        webView.setWebViewClient( new WebViewClient());

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        return v;


    }

}
