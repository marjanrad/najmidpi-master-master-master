package com.example.najmidpi.parent;

import android.content.Intent;
import android.os.Bundle;

import com.example.najmidpi.database.DatabaseController;
import com.example.najmidpi.database.DatabaseHolder;
import com.example.najmidpi.helper.UserHelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class ParentActivity extends AppCompatActivity {

    private UserHelper userHelper;
    private DatabaseController dbController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userHelper = UserHelper.getInstance(this);
        dbController = DatabaseHolder.Companion.getInstance(this).databaseController();
    }

    protected UserHelper getUserHelper() {
        return userHelper;
    }

    protected DatabaseController getDatabaseController() {
        return dbController;
    }

    protected void openActivity(Class<? extends AppCompatActivity> activity, boolean withFinish){

        startActivity(
                new Intent(
                        this,
                        activity
                )
        );

        if (withFinish){
            finish();
        }
    }
}
