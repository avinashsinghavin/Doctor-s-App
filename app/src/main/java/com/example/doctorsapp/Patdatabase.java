package com.example.doctorsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class Patdatabase extends SQLiteOpenHelper {
    private static String Patname="PATIENT";
    private static int VERSION=3;
    private String TAG="PATACCESS";

    public Patdatabase(Context context) {
        super(context, Patname, null, VERSION);
    }


    private void dropTables(SQLiteDatabase db){
        db.execSQL("drop table patinfo");
    }
    private void createTables(SQLiteDatabase db){
        db.execSQL("create table patinfo(patid INTEGER PRIMARY KEY,name TEXT,age TEXT,gender TEXT,phone TEXT,ctgr TEXT)");
        Log.i(TAG,"createTables: table created #######");
        db.execSQL("insert into patinfo(name,age,gender,phone,ctgr) values ('Prabhas','21','M','9679049540','Emergency')");
        db.execSQL("insert into patinfo(name,age,gender,phone,ctgr) values ('Antick','21','M','9679049540','NORMAL')");
        db.execSQL("insert into patinfo(name,age,gender,phone,ctgr) values ('Debdip','21','M','9679049540','Emergency')");
        Log.i(TAG,"createTables: records insertion done #######");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,"##### creating database #####");
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int newV) {
        Log.i(TAG,"onUpgrade: upgrading db #####");
        dropTables(db);
        createTables(db);
    }
    ArrayList<Patients> getAllPatients(){
        Log.i(TAG,"getAllPatients: ####");
        SQLiteDatabase db=getReadableDatabase();
        Cursor c= (Cursor) db.rawQuery("select patid,name,ctgr from patinfo",null);
        ArrayList<Patients> list=null;
        if (c.moveToFirst()){
            list=new ArrayList<>();
            do {
                list.add(new Patients(c.getString(0),c.getString(1),c.getString(2)));
                Log.i(TAG,"getAllPatients: got one record ####");
            }while(c.moveToNext());
        }
        return list;
    }
    public long createPatient(Patients p){
        Log.i(TAG,"createPatient: #####");
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
       // cv.put("patid",p.getPatientID());
        cv.put("name",p.getPatientName());
        cv.put("age",p.getPatientAge());
        cv.put("phone",p.getPatientphone());
        cv.put("gender",p.getPatientgender());
        cv.put("ctgr",p.getPatientCategory());
        Long id=db.insert("patinfo",null,cv);
        Log.i(TAG,"createPatient: records created : "+id);

        db.close();
        return id;
    }
    public void updatePatientDetails(String a,String b)
    {
        Log.i(TAG, "updatePatient: #####");
        SQLiteDatabase db=this.getWritableDatabase();
         db.execSQL("update patinfo set ctgr='"+b+"' where patid="+a);
    }
}

