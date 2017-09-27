package com.finki.application.smartbin;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BaseFirmActivity extends AppCompatActivity {
    TextView tvPhone;

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

    public void callImageClick(View view) {
        tvPhone = (TextView) findViewById(R.id.tvPhone);

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(tvPhone.getText().toString()));

        startActivity(intent);

        //startActivity(intent);
    }


}
