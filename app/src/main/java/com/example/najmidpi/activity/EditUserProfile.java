package com.example.najmidpi.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.najmidpi.R;
import com.example.najmidpi.activity.AboutUs;
import com.example.najmidpi.activity.DoctorProfile;
import com.example.najmidpi.activity.HistoryActivity;
import com.example.najmidpi.activity.HomeActivity;
import com.example.najmidpi.activity.ShowUserProfile;

import static android.text.TextUtils.isEmpty;

public class EditUserProfile extends AppCompatActivity {

    TextView menu_user, menu_doctor, menu_history, menu_aboutus, menu_contactus, menu_home;
    private DrawerLayout mDrawerLayout;
    RadioGroup radioGroup;
    RadioButton radioButton;
    public static final String MY_PREFS_NAME = "USER_INFORMATIONS";
    EditText name, family_name, age, tall, patient_history;


    Button btnsave;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconmenu);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        init();
        getvalue();
        menu();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set radioButton
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String radio = radioButton.getText().toString();


                String first_name = name.getText().toString().trim();
                String last_name = family_name.getText().toString().trim();
                String age1 = age.getText().toString().trim();
                String tall1 = tall.getText().toString().trim();
                String patient = patient_history.getText().toString().trim();

                //save data in memory
                if (isValidInput(first_name, last_name, age1, tall1, patient)) {

                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("NAME", first_name);
                    editor.putString("FAMILY", last_name);
                    editor.putString("AGE", age1);
                    editor.putString("TALL", tall1);
                    editor.putString("PATIENT", patient);
                    editor.putString("SEX", radio);

                    editor.commit();
                }

                Intent intent = new Intent(getApplicationContext(), ShowUserProfile.class);
                startActivity(intent);
            }
        });

    }

    //get data from memory and set in editText
    private void getvalue() {

        //get
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String first_name = prefs.getString("NAME", "");
        String last_name = prefs.getString("FAMILY", "");
        String age1 = prefs.getString("AGE", "");
        String tall1 = prefs.getString("TALL", "");
        String patienthistory = prefs.getString("PATIENT", "");
        String sex = prefs.getString("SEX", "");

        //set
        name.setText(first_name);
        family_name.setText(last_name);
        age.setText(age1);
        tall.setText(tall1);
        patient_history.setText(patienthistory);


    }

    private void init() {
        btnsave = findViewById(R.id.edit_user_profile_btnsave);
        menu_user = findViewById(R.id.menu_user);
        menu_doctor = findViewById(R.id.menu_doctor);
        menu_history = findViewById(R.id.menu_history);
        menu_aboutus = findViewById(R.id.menu_aboutus);
        menu_contactus = findViewById(R.id.menu_contactus);
        mDrawerLayout = findViewById(R.id.edit_user_drawer);
        menu_home = findViewById(R.id.menu_home);
        radioGroup = findViewById(R.id.edit_user_profile_radioGroup);
        name = findViewById(R.id.edit_user_profile_name);
        family_name = findViewById(R.id.edit_user_profile_family);
        age = findViewById(R.id.edit_user_profile_age);
        tall = findViewById(R.id.edit_user_profile_tall);
        patient_history = findViewById(R.id.edit_user_profile_patient_history);

    }

    //check edit text in not null
    private boolean isValidInput(String first_name, String last_name, String age1, String tall1, String patient) {
        if (isEmpty(first_name)) {
            return false;
        }
        if (isEmpty(last_name)) {
            return false;
        }
        if (isEmpty(age1)) {
            return false;
        }
        if (isEmpty(tall1)) {
            return false;
        }
        if (isEmpty(patient)) {
            return false;
        }
        return true;
    }


    //drawer menu
    private void menu() {

        menu_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowUserProfile.class);
                startActivity(intent);
            }
        });
        menu_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DoctorProfile.class);
                startActivity(intent);

            }
        });
        menu_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(intent);
            }
        });
        menu_contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:09127792410"));
                startActivity(intent);

            }

        });
        menu_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });
        menu_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}


