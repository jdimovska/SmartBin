package com.finki.application.smartbin;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Marina on 24.9.2017.
 */

public class WebViewFragment extends Fragment {
    View view;
    FragmentActivity context;
    WebView webView;
    public void onActivityCreated(Bundle bundle)
    {

        super.onActivityCreated(bundle);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_web_view, container, false);
        webView = (WebView) view.findViewById(R.id.webView);
        String url = getArguments().getString("url");
        webView.loadUrl(url);
        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        context=(FragmentActivity) activity;
        super.onAttach(activity);
    }
}
