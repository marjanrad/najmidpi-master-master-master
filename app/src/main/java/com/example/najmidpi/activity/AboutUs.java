package com.example.najmidpi.activity;

import android.content.Intent;
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

public class AboutUs extends AppCompatActivity {
    TextView menu_user,menu_doctor,menu_history,menu_aboutus,menu_contactus , menu_home;
    private DrawerLayout mDrawerLayout;
    TextView tvAboutUs;
    Button btn_web;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconmenu);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        init();
        menu();
        btn_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://teslam.ir"));
                startActivity(browserIntent);

            }
        });


    }

    private void init() {
        menu_user=findViewById(R.id.menu_user);
        menu_doctor=findViewById(R.id.menu_doctor);
        menu_history=findViewById(R.id.menu_history);
        menu_aboutus=findViewById(R.id.menu_aboutus);
        menu_contactus=findViewById(R.id.menu_contactus);
        mDrawerLayout=findViewById(R.id.about_us_drawer);
        menu_home=findViewById(R.id.menu_home);

        tvAboutUs=findViewById(R.id.about_us_tv);
        btn_web=findViewById(R.id.about_us_web);
    }
    //drawer menu
    private void menu() {

        menu_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ShowUserProfile.class);
                startActivity(intent);
            }
        });
        menu_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DoctorProfile.class);
                startActivity(intent);

            }
        });
        menu_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AboutUs.class);
                startActivity(intent);
            }
        });
        menu_contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL
                );
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

        if (id == android.R.id.home){
            if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
