package com.example.najmidpi.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.najmidpi.R;
import com.example.najmidpi.parent.ParentActivity;

import static android.text.TextUtils.isEmpty;

public class SignInActivity extends ParentActivity {

    Button btn_signin, btn_signup;
    EditText enter_eamil, enter_pass;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        if (getUserHelper().hasUser()) {
            openActivity(SplashActivity.class, true);
        }

        init();

        login();
        register();

    }

    private void register() {
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Activity_Register.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mainPass = enter_pass.getText().toString().trim();
                String mainMail = enter_eamil.getText().toString().trim();

                if (isValidInput(mainMail, mainPass)) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        });
    }

    private void init() {
        btn_signin = findViewById(R.id.main_btn_signin);
        btn_signup = findViewById(R.id.main_btn_signup);
        enter_eamil = findViewById(R.id.main_et);
        enter_pass = findViewById(R.id.main_pass);
    }

    private boolean isValidInput(String mail, String pass) {

        //get data of memory
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String emails = prefs.getString("EMAIL", "");
        String pass1 = prefs.getString("PASS", "");

        if (isEmpty(mail)) {
            return false;
        }
        if (isEmpty(pass)) {
            return false;
        }
        if (!mail.equals(emails)) {
            return false;
        }
        if (!pass.equals(pass1)) {
            return false;
        }
        return true;
    }
}
