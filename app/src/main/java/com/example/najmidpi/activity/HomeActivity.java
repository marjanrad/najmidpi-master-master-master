package com.example.najmidpi.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.najmidpi.R;
import com.example.najmidpi.bluetooth.DeviceScanActivity;
import com.example.najmidpi.database.tables.SensorTable;
import com.example.najmidpi.parent.ParentActivity;
import com.example.najmidpi.server.RetrofitSetting;
import com.example.najmidpi.server.model.RequestSensor;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

//import com.example.najmidpi.model.SensorObject;

public class HomeActivity extends ParentActivity implements LocationListener {

    TextView menu_user, menu_doctor, menu_history, menu_aboutus, menu_contactus, menu_home;
    private DrawerLayout mDrawerLayout;
    private Button btn_save_data;
    ImageView btnBalance, btnBarometer, btnBloodOxygen, btnHeartbeat, btnSugarBlood;
    TextView tvBalance, tvBarometer, tvBloodOxygen, tvHeartbeat, tvSugarBlood;
    static int type;
    LocationManager locationManager;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconmenu);


        init();
        menu();
        clickButten();


        //get data from DeviceControl and set in TextView
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences prefs = getSharedPreferences("SENSORE-DATA", MODE_PRIVATE);
        String sensoreData = prefs.getString("DATA", "0");

        if (type == 1) {
            tvBalance.setText(sensoreData);
        } else if (type == 2) {
            tvBarometer.setText(sensoreData);
        } else if (type == 3) {
            tvBloodOxygen.setText(sensoreData);
        } else if (type == 4) {
            tvHeartbeat.setText(sensoreData);
        } else if (type == 5) {
            tvSugarBlood.setText(sensoreData);
        }


    }

    // click for get data from bluetooth sensore and send data to server
    private void clickButten() {

        btnBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                type = 1;

                //send data for DeviceControlActivity
                String measurment = "0000181b-0000-1000-8000-00805f9b34fb";
                String dataAdress = "00002a9c-0000-1000-8000-00805f9b34fb";
                sendAdressToBluetooth(measurment, dataAdress);


                //send macAdress to DeviceScanActivity
                Intent intent = new Intent(getApplicationContext(), DeviceScanActivity.class);
                intent.putExtra("MACADRESS", "E0:78:6A:A5:0B:B7");
                startActivity(intent);


            }
        });
        btnBarometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                type = 2;


                //send data for DeviceControlActivity
                String measurment = "";
                String dataAdress = "";
                sendAdressToBluetooth(measurment, dataAdress);

                //send macAdress to DeviceScanActivity
                Intent intent = new Intent(getApplicationContext(), DeviceScanActivity.class);
                intent.putExtra("MACADRESS", "A4:D5:78:40:7F:3B");
                startActivity(intent);

            }
        });
        btnBloodOxygen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                type = 3;


                //send data for DeviceControlActivity
                String measurment = "";
                String dataAdress = "";
                sendAdressToBluetooth(measurment, dataAdress);

                //send macAdress to DeviceScanActivity
                Intent intent = new Intent(getApplicationContext(), DeviceScanActivity.class);
                intent.putExtra("MACADRESS", "");
                startActivity(intent);

            }
        });
        btnHeartbeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                type = 4;

                //send data for DeviceControlActivity
                String measurment = "0000180a-0000-1000-8000-00805f9b34fb";
                String dataAdress = "00002a26-0000-1000-8000-00805f9b34fb";
                sendAdressToBluetooth(measurment, dataAdress);

                //send macAdress to DeviceScanActivity
                Intent intent = new Intent(getApplicationContext(), DeviceScanActivity.class);
                intent.putExtra("MACADRESS", "C0:00:00:D5:09:E7");
                startActivity(intent);

            }
        });
        btnSugarBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 5;

                //send data for DeviceControlActivity
                String measurment = "";
                String dataAdress = "";
                sendAdressToBluetooth(measurment, dataAdress);


                //send macAdress to DeviceScanActivity
                Intent intent = new Intent(getApplicationContext(), DeviceScanActivity.class);
                intent.putExtra("MACADRESS", "");
                startActivity(intent);
            }
        });


        //save data in sqlite and send to server
        btn_save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(HomeActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

                } else {

                    // save in sqlite
                    saveInSql();

                    //send data to server
                    setDataToApi();
                }


                // location
                getLocation();
            }
        });
    }


    //save data in your phone
    private void saveInSql() {
        //get time
        Calendar times = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        final String time = sdf.format(times.getTime());

        SensorTable sensor = new SensorTable();

        sensor.setFeshar_sanj(Integer.valueOf(tvBarometer.getText().toString()));
        sensor.setBlood_oxygen(Integer.valueOf(tvBloodOxygen.getText().toString()));
        sensor.setVazn(Integer.valueOf(tvBalance.getText().toString()));
        sensor.setZaraban_ghalb(Integer.valueOf(tvHeartbeat.getText().toString()));
        sensor.setGhandKhon(Integer.valueOf(tvSugarBlood.getText().toString()));
        sensor.setDate(getCurrentTime());
        sensor.setTimes(time);

        getDatabaseController().insertSensor(sensor);

//                Toast.makeText(HomeActivity.this, "ذخیره", Toast.LENGTH_SHORT).show();
    }

    //get location
    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, false);
            Location location = locationManager.getLastKnownLocation(provider);

