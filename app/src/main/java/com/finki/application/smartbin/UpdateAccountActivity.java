package com.finki.application.smartbin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        //TODO: call api service for updating account
        if (!inputNewPassword.getText().toString().trim().equals(inputRepeatPassword.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(),
                    "Please enter the same password twice!", Toast.LENGTH_LONG)
                    .show();

        } else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            finish();
            startActivity(intent);
            session.setLogin(false);
        }

    }
}
