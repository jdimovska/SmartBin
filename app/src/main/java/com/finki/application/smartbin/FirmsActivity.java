package com.finki.application.smartbin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.finki.application.smartbin.models.Firm;
import com.finki.application.smartbin.rest_services.AppConfig;
import com.finki.application.smartbin.rest_services.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firms);

        ArrayList<Firm> firmsList=new ArrayList<>();
        firmsList.add(new Firm("1firma","nfedckjcd","fdjknv","nfrejkne","fejk"));
        firmsList.add(new Firm("2fjrkdna","nfedckjcd","fdjknv","nfrejkne","fejk"));
        CustomFirmsAdapter adapter=new CustomFirmsAdapter(this,firmsList);
        ListView viewList=(ListView) findViewById(R.id.list_firms);
        viewList.setAdapter(adapter);
    }




}
