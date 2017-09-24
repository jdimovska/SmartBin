package com.finki.application.smartbin;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.finki.application.smartbin.rest_services.AppConfig;
import com.finki.application.smartbin.rest_services.AppController;

import java.util.HashMap;
import java.util.Map;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
public class UpdateAccountActivity extends AppCompatActivity {

    private Button btnUpdate;
    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputNewPassword;
    private EditText inputOldPassword;
    private EditText inputRepeatPassword;
    private EditText inputUsername;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        setupView();
        preFillTextFields();
    }
    private void preFillTextFields() {
        session = new SessionManager(getApplicationContext());
        inputFullName.setText(session.getFullname());
        inputEmail.setText(session.getEmail());
        inputUsername.setText(session.getUsername());
    }
    private void setupView() {
        inputFullName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        inputNewPassword = (EditText) findViewById(R.id.new_password);
        inputOldPassword= (EditText) findViewById(R.id.old_password);
        inputRepeatPassword= (EditText) findViewById(R.id.repeat_password);
        inputUsername = (EditText) findViewById(R.id.username);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

    }

    public void updateAccount(View view) {

        if(inputNewPassword.getText().toString().trim().isEmpty()) {
            StringRequest strReq = new StringRequest(Method.POST,
                    AppConfig.URL_UPDATE_USER, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(),"Successfully updated!",Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", inputFullName.getText().toString().trim());
                    params.put("username", inputUsername.getText().toString().trim());
                    params.put("email", inputEmail.getText().toString().trim());

                    session.setFullName(inputFullName.getText().toString().trim());
                    session.setUsername(inputUsername.getText().toString().trim());

                    return params;
                }


            };
            AppController.getInstance().addToRequestQueue(strReq);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            finish();
            startActivity(intent);


        }


        else if (!inputNewPassword.getText().toString().trim().equals(inputRepeatPassword.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(),
                    "Please enter the same password twice!", Toast.LENGTH_LONG)
                    .show();

        } else {
            StringRequest strReq = new StringRequest(Method.POST,
                    AppConfig.URL_UPDATE_USER, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(),
                            "Successfully updated!",Toast.LENGTH_SHORT)
                            .show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", inputFullName.getText().toString().trim());
                    params.put("username", inputUsername.getText().toString().trim());
                    params.put("email", inputEmail.getText().toString().trim());
                    params.put("password", inputNewPassword.getText().toString().trim());

                    session.setFullName(inputFullName.getText().toString().trim());
                    session.setUsername(inputUsername.getText().toString().trim());
                    return params;
                }


            };
            AppController.getInstance().addToRequestQueue(strReq);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            session.setLogin(false);
            finish();
            startActivity(intent);


        }

    }
}
