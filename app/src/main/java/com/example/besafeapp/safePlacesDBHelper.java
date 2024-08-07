package com.example.besafeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class safePlacesDBHelper extends SQLiteOpenHelper {
    public safePlacesDBHelper(@Nullable Context context) {
        super(context, "BeSafe.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE NearbySafePlaces(locationName TEXT PRIMARY KEY, latitude TEXT, longitude TEXT, safePlace1 TEXT,safePlace2 TEXT,safePlace3 TEXT,safePlace4 TEXT, safePlace5 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP Table if exists NearbySafePlaces");
    }

    public Boolean insertProduct(String locationName, String latitude, String longitude, String safePlace1, String safePlace2, String safePlace3, String safePlace4, String safePlace5){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        //Write content into DB
        ContentValues contentValues = new ContentValues();
        contentValues.put("locationName", locationName);
        contentValues.put("latitude", latitude);
        contentValues.put("longitude", longitude);
        contentValues.put("safePlace1", safePlace1);
        contentValues.put("safePlace2", safePlace2);
        contentValues.put("safePlace3", safePlace3);
        contentValues.put("safePlace4", safePlace4);
        contentValues.put("safePlace5", safePlace5);

        //execute the insert query
        Long result = DB.insert("NearbySafePlaces", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


//    public Boolean updateDiscrete(String locationName){
//        //get database connection
//        SQLiteDatabase DB = this.getWritableDatabase();
//
//        //Write content into DB
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("locationName", locationName);
//
//        //Find record with the ID
//        Cursor currentRecord = DB.rawQuery("SELECT * FROM UserDetails WHERE phoneNo = ?",new String[]{String.valueOf(phone)});
//        if(currentRecord.getCount() >0){
//            //Update record if record exist
//            int result = DB.update("UserDetails", contentValues, "phoneNo = ?",new String[]{String.valueOf(phone)});
//            if(result == -1)
//                return false;
//            else
//                return true;
//        }
//        else
//            return false;
//    }

    public Boolean deleteProduct(String locationName){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        //Find record with the ID
        Cursor findRecord = DB.rawQuery("SELECT * FROM NearbySafePlaces WHERE locationName = ?",new String[]{String.valueOf(locationName)});
        if(findRecord.getCount() >0){
            //Delete record
            int result = DB.delete("NearbySafePlaces","locationName = ?",new String[]{String.valueOf(locationName)});
            if(result == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }

    public Cursor retrieveUser(String locationName){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        //Find record with the ID
        Cursor findRecord = DB.rawQuery("SELECT * FROM NearbySafePlaces WHERE locationName = ?",new String[]{String.valueOf(locationName)});
        return findRecord;
    }

//    public String getPassword(int phone){
//        //get database connection
//        SQLiteDatabase DB = this.getWritableDatabase();
//
//        //Find record with the ID
//        Cursor findRecord = DB.rawQuery("SELECT password FROM NearbySafePlaces WHERE phoneNo = ?",new String[]{String.valueOf(phone)});
//        if(findRecord.getCount()>0){
//            while(findRecord.moveToNext()){
//                return findRecord.getString(0);
//            }
//        }
//        return null;
//    }

    public Cursor retrieveAllPlaces(){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor findAllRecords = DB.rawQuery("SELECT * FROM NearbySafePlaces", null);
        return findAllRecords;
    }
}
