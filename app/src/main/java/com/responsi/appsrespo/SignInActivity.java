package com.responsi.appsrespo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.responsi.appsrespo.sharedpreferences.Preferencs;

public class SignInActivity extends AppCompatActivity {

    private EditText Email,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Email=findViewById(R.id.et_emaillog);
        Password =findViewById(R.id.EdPassword);
        Password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia();
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.btn_Signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                razia();
            }
        });
        findViewById(R.id.tv_Signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),SignUpActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferencs.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(),HomeActivity.class));
            finish();
        }
    }

    private void razia(){
        Email.setError(null);
        Password.setError(null);
        View fokus = null;
        boolean cancel = false;

        String user = Email.getText().toString();
        String password = Password.getText().toString();

        if (TextUtils.isEmpty(user)){
            Email.setError("This field is required");
            fokus = Email;
            cancel = true;
        }else if(!cekUser(user)){
            Email.setError("This Username is not found");
            fokus = Email;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)){
            Password.setError("This field is required");
            fokus = Password;
            cancel = true;
        }else if (!cekPassword(password)){
            Password.setError("This password is incorrect");
            fokus = Password;
            cancel = true;
        }

        if (cancel) fokus.requestFocus();
        else masuk();
    }

    private void masuk(){
        Preferencs.setLoggedInUser(getBaseContext(),Preferencs.getRegisteredUser(getBaseContext()));
        Preferencs.setLoggedInStatus(getBaseContext(),true);
        startActivity(new Intent(getBaseContext(),HomeActivity.class));finish();
    }

    private boolean cekPassword(String password){
        return password.equals(Preferencs.getRegisteredPass(getBaseContext()));
    }

    private boolean cekUser(String user){
        return user.equals(Preferencs.getRegisteredUser(getBaseContext()));
    }




    }
