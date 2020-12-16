package com.example.najmidpi.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.najmidpi.R;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


public class ShowUserProfile extends AppCompatActivity {
    TextView menu_user, menu_doctor, menu_history, menu_aboutus, menu_contactus, menu_home;
    TextView input_id, input_name, input_address, input_age, input_tall, input_patient ,input_sex ,input_weight ,input_call;
    ;
    private DrawerLayout mDrawerLayout;
    Button btn_edit;
    public static final String MY_PREFS_NAME = "USER_INFORMATIONS";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_profile);

        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconmenu);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        init();
        show_information();
        menu();

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditUserProfile.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void show_information() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String first_name = prefs.getString("NAME", "");
        String address = prefs.getString("ADDRESS", "");
        String age = prefs.getString("AGE", "");
        String tall = prefs.getString("TALL", "");
        String patient_history = prefs.getString("PATIENT", "");
        String sex = prefs.getString("SEX", "");
        String weight = prefs.getString("WEIGHT", "");
        String call = prefs.getString("NEWCALL", "");

        input_id.setText("0");
        input_name.setText(first_name);
        input_address.setText(address);
        input_age.setText(age);
        input_tall.setText(tall);
        input_patient.setText(patient_history);
        input_sex.setText(sex);
        input_weight.setText(weight);
        input_call.setText(call);

    }

    private void init() {
        btn_edit = findViewById(R.id.show_user_profile_btnedit);

        //menu
        menu_user = findViewById(R.id.menu_user);
        menu_doctor = findViewById(R.id.menu_doctor);
        menu_history = findViewById(R.id.menu_history);
        menu_aboutus = findViewById(R.id.menu_aboutus);
        menu_contactus = findViewById(R.id.menu_contactus);
        mDrawerLayout = findViewById(R.id.show_user_drawer);
        menu_home = findViewById(R.id.menu_home);

        //textView
        input_id = findViewById(R.id.show_user_profile_id);
        input_name = findViewById(R.id.show_user_profile_name);
        input_address = findViewById(R.id.show_user_profile_address);
        input_age = findViewById(R.id.show_user_profile_age);
        input_tall = findViewById(R.id.show_user_profile_tall);
        input_patient = findViewById(R.id.show_user_profile_description);
        input_sex = findViewById(R.id.show_user_profile_sex);
        input_weight=findViewById(R.id.show_user_profile_weight);
        input_call=findViewById(R.id.show_user_profile_call);

    }

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
                intent.setData(Uri.parse("tel:09212755760"));
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



