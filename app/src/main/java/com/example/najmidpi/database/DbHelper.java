package com.example.najmidpi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.najmidpi.model.SensorObject;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private final static int Version_DB = 1;
    public final static String DB_NAME = "Health";
    public final static String TABLE_SENSOR = "sensor";

    public final static String COL_ID = "id";
    public final static String COL_GAMSHOMAR = "gam_shomar";
    public final static String COL_FESHARSANJ = "feshar_sanj";
    public final static String COL_ZARABENGHALB = "zaraban_ghalb";
    public final static String COL_VAZN = "vazn";
    public final static String COL_DATE = "date";
    public final static String COL_TIMES = "times";

    private static DbHelper dbHelper;
    private SQLiteDatabase db;
    private ContentValues values;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, Version_DB);

    }

    public static DbHelper newInstance(Context context) {


        if (dbHelper == null) {
            dbHelper = new DbHelper(context);

        }

        return dbHelper;

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryTableSensor = "create table " + TABLE_SENSOR + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " + COL_FESHARSANJ
                + " INTEGER ," + COL_GAMSHOMAR + " INTEGER," + COL_ZARABENGHALB
                + " INTEGER," + COL_VAZN + " INTEGER," + COL_TIMES + " TEXT," + COL_DATE + " TEXT );";

        sqLiteDatabase.execSQL(queryTableSensor);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        if (oldVersion < newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
            onCreate(sqLiteDatabase);
        }
    }

    public synchronized void openReadable() {
        if (isOpenDatabase() == false) {
            db = getReadableDatabase();
        }
    }

    public synchronized void openWritable() {
        if (isOpenDatabase() == false) {
            db = getWritableDatabase();
        }
    }

    public boolean isOpenDatabase() {

        return (db != null && db.isOpen());
    }

    public synchronized void close() {
        if (db != null) {
            db.close();

        }

    }


    // add city in database sqlite
    public void add_sensor(List<SensorObject> list) {
        db = this.getWritableDatabase();
        db.beginTransaction();
        try {
             values = new ContentValues();
            for (SensorObject se : list) {
                values.put(COL_FESHARSANJ, se.getFesharSanj());
                values.put(COL_GAMSHOMAR, se.getGamShomar());
                values.put(COL_VAZN, se.getVazn());
                values.put(COL_ZARABENGHALB, se.getZarabaneGhalb());
                values.put(COL_DATE, se.getDate());
                values.put(COL_TIMES, se.getTime());


                db.insert(TABLE_SENSOR, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }


    }

    //
    // return city list from database
    public ArrayList<SensorObject> getAllSensor() {

        ArrayList<SensorObject> sensorList = new ArrayList<>();
        Cursor cursor = null;
        try {

            openReadable();
            cursor = db.rawQuery("select * from " + TABLE_SENSOR, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                SensorObject object = new SensorObject();

                object.setDate(cursor.getString(cursor.getColumnIndex(COL_DATE)));
                object.setZarabaneGhalb(cursor.getString(cursor.getColumnIndex(COL_ZARABENGHALB)));
                object.setVazn(cursor.getString(cursor.getColumnIndex(COL_VAZN)));
                object.setGamShomar(cursor.getString(cursor.getColumnIndex(COL_GAMSHOMAR)));
                object.setFesharSanj(cursor.getString(cursor.getColumnIndex(COL_FESHARSANJ)));
                object.setTime(cursor.getString(cursor.getColumnIndex(COL_TIMES)));

                sensorList.add(object);
                cursor.moveToNext();
            }


        } catch (Exception e) {
            //Log.e("error", e.getMessage());
        } finally {
            if (cursor != null)
                cursor.close();
            close();

        }


        return sensorList;

    }
//
//
//    // add bar in database sqlite
//    public void addBar(NewBarItemsData object) {//change to product
//        db = this.getWritableDatabase();
//        db.beginTransaction();
//        try {
//
//
//            values = new ContentValues();
//
//            values.put(COL_NAME, object.getName());
//            values.put(COL_BAR_ID, object.getId());
//
//            db.insert(TABLE_CARGO, null, values);
//            db.setTransactionSuccessful();
//
//        }finally {
//            db.endTransaction();
//        }
//    }
//
//
//    // return bar list from database
//    public ArrayList<NewBarItemsData> getAllBar() {
//
//        ArrayList<NewBarItemsData> barList = new ArrayList<>();
//        Cursor cursor = null;
//        try {
//
//            openReadable();
//            cursor = db.rawQuery("select * from " + TABLE_CARGO, null);
//            cursor.moveToFirst();
//
//            while (!cursor.isAfterLast()) {
//
//                NewBarItemsData object = new NewBarItemsData();
//
//                object.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
//                object.setId(cursor.getLong(cursor.getColumnIndex(COL_BAR_ID)));
//
//                barList.add(object);
//                cursor.moveToNext();
//
//            }
//
//
//        } catch (Exception e) {
//            //Log.e("error", e.getMessage());
//        } finally {
//            if (cursor != null)
//                cursor.close();
//            close();
//
//        }
//
//
//        return barList;
//
//    }
//
//    // add support in database sqlite
//    public void addPackage(NewBarItemsData object) {
//        db = this.getWritableDatabase();
//        db.beginTransaction();
//        try{
//
//             values = new ContentValues();
//
//            values.put(COL_NAME, object.getName());
//            values.put(COL_PACKAGE_ID, object.getId());
//
//            db.insert(TABLE_CATEGORY_CARGO, null, values);
//            db.setTransactionSuccessful();
//
//        }finally {
//            db.endTransaction();
//        }
//    }
//
//    public void clearAllPackageFromDb(){
//
//        db.execSQL("delete from "+ TABLE_CATEGORY_CARGO);
//
//    }
//    public void clearAllProductFromDb(){
//
//        db.execSQL("delete from "+ TABLE_CARGO);
//
//    }
//    // return support list from database
//    public ArrayList<NewBarItemsData> getAllPackage() {
//
//        ArrayList<NewBarItemsData> packageList = new ArrayList<>();
//        Cursor cursor = null;
//        try {
//
//            openReadable();
//            cursor = db.rawQuery("select * from " + TABLE_CATEGORY_CARGO, null);
//            cursor.moveToFirst();
//
//            while (!cursor.isAfterLast()) {
//
//                NewBarItemsData object = new NewBarItemsData();
//
//                object.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
//                object.setId(cursor.getLong(cursor.getColumnIndex(COL_PACKAGE_ID)));
//
//                packageList.add(object);
//                cursor.moveToNext();
//
//            }
//
//
//        } catch (Exception e) {
//           // Log.e("error", e.getMessage());
//        } finally {
//            if (cursor != null)
//                cursor.close();
//            close();
//
//        }
//
//
//        return packageList;
//
//    }
//
//
//    public void add_support(List<SupportDataList> list) {
//        db = this.getWritableDatabase();
//        db.beginTransaction();
//        try {
//
//            db.execSQL("delete from "+ TABLE_SUPPORT);
//
//            values = new ContentValues();
//            for (SupportDataList support : list) {
//                values.put(COL_SUPPORT_ID, support.getId());
//                values.put(COL_TITLE, support.getTitle());
//                values.put(COL_DESCRIPTION, support.getDescription());
//                db.insert(TABLE_SUPPORT, null, values);
//                //Log.e("insert" , support.getId() + "-" + support.getTitle() + "-" + support.getDescription());
//            }
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
//
//
//    }
//
//    // return package list from database
//    public ArrayList<SupportDataList> getAllSupport() {
//
//        ArrayList<SupportDataList> supportList = new ArrayList<>();
//        Cursor cursor = null;
//        try {
//
//            openReadable();
//            cursor = db.rawQuery("select * from " + TABLE_SUPPORT, null);
//            cursor.moveToFirst();
//
//            while (!cursor.isAfterLast()) {
//
//                SupportDataList object = new SupportDataList();
//
//                object.setId(cursor.getLong(cursor.getColumnIndex(COL_SUPPORT_ID)));
//                object.setTitle(cursor.getString(cursor.getColumnIndex(COL_TITLE)));
//                object.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)));
//
//                supportList.add(object);
//                cursor.moveToNext();
//                //Log.e("read", object.getId() + "-" + object.getTitle());
//
//            }
//
//
//        } catch (Exception e) {
//           // Log.e("error", e.getMessage());
//        } finally {
//            if (cursor != null)
//                cursor.close();
//            close();
//
//        }
//
//
//        return supportList;
//
//    }
//
//    // add truks in database sqlite
//    public void add_truks(List<NewBarItemsData> list) {
//        db = this.getWritableDatabase();
//        db.beginTransaction();
//        try {
//
//            values = new ContentValues();
//            for (NewBarItemsData city : list) {
//                values.put(COL_NAME_TRUCKS, city.getName());
//                values.put(COL_TRUCKS_ID, city.getId());
//                db.insert(TABLE_TRUCKS, null, values);
//            }
//            //Log.e("insert" , "insert All");
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
//
//
//
//
//    }
//
//    // return truks list from database
//    public ArrayList<NewBarItemsData> getAllTruks() {
//
//        ArrayList<NewBarItemsData> truksList = new ArrayList<>();
//        Cursor cursor = null;
//        try {
//
//            openReadable();
//            cursor = db.rawQuery("select * from " + TABLE_TRUCKS, null);
//            cursor.moveToFirst();
//
//            while (!cursor.isAfterLast()) {
//
//                NewBarItemsData object = new NewBarItemsData();
//
//                object.setName(cursor.getString(cursor.getColumnIndex(COL_NAME_TRUCKS)));
//                object.setId(cursor.getLong(cursor.getColumnIndex(COL_TRUCKS_ID)));
//
//                truksList.add(object);
//                cursor.moveToNext();
//            }
//           // Log.e("read", "read from database");
//
//        } catch (Exception e) {
//           // Log.e("error", e.getMessage());
//        } finally {
//            if (cursor != null)
//                cursor.close();
//            close();
//
//        }
//
//        return truksList;
//
//    }
//
//
//    // return city list from database while search province and city
//    public ArrayList<NewBarItemsData> getSearchCity( String txtSearch) {
//        ArrayList<NewBarItemsData> cityList = new ArrayList<>();
//        Cursor cursor = null;
//        try {
//            openReadable();
//            cursor = db.rawQuery("select * from " + TABLE_CITY + " WHERE " + COL_NAME + " like '%"+txtSearch+"%'", null);
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                NewBarItemsData object = new NewBarItemsData();
//                object.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
//                object.setId(cursor.getLong(cursor.getColumnIndex(COL_CITY_ID)));
//                cityList.add(object);
//                cursor.moveToNext();
//            }
//        } catch (Exception e) {
//            Log.e("error", e.getMessage());
//        } finally {
//            if (cursor != null)
//            cursor.close();
//            close();
//        }
//        return cityList;
//    }
//
//    // return city list from database while search city
//    public ArrayList<NewBarItemsData> getSearchJustCity( String txtSearch) {
//        ArrayList<NewBarItemsData> cityList = new ArrayList<>();
//        Cursor cursor = null;
//        try {
//            openReadable();
//            cursor = db.rawQuery("select * from " + TABLE_CITY + " WHERE " + COL_NAME + " like '"+txtSearch+"%'", null);
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                NewBarItemsData object = new NewBarItemsData();
//                object.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
//                object.setId(cursor.getLong(cursor.getColumnIndex(COL_CITY_ID)));
//                cityList.add(object);
//                cursor.moveToNext();
//            }
//        } catch (Exception e) {
//            Log.e("error", e.getMessage());
//        } finally {
//            if (cursor != null)
//            cursor.close();
//            close();
//        }
//        return cityList;
//    }
//
//    // return city list from database while search city
//    public ArrayList<NewBarItemsData> getSearchProduct( String txtSearch) {
//        ArrayList<NewBarItemsData> cityList = new ArrayList<>();
//        Cursor cursor = null;
//        try {
//            openReadable();
//            cursor = db.rawQuery("select * from " + TABLE_CARGO + " WHERE " + COL_NAME + " like '%"+txtSearch+"%'", null);
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                NewBarItemsData object = new NewBarItemsData();
//                object.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
//                object.setId(cursor.getLong(cursor.getColumnIndex(COL_BAR_ID)));
//                cityList.add(object);
//                cursor.moveToNext();
//            }
//        } catch (Exception e) {
//            Log.e("error", e.getMessage());
//        } finally {
//            if (cursor != null)
//            cursor.close();
//            close();
//        }
//        return cityList;
//    }
//
//
//    // return truks list from database while search trucks name
//    public ArrayList<NewBarItemsData> getSearchTruks(String truckName) {
//        ArrayList<nsorNewBarItemsData> truksList = new ArrayList<>();
//        Cursor cursor = null;
//        try {
//            openReadable();
//            cursor = db.rawQuery("select * from " + TABLE_TRUCKS +" WHERE "+ COL_NAME_TRUCKS +" like '"+truckName+"%'", null);
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                NewBarItemsData object = new NewBarItemsData();
//                object.setName(cursor.getString(cursor.getColumnIndex(COL_NAME_TRUCKS)));
//                object.setId(cursor.getLong(cursor.getColumnIndex(COL_TRUCKS_ID)));
//                truksList.add(object);
//                cursor.moveToNext();
//            }
//        } catch (Exception e) {
//            Log.e("error", e.getMessage());
//        } finally {
//            cursor.close();
//            close();
//        }
//        return truksList;
//    }

}
