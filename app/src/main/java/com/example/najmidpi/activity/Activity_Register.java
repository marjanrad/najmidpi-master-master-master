package com.example.najmidpi.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.najmidpi.R;

import static android.text.TextUtils.isEmpty;

public class Activity_Register extends AppCompatActivity {

    Button btnsave ;
    EditText ETemail , ETphone , ETpass , ETretryPass ;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register);

        init();


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail=ETemail.getText().toString().trim();
                String phone=ETphone.getText().toString().trim();
                String pass=ETpass.getText().toString().trim();
                String rePass=ETretryPass.getText().toString().trim();


                if(isValidInput(mail , phone , pass , rePass)){
                    //save data in memory
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("EMAIL", mail);
                    editor.putString("PHONE", phone);
                    editor.putString("PASS", pass);
                    editor.apply();

                    Toast.makeText(Activity_Register.this, "save in memory", Toast.LENGTH_SHORT).show();


                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }


            }
        });


    }

    private boolean isValidInput(String mail, String phone ,String pass , String repass) {
        if (mail.lastIndexOf("@")<=0 || !mail.contains(".") ||mail.lastIndexOf(".")<mail.lastIndexOf("@")|| mail.split("@").length>2){
            ETemail.requestFocus();
            Toast.makeText(this, "ایمیل را وارد نمایید", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!phone.isEmpty()&& phone.length() !=11 || !phone.startsWith("09") || phone.isEmpty()){
            ETphone.requestFocus();
            Toast.makeText(this, "موبایل را وارد نمایید", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(isEmpty(pass))
        {
            Toast.makeText(this, "پسورد را وارد نمایید", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!repass.equals(pass))
        {
            Toast.makeText(this, "پسورد برابر نیست", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void init() {
        btnsave=findViewById(R.id.register_btn);
        ETemail=findViewById(R.id.register_mail);
        ETphone=findViewById(R.id.register_phone);
        ETpass=findViewById(R.id.register_pass);
        ETretryPass=findViewById(R.id.register_retrypass);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}