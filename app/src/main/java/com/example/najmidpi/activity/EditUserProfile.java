package com.example.najmidpi.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.najmidpi.R;
import com.example.najmidpi.server.RetrofitSetting;
import com.example.najmidpi.server.model.PatientInfirmation;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static android.text.TextUtils.isEmpty;

public class EditUserProfile extends AppCompatActivity {

    TextView menu_user, menu_doctor, menu_history, menu_aboutus, menu_contactus, menu_home;
    private DrawerLayout mDrawerLayout;
    RadioGroup radioGroup;
    RadioButton radioButton;
    public static final String MY_PREFS_NAME = "USER_INFORMATIONS";
    TextInputLayout name, address, age, tall, patient_history, weight, call;


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

                String first_name = name.getEditText().getText().toString().trim();
                String address = EditUserProfile.this.address.getEditText().getText().toString().trim();
                String age1 = age.getEditText().getText().toString().trim();
                String tall1 = tall.getEditText().getText().toString().trim();
                String patient = patient_history.getEditText().getText().toString().trim();
                String patWeight = weight.getEditText().getText().toString().trim();
                String getCall = call.getEditText().getText().toString().trim();

                //save data in memory
                if (isValidInput(first_name, address, age1, tall1, patWeight, getCall)) {

                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("NAME", first_name);
                    editor.putString("ADDRESS", address);
                    editor.putString("AGE", age1);
                    editor.putString("TALL", tall1);
                    editor.putString("PATIENT", patient);
                    editor.putString("SEX", radio);
                    editor.putString("WEIGHT", patWeight);
                    editor.putString("NEWCALL", getCall);

                    editor.commit();

                    setDataToApi();
                    Intent intent = new Intent(getApplicationContext(), ShowUserProfile.class);
                    startActivity(intent);
                }


            }
        });

    }

    //get data from memory and set in editText
    private void getvalue() {

        //get
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String first_name = prefs.getString("NAME", "");
        String address = prefs.getString("ADDRESS", "");
        String age1 = prefs.getString("AGE", "");
        String tall1 = prefs.getString("TALL", "");
        String patienthistory = prefs.getString("PATIENT", "");
        String sex = prefs.getString("SEX", "");
        String patWeight = prefs.getString("WEIGHT", "");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences calls = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        String setCall = calls.getString("CALL", "");


        //set
        name.getEditText().setText(first_name);
        this.address.getEditText().setText(address);
        age.getEditText().setText(age1);
        tall.getEditText().setText(tall1);
        patient_history.getEditText().setText(patienthistory);
        weight.getEditText().setText(patWeight);
        call.getEditText().setText(setCall);


    }

    private void init() {
        btnsave = findViewById(R.id.edit_user_profile_btnsave);

        //menu
        menu_user = findViewById(R.id.menu_user);
        menu_doctor = findViewById(R.id.menu_doctor);
        menu_history = findViewById(R.id.menu_history);
        menu_aboutus = findViewById(R.id.menu_aboutus);
        menu_contactus = findViewById(R.id.menu_contactus);
        mDrawerLayout = findViewById(R.id.edit_user_drawer);
        menu_home = findViewById(R.id.menu_home);


        radioGroup = findViewById(R.id.edit_user_profile_radioGroup);
        //editText
        name = findViewById(R.id.edit_user_profile_name);
        address = findViewById(R.id.edit_user_profile_address);
        age = findViewById(R.id.edit_user_profile_age);
        tall = findViewById(R.id.edit_user_profile_tall);
        patient_history = findViewById(R.id.edit_user_profile_patient_history);
        weight = findViewById(R.id.edit_user_profile_weight);
        call = findViewById(R.id.edit_user_profile_call);

    }

    //check edit text in not null
    private boolean isValidInput(String first_name, String last_name, String age1, String tall1, String patWeight, String call) {
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
        if (isEmpty(patWeight)) {
            return false;
        }
        if (isEmpty(call)) {
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


    //send data to server
    private void setDataToApi() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setMessage("لطفا چند لحظه صبر کنید...");


        String first_name = name.getEditText().getText().toString().trim();
        String address = EditUserProfile.this.address.getEditText().getText().toString().trim();
        String age1 =age.getEditText().getText().toString().trim();
        String height = tall.getEditText().getText().toString().trim();
        String history = patient_history.getEditText().getText().toString().trim();
        String patWeight = weight.getEditText().getText().toString().trim();
        String drId = "7";

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        String call = prefs.getString("CALL", "");
        String patId = call;

        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        String gender = radioButton.getText().toString();


        PatientInfirmation patientInfirmation = new PatientInfirmation();
        patientInfirmation.setName(first_name);
        patientInfirmation.setAddress(address);
        patientInfirmation.setAge(age1);
        patientInfirmation.setHeight(height);
        patientInfirmation.setCasehist(history);
        patientInfirmation.setWeight(patWeight);
        patientInfirmation.setPatId( patId);
        patientInfirmation.setCall(call);
        patientInfirmation.setDrId(drId);
        patientInfirmation.setGender(gender);


        Single<Response<Void>> setData = RetrofitSetting.getInstance().getApiService().setPatInformation(patientInfirmation);
        setData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<Response<Void>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Response<Void> responseSensorResponse) {

                if (responseSensorResponse.isSuccessful()) {
                    Toast.makeText(EditUserProfile.this, "ارسال شد", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditUserProfile.this, "ارسال نشد", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Toast.makeText(EditUserProfile.this, "error", Toast.LENGTH_SHORT).show();
                Log.e("error", e.getMessage());
                Toast.makeText(EditUserProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}


