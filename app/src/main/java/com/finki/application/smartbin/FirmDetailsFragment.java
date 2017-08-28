package com.finki.application.smartbin;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.finki.application.smartbin.models.Firm;

import java.util.ArrayList;

public class FirmDetailsFragment extends Fragment {

    View view;
    FragmentActivity context;
    TextView tvName;
    TextView tvUrl;
    TextView tvPhone;
    TextView tvLocation;
    TextView tvEmail;
    Firm firm;

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_firm_details, container, false);
        setupTextFields();
        fillUpTextFields();
        return view;

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
    }


    @Override
    public void onAttach(Activity activity) {
        context=(FragmentActivity) activity;
        super.onAttach(activity);
    }

}

