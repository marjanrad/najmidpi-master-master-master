package com.example.najmidpi.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.najmidpi.R;
import com.example.najmidpi.helper.UserHelper;
import com.example.najmidpi.parent.ParentActivity;
import com.google.android.material.textfield.TextInputLayout;

import static android.text.TextUtils.isEmpty;

public class Activity_Register extends ParentActivity {

    Button btnsave ;
    EditText ETemail , ETphone , ETpass , ETretryPass ;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    TextInputLayout input_email , input_phone , input_pass , input_repass;

//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        init();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail=input_email.getEditText().getText().toString().trim();
                String phone=input_phone.getEditText().getText().toString().trim();
                String pass=input_pass.getEditText().getText().toString().trim();
                String rePass=input_repass.getEditText().getText().toString().trim();


                if(isValidInput(mail , phone , pass , rePass)){
                    //save data in memory
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                    UserHelper userHelper = getUserHelper();
                    userHelper.setEmail(mail);
                    userHelper.setPhone(phone);
                    userHelper.setUsername("");
                    userHelper.setPassword(pass);
                    userHelper.setHasUser(true);

                    Toast.makeText(Activity_Register.this, "save in memory", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("EMAIL",mail);
                    editor.putString("PASS",pass);
                    editor.putString("CALL",phone);
                    editor.commit();


                    openActivity(SignInActivity.class, false);
                }
            }
        });
    }

    private boolean isValidInput(String mail, String phone ,String pass , String repass) {
        if (mail.lastIndexOf("@")<=0 || !mail.contains(".") ||mail.lastIndexOf(".")<mail.lastIndexOf("@")|| mail.split("@").length>2){
            input_pass.requestFocus();
            Toast.makeText(this, "ایمیل را وارد نمایید", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!phone.isEmpty()&& phone.length() !=11 || !phone.startsWith("09") || phone.isEmpty()){
            input_phone.requestFocus();
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
//        ETemail=findViewById(R.id.register_mail);
//        ETphone=findViewById(R.id.register_phone);
//        ETpass=findViewById(R.id.register_pass);
//        ETretryPass=findViewById(R.id.register_retrypass);

        input_email=findViewById(R.id.register_mail);
        input_phone=findViewById(R.id.register_phone);
        input_pass=findViewById(R.id.register_pass);
        input_repass=findViewById(R.id.register_retrypass);

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