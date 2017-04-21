package com.freeletics.dilyana.freeletics.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.freeletics.dilyana.freeletics.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {


    private WebView web;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_video, container, false);


        web = (WebView) root.findViewById(R.id.web_view);
        web.setWebViewClient(new MyBrowser());
        web.getSettings().setJavaScriptEnabled(true);

        if(getArguments() != null){

            Bundle bundle = getArguments();

            if(bundle.getString("url") != null) {
                String videoUrl = bundle.getString("url");
                web.loadUrl(videoUrl);
            }
        }
        return  root;
    }

    private class MyBrowser extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