//           double lat= location.getLatitude();
//            locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
//            Toast.makeText(this, "lat"+location.getLatitude(), Toast.LENGTH_SHORT).show();


        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
//        locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
//        Toast.makeText(this, "lat" + location.getLatitude(), Toast.LENGTH_SHORT).show();
        Log.d("getLocation", "lat " + location.getLatitude());
        Log.d("getLocation", "lat " + location.getLongitude());

    }

    @Override
    public void onProviderDisabled(String provider) {
//        Toast.makeText(HomeActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();

        final LocationManager manager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Intent gps = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(gps);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    //send data to server
    private void setDataToApi() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setMessage("لطفا چند لحظه صبر کنید...");
        final String vazn = tvBalance.getText().toString();
        final String zarabanGhalb = tvHeartbeat.getText().toString();
        final String oxygenKhon = tvBloodOxygen.getText().toString();
        final String feshar = tvBarometer.getText().toString();
        final String ghandKhon = tvSugarBlood.getText().toString();
        Calendar times = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        final String time = sdf.format(times.getTime());

        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        String call = prefs.getString("CALL", "");

        final String patId = call;

        final String drId = "2";
        final String locationLat = "0.365";
        final String locationlong = "2.544540";
        String fullDate = getCurrentTime() + " " + time;

        RequestSensor sensorObject = new RequestSensor();
        sensorObject.setHbeat(zarabanGhalb);
        sensorObject.setDate(fullDate);
        sensorObject.setFeshar(feshar);
        sensorObject.setOxygen(oxygenKhon);
        sensorObject.setVazn(vazn);
        sensorObject.setSugar(ghandKhon);
        sensorObject.setDrId(drId);
        sensorObject.setPatId(patId);
        sensorObject.setLat(locationLat);
        sensorObject.setLon(locationlong);

        Single<Response<Void>> setData = RetrofitSetting.getInstance().getApiService().setDataSensor(sensorObject);
        setData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<Response<Void>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Response<Void> responseSensorResponse) {

                if (responseSensorResponse.isSuccessful()) {
                    Toast.makeText(HomeActivity.this, "ارسال شد", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(HomeActivity.this, "خطا", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, "error", Toast.LENGTH_SHORT).show();
                Log.e("error", e.getMessage());
            }
        });
    }

    //send ip adress to bluetooth
    private void sendAdressToBluetooth(String measurment, String dataAdress) {

        SharedPreferences.Editor editor = getSharedPreferences("MEASUREMENT_ADRESS", MODE_PRIVATE).edit();
        editor.putString("MEASUREMENT", measurment);
        editor.putString("DATA_ADRESS", dataAdress);
        editor.commit();
    }

    //get date
    private String getCurrentTime() {

        Calendar c = Calendar.getInstance();

        String jalaliDate, JalaliMonth;
        int jalaliYear, jalaliMonth, calculateMonth, jalaliDay = 0, allDays = 0;
        int day = c.get(Calendar.DAY_OF_MONTH)+1;
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);

        switch (month) {
            case 1:
                allDays = year * 365 + 31 + day;
                break;
            case 2:
                allDays = year * 365 + (31 + 28) + day;
                break;
            case 3:
                allDays = year * 365 + (31 + 28 + 31) + day;
                break;
            case 4:
                allDays = year * 365 + (31 + 28 + 31 + 30) + day;
                break;
            case 5:
                allDays = year * 365 + (31 + 28 + 31 + 30 + 31) + day;
                break;
            case 6:
                allDays = year * 365 + (31 + 28 + 31 + 30 + 31 + 30) + day;
                break;
            case 7:
                allDays = year * 365 + (31 + 28 + 31 + 30 + 31 + 30 + 31) + day;
                break;
            case 8:
                allDays = year * 365 + (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31) + day;
                break;
            case 9:
                allDays = year * 365 + (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30) + day;
                break;
            case 10:
                allDays = year * 365 + (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31) + day;
                break;
            case 11:
                allDays = year * 365 + (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30) + day;
                break;
            case 12:
                allDays = year * 365 + (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + 31) + day;
                break;
        }

        //226899
        jalaliYear = (allDays - 227139) / 365 + 1;
        calculateMonth = (allDays - 227139) % 365;
        if (calculateMonth < 32) jalaliMonth = 1;
        else if ((calculateMonth - 31) < 32) {
            jalaliMonth = 2;
            jalaliDay = calculateMonth - 31;
        } else if ((calculateMonth - 62) < 32) {
            jalaliMonth = 3;
            jalaliDay = calculateMonth - 62;
        } else if ((calculateMonth - 93) < 32) {
            jalaliMonth = 4;
            jalaliDay = calculateMonth - 93;
        } else if ((calculateMonth - 124) < 32) {
            jalaliMonth = 5;
            jalaliDay = calculateMonth - 124;
        } else if ((calculateMonth - 155) < 32) {
            jalaliMonth = 6;
            jalaliDay = calculateMonth - 155;
        } else if ((calculateMonth - 186) < 31) {
            jalaliMonth = 7;
            jalaliDay = calculateMonth - 186;
        } else if ((calculateMonth - 216) < 31) {
            jalaliMonth = 8;
            jalaliDay = calculateMonth - 216;
        } else if ((calculateMonth - 246) < 31) {
            jalaliMonth = 9;
            jalaliDay = calculateMonth - 246;
        } else if ((calculateMonth - 276) < 31) {
            jalaliMonth = 10;
            jalaliDay = calculateMonth - 276;
        } else if ((calculateMonth - 306) < 31) {
            jalaliMonth = 11;
            jalaliDay = calculateMonth - 306;
        } else {
            jalaliMonth = 12;
            if ((jalaliYear % 4) == 0) jalaliDay = calculateMonth - 336;
            else jalaliDay = calculateMonth - 335;
        }


        String time = String.valueOf(jalaliYear) + "/" + String.valueOf(jalaliMonth) + "/" + String.valueOf(jalaliDay);
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
        btnBloodOxygen = findViewById(R.id.home_btn_bloodOxygen);
        btnHeartbeat = findViewById(R.id.home_btn_heartbeat);
        btnSugarBlood = findViewById(R.id.home_btn_sugar_blood);
        btn_save_data = findViewById(R.id.btn_save_data);

        //textView
        tvBalance = findViewById(R.id.home_tv_balance);
        tvBarometer = findViewById(R.id.home_tv_barometer);
        tvBloodOxygen = findViewById(R.id.home_tv_bloodOxygen);
        tvHeartbeat = findViewById(R.id.home_tv_heartbeat);
        tvSugarBlood = findViewById(R.id.home_tv_bloodsugar);

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

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = getSharedPreferences("SENSORE-DATA", MODE_PRIVATE).edit();
        editor.putString("DATA", "0");
        editor.commit();
    }
}


