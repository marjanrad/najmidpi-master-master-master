package com.example.najmidpi.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.najmidpi.R;
import com.example.najmidpi.adapter.AdapterRecyclerHistory;
import com.example.najmidpi.fragment.FragmentBarChart;
import com.example.najmidpi.fragment.FragmentTable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class HistoryActivity extends AppCompatActivity {
    TextView menu_user, menu_doctor, menu_history, menu_aboutus, menu_contactus, menu_home;
    private DrawerLayout mDrawerLayout;
    Spinner sensoreSpinner;
    String[] stringSensore;
    private Button fragmentTable, fragmentBarChart;
    private PersianDatePickerDialog picker;
    TextView tvStartDate, tvEndDate;
    Button btnShow;
    String startYear, startMonth, startDay;
    String endYear, endMonth, endDay;
    int typeSensore = 0;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconmenu);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        init();
        menu();
        spiner();
        chart();

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                recreate();
                final Bundle bundle = new Bundle();
                final FragmentTable fragment = new FragmentTable();

                bundle.clear();
                bundle.putString("startYear", startYear);
                bundle.putString("startMonth", startMonth);
                bundle.putString("startDay", startDay);
                bundle.putString("endYear", endYear);
                bundle.putString("endMonth", endMonth);
                bundle.putString("endDay", endDay);

                fragment.setArguments(bundle);

                setFragment(fragment);
            }
        });

    }

    private void chart() {

        final Bundle bundle = new Bundle();
        final FragmentTable fragment = new FragmentTable();
        final FragmentBarChart fragment2 = new FragmentBarChart();


        fragmentTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bundle.clear();
                bundle.putString("startYear", startYear);
                bundle.putString("startMonth", startMonth);
                bundle.putString("startDay", startDay);
                bundle.putString("endYear", endYear);
                bundle.putString("endMonth", endMonth);
                bundle.putString("endDay", endDay);

                fragment.setArguments(bundle);

                setFragment(fragment);
            }
        });

//        fragmentBarChart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                setFragment(fragment2);
//            }
//        });

    }

    private void setFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentDynamic, fragment);
        ft.commit();
    }

    private void spiner() {
        stringSensore = getResources().getStringArray(R.array.sensoreArray);
        ArrayAdapter<String> AdapterSensore = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stringSensore);
        AdapterSensore.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sensoreSpinner.setAdapter(AdapterSensore);
        sensoreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(HistoryActivity.this, (String) parent.getSelectedItem(), Toast.LENGTH_SHORT).show();


                AdapterRecyclerHistory adapter = new AdapterRecyclerHistory();
                adapter.sensoreType(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void init() {
        //spinner
        sensoreSpinner = findViewById(R.id.history_spiner);

        //menu
        menu_user = findViewById(R.id.menu_user);
        menu_doctor = findViewById(R.id.menu_doctor);
        menu_history = findViewById(R.id.menu_history);
        menu_aboutus = findViewById(R.id.menu_aboutus);
        menu_contactus = findViewById(R.id.menu_contactus);
        mDrawerLayout = findViewById(R.id.history_drawer);
        menu_home = findViewById(R.id.menu_home);

        //chart
        fragmentTable = findViewById(R.id.history_btn_pie);
//        fragmentBarChart = findViewById(R.id.history_btn_bar_chart);

        //date
        tvStartDate = findViewById(R.id.history_tv_start_date);
        tvEndDate = findViewById(R.id.history_tv_end_date);

        btnShow = findViewById(R.id.history_btn_show);

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

    public void showCalendar(View view) {
        picker = new PersianDatePickerDialog(this);
        picker.setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setActionTextColor(Color.GRAY)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {

                        startYear = String.valueOf(persianCalendar.getPersianYear());
                        startMonth = String.valueOf(persianCalendar.getPersianMonth());
                        startDay = String.valueOf(persianCalendar.getPersianDay());

                        tvStartDate.setText(persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay());


                    }

                    @Override
                    public void onDismissed() {

                    }
                });

        picker.show();

    }

    public void showCalendarEnd(View view) {
        picker = new PersianDatePickerDialog(this);
        picker.setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setActionTextColor(Color.GRAY)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {

                        endYear = String.valueOf(persianCalendar.getPersianYear());
                        endMonth = String.valueOf(persianCalendar.getPersianMonth());
                        endDay = String.valueOf(persianCalendar.getPersianDay());

                        tvEndDate.setText(persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay());
                    }

                    @Override
                    public void onDismissed() {

                    }
                });

        picker.show();

    }
}

