package com.example.najmidpi.database;

import com.example.najmidpi.database.tables.SensorTable;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DatabaseController {

    @Query("SELECT * FROM sensor")
    List<SensorTable> getAllSensor();

    @Query("SELECT COUNT(*) FROM sensor")
    int getSensorsCount();

    @Insert
    void insertSensor(SensorTable... sensorTables);

    @Update
    void updateSensor(SensorTable sensorTable);

    @Delete
    void deleteSensor(SensorTable sensorTable);

    @Query("DELETE FROM sensor")
    void deleteAll();
}
