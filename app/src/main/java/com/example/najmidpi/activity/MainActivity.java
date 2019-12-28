package com.example.najmidpi.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.najmidpi.R;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {
    Button btn_signin, btn_signup;
    EditText enter_eamil , enter_pass;
    public static final String MY_PREFS_NAME = "MyPrefsFile";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sp.edit();

//        boolean hasUser = sp.getBoolean("hasUser", false);
//
//        if(!hasUser){
//            // code
//            Intent intent=new Intent(this,HomeActivity.class);
//            startActivity(intent);
//            editor.putBoolean("hasUser", true);
//            editor.apply();
//        }



        btn_signin = findViewById(R.id.main_btn_signin);
        btn_signup = findViewById(R.id.main_btn_signup);
        enter_eamil = findViewById(R.id.main_et);
        enter_pass=findViewById(R.id.main_pass);


        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String mainPass=enter_pass.getText().toString().trim();
                String mainMail=enter_eamil.getText().toString().trim();

                if(isValidInput(mainMail , mainPass)){
                    Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                    finish();
                }


//                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                startActivity(intent);

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Activity_Register.class);
                startActivity(intent);
            }
        });
    }



    private boolean isValidInput(String mail ,String pass){

        //get data of memory
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String emails = prefs.getString("EMAIL", "");
        String pass1 = prefs.getString("PASS", "");

        if(isEmpty(mail))
        {
            return false;
        }
        if(isEmpty(pass))
        {
            return false;
        }
        if (!mail.equals(emails))
        {
            return false;
        }
        if (!pass.equals(pass1))
        {
            return false;
        }
        return true;
    }
}
