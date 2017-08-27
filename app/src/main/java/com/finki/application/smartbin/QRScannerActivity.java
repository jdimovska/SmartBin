package com.finki.application.smartbin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class QRScannerActivity extends AppCompatActivity {
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        qrScan = new IntentIntegrator(this);


        startScanning();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {

            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {

                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    StringBuilder sb = new StringBuilder();
                    sb.append("Name: "+obj.getString("name")+"\n"+"\n");
                    sb.append("Points: "+obj.getString("points")+"\n"+"\n");
                    sb.append("Location: "+obj.getString("address")+"\n"+"\n");


                    AlertDialog.Builder builder = new AlertDialog.Builder(this);


                    builder.setMessage(sb)
                            .setTitle(R.string.congrats_title)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            finish();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void startScanning(){
        qrScan.initiateScan();
    }
}
