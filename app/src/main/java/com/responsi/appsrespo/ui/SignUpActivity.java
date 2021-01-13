package com.responsi.appsrespo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.responsi.appsrespo.R;
import com.responsi.appsrespo.sharedpreferences.Preferencs;

public class SignUpActivity extends AppCompatActivity {


    private EditText emailup,passwordup,confirmpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        emailup =findViewById(R.id.et_emailUp);
        passwordup =findViewById(R.id.EdPasswordUp);
        confirmpass =findViewById(R.id.et_ConPass);

        final TextView sigin = findViewById(R.id.tv_signin);
        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(in);
            }
        });

        confirmpass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    signup();
                    return true;
                }
                return false;
            }
        });
        findViewById(R.id.btn_SignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    private void signup(){
        emailup.setError(null);
        passwordup.setError(null);
        confirmpass.setError(null);
        View fokus = null;
        boolean cancel = false;

        /* Mengambil text dari Form User, Password, Repassword dengan variable baru bertipe String*/
        String repassword = confirmpass.getText().toString();
        String user = emailup.getText().toString();
        String password = passwordup.getText().toString();

        if (TextUtils.isEmpty(user)){
            emailup.setError("This field is required");
            fokus = emailup;
            cancel = true;

        }else if(cekUser(user)){
            emailup.setError("This Username is already exist");
            fokus = emailup;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)){
            passwordup.setError("This field is required");
            fokus = passwordup;
            cancel = true;
        }else if (!cekPassword(password,repassword)){
            confirmpass.setError("This password is incorrect");
            fokus = confirmpass;
            cancel = true;
        }

        if (cancel){
            fokus.requestFocus();
        }else{
            Preferencs.setRegisteredUser(getBaseContext(),user);
            Preferencs.setRegisteredPass(getBaseContext(),password);
            finish();
        }
    }

    private boolean cekPassword(String password, String repassword){
        return password.equals(repassword);
    }

    private boolean cekUser(String user){
        return user.equals(Preferencs.getRegisteredUser(getBaseContext()));
    }

 }
