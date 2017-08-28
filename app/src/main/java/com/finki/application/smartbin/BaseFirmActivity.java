package com.finki.application.smartbin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseFirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_firm);
        FirmsFragment firstFragment = new FirmsFragment();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_firms, firstFragment);
        fragmentTransaction.commit();

    }


}
