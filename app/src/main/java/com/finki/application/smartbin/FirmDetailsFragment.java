package com.finki.application.smartbin;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.finki.application.smartbin.models.Firm;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FirmDetailsFragment extends Fragment {

    View view;
    FragmentActivity context;
    TextView tvName;
    TextView tvUrl;
    TextView tvPhone;
    TextView tvLocation;
    TextView tvEmail;
    ImageView image;
    Firm firm;

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_firm_details, container, false);
        setupTextFields();
        fillUpTextFields();


        tvUrl.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                loadUrl();
                return false;
            }

        });
        return view;

    }

    public void loadUrl() {
        WebViewFragment webViewFragment = new WebViewFragment();
        android.app.FragmentManager fragmentManager = context.getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle args=new Bundle();
        args.putString("url", (String) tvUrl.getText());
        webViewFragment.setArguments(args);
        fragmentTransaction.replace(R.id.fragment_firms, webViewFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    void fillUpTextFields() {
        String text = getArguments().getString("name");
        tvName.setText(text);
        tvUrl.setText(getArguments().getString("url").toString());
        tvLocation.setText(getArguments().getString("location").toString());
        tvPhone.setText(getArguments().getString("phone").toString());
        tvEmail.setText(getArguments().getString("email").toString());

    }

    void setupTextFields() {
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvUrl = (TextView) view.findViewById(R.id.tvUrl);
        tvPhone = (TextView) view.findViewById(R.id.tvPhone);
        tvLocation = (TextView) view.findViewById(R.id.tvLocation);
        tvEmail = (TextView) view.findViewById(R.id.tvEmail);
        image = (ImageView) view.findViewById(R.id.image);
    }


    @Override
    public void onAttach(Activity activity) {
        context=(FragmentActivity) activity;
        super.onAttach(activity);
    }

}

