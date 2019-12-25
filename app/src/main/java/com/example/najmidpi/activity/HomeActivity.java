package com.example.najmidpi.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.najmidpi.R;
import com.example.najmidpi.database.DbHelper;
import com.example.najmidpi.model.SensorObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    TextView menu_user, menu_doctor, menu_history, menu_aboutus, menu_contactus, menu_home;
    private DrawerLayout mDrawerLayout;
    private Button btn_save_data;

    ImageView btnBalance, btnBarometer, btnPedimeter, btnHeartbeat, btnStethoscop;
    TextView tvBalance, tvBarometer, tvPedimeter, tvHeartbeat, tvStethoscop;
    private DbHelper dbHelper;
    private List<SensorObject> list;
    private SensorObject sensorObject;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new DbHelper(this);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconmenu);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        init();
        menu();
        clickButten();


    }

    private void clickButten() {
        final BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();

        btnBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bAdapter == null)
                {
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                }
                else if(!bAdapter.isEnabled()){
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                        Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();

                        tvBalance.setText("60"+" kg");
                    }else if (bAdapter.isEnabled()){
                    tvBalance.setText("60"+" kg");
                    }



            }
        });
        btnBarometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bAdapter == null)
                {
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                }
                else if(!bAdapter.isEnabled()){
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                        Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();

                        tvBarometer.setText("14" +" mmhg");
                    }else if (bAdapter.isEnabled()){
                    tvBarometer.setText("14" +" mmhg");
                }



            }
        });
        btnPedimeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bAdapter == null)
                {
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                }
                else if(!bAdapter.isEnabled()){
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                        Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();

                        tvPedimeter.setText("100"+" step");
                    }else if (bAdapter.isEnabled()){
                    tvPedimeter.setText("100"+" step");
                }



            }
        });
        btnHeartbeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bAdapter == null)
                {
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                }
                else if(!bAdapter.isEnabled()){
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                        Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();

                        tvHeartbeat.setText("70" +" bpm");
                    }else if (bAdapter.isEnabled()){
                    tvHeartbeat.setText("70" +" bpm");
                }



            }
        });
        btn_save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorObject = new SensorObject();

                //get time
                Calendar times = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String time = sdf.format(times.getTime());

                //set in sqlite
                list = new ArrayList<>();
                sensorObject.setFesharSanj(tvBarometer.getText().toString());
                sensorObject.setGamShomar(tvPedimeter.getText().toString());
                sensorObject.setVazn(tvBalance.getText().toString());
                sensorObject.setZarabaneGhalb(tvHeartbeat.getText().toString());
                sensorObject.setDate(getCurrentTime());
                sensorObject.setTime(time);
                list.add(sensorObject);
                dbHelper.add_sensor(list);


                //get from sqlite
                for (int i = 0; i < dbHelper.getAllSensor().size(); i++) {
                String feshar = dbHelper.getAllSensor().get(i).getFesharSanj();
                   String gam = dbHelper.getAllSensor().get(i).getGamShomar();
                    String date = dbHelper.getAllSensor().get(i).getDate();

//                    Log.e("db", String.valueOf(feshar + gam
//                            + date));
//                    Toast.makeText(HomeActivity.this, String.valueOf(feshar + gam
//                            + date + time), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private String getCurrentTime() {

//        Calendar calendar = Calendar.getInstance();
//        String year = String.valueOf(calendar.get(Calendar.YEAR));
//        String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
//        String date = String.valueOf(calendar.get(Calendar.DATE));
//        String time = year + "-" + month + "-" + date;
//        return time;

            Calendar c = Calendar.getInstance();


            String jalaliDate,JalaliMonth;
            int jalaliYear,jalaliMonth,calculateMonth,jalaliDay=0,allDays=0;
            int day=c.get(Calendar.DAY_OF_MONTH);
            int month=c.get(Calendar.MONTH)+1;
            int year=c.get(Calendar.YEAR);

            switch (month){
                case 1: allDays=year*365+31+day;break;
                case 2: allDays=year*365+(31+28)+day;break;
                case 3: allDays=year*365+(31+28+31)+day;break;
                case 4: allDays=year*365+(31+28+31+30)+day;break;
                case 5: allDays=year*365+(31+28+31+30+31)+day;break;
                case 6: allDays=year*365+(31+28+31+30+31+30)+day;break;
                case 7: allDays=year*365+(31+28+31+30+31+30+31)+day;break;
                case 8: allDays=year*365+(31+28+31+30+31+30+31+31)+day;break;
                case 9: allDays=year*365+(31+28+31+30+31+30+31+31+30)+day;break;
                case 10: allDays=year*365+(31+28+31+30+31+30+31+31+30+31)+day;break;
                case 11: allDays=year*365+(31+28+31+30+31+30+31+31+30+31+30)+day;break;
                case 12: allDays=year*365+(31+28+31+30+31+30+31+31+30+31+30+31)+day;break;
            }

            //226899
            jalaliYear=( allDays - 227139 )/365+1;
            calculateMonth=( allDays - 227139 )%365;
            if(calculateMonth<32)jalaliMonth=1;
            else if((calculateMonth-31)<32){jalaliMonth=2;jalaliDay=calculateMonth-31;}
            else if((calculateMonth-62)<32){jalaliMonth=3;jalaliDay=calculateMonth-62;}
            else if((calculateMonth-93)<32){jalaliMonth=4;jalaliDay=calculateMonth-93;}
            else if((calculateMonth-124)<32){jalaliMonth=5;jalaliDay=calculateMonth-124;}
            else if((calculateMonth-155)<32){jalaliMonth=6;jalaliDay=calculateMonth-155;}
            else if((calculateMonth-186)<31){jalaliMonth=7;jalaliDay=calculateMonth-186;}
            else if((calculateMonth-216)<31){jalaliMonth=8;jalaliDay=calculateMonth-216;}
            else if((calculateMonth-246)<31){jalaliMonth=9;jalaliDay=calculateMonth-246;}
            else if((calculateMonth-276)<31){jalaliMonth=10;jalaliDay=calculateMonth-276;}
            else if((calculateMonth-306)<31){jalaliMonth=11;jalaliDay=calculateMonth-306;}
            else {
                jalaliMonth=12;
                if((jalaliYear%4)==0)jalaliDay=calculateMonth-336;
                else jalaliDay=calculateMonth-335;
            }


            String time=String.valueOf(jalaliYear)+"/"+String.valueOf(jalaliMonth)+"/"+String.valueOf(jalaliDay-1);
            return time;

    }


    private void init() {
        menu_user = findViewById(R.id.menu_user);
        menu_doctor = findViewById(R.id.menu_doctor);
        menu_history = findViewById(R.id.menu_history);
        menu_aboutus = findViewById(R.id.menu_aboutus);
        menu_contactus = findViewById(R.id.menu_contactus);
        mDrawerLayout = findViewById(R.id.home_drawer);
        menu_home = findViewById(R.id.menu_home);

        //btn
        btnBalance = findViewById(R.id.home_btn_balance);
        btnBarometer = findViewById(R.id.home_btn_barometer);
        btnPedimeter = findViewById(R.id.home_btn_pedimeter);
        btnHeartbeat = findViewById(R.id.home_btn_heartbeat);
        btnStethoscop = findViewById(R.id.home_btn_stethoscop);
        btn_save_data = findViewById(R.id.btn_save_data);

        //textView
        tvBalance = findViewById(R.id.home_tv_balance);
        tvBarometer = findViewById(R.id.home_tv_barometer);
        tvPedimeter = findViewById(R.id.home_tv_pedimeter);
        tvHeartbeat = findViewById(R.id.home_tv_heartbeat);
//        tvStethoscop = findViewById(R.id.home_tv_stethoscop);

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


