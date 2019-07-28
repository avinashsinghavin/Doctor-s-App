package com.example.doctorsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import java.net.CookieHandler;
import java.util.ArrayList;

public class CommunicateDatabase extends SQLiteOpenHelper {

    private static String DBNAME="COMMUNICATEDB";
    private static int VERSION=3;

    private String TAG="DBACCESS";

    public CommunicateDatabase(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    private void dropTables(SQLiteDatabase db)
    {
        db.execSQL("drop table communicate");
    }

    private void createTables(SQLiteDatabase db)
    {
        db.execSQL("create table communicate(address TEXT,message TEXT)");
        Log.i(TAG, "createTables: message table created ####");
        db.execSQL("insert into communicate(address,message) values('1234567890','hello!')");
        db.execSQL("insert into communicate(address,message) values('0987654321','hi!')");
        Log.i(TAG, "createTables: message records insertion done #####");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate: ### Creating message Database ###");
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(TAG, "onUpgrade: Upgrading message db ######");
        dropTables(db);
        createTables(db);
    }
    public ArrayList<Communicate>getAllCommunicates()
    {
        Log.i(TAG, "getAllCommunicates: getAll Messages #####");
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select address,message from communicate",null);
        ArrayList<Communicate> list=null;
        if(c.moveToFirst())
        {
            list=new ArrayList<>();
            do {
                list.add(new Communicate(c.getString(0),c.getString(1)));
                Log.i(TAG, "getAllCommunicates: got one message ####");
            }while (c.moveToNext());
        }
        return list;
    }
    public long createCommunicate(Communicate c)
    {
        Log.i(TAG, "createCommunicate: #########");
        SQLiteDatabase db=getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("address",c.getAddress());
        cv.put("message",c.getMessage());
        long id=db.insert("communicate",null,cv);
        db.close();
        return id;
    }
}

