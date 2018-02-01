package com.example.user.restoran;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 13/06/2017.
 */

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Restoran1.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table makanan(no integer primary key autoincrement, nama text null, opsi text null, pesan text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

        String sql1 = "create table minuman(no integer primary key autoincrement, nama text null);";
        Log.d("Data", "onCreate: " + sql1);
        db.execSQL(sql1);

        String sql2 = "create table pelanggan(no integer primary key, nama text null, tgl text null, jk text null, alamat text null);";
        Log.d("Data", "onCreate: " + sql2);
        db.execSQL(sql2);

        String sqllogin = "create table login(no integer primary key autoincrement, username text null, password text null);";
        Log.d("Data", "onCreate: " + sqllogin);
        db.execSQL(sqllogin);
        
        sqllogin = "INSERT INTO login (username, password) VALUES ('Admin','admin');";
        db.execSQL(sqllogin);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}
