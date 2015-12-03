package com.example.ljs93kr.sqlitetest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ljs93kr on 2015-12-02.
 */
public class DBManager extends SQLiteOpenHelper{



    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBManager","this is onCreate");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DBManager", "this is onUpdate");
    }

    public void createTable(String tableName, String columns){
        SQLiteDatabase db = getWritableDatabase();
        String _query = "CREATE TABLE IF NOT EXISTS "+tableName+" "+columns;
        db.execSQL(_query);
        db.close();
    }
    public void dropTable(String _query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void insert(String tableName, String name, String ingredient, String cal) {
        SQLiteDatabase db = getWritableDatabase();
        String _query = "insert into "+tableName+" values(null, '"+name+"','" +ingredient+ "', " + cal + ", DATETIME('NOW', 'LOCALTIME' ));";
        db.execSQL(_query);
        db.close();
    }

    public void update(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String tableName, String dele, int flag) {
        SQLiteDatabase db = getWritableDatabase();
        String _query;
        switch(flag){
            case DBSupport.BY_ID:
                _query = "delete from "+tableName+" where _id = '" + dele + "';";
                break;
            case DBSupport.BY_NAME:
                _query = "delete from "+tableName+" where name = '" + dele + "';";
                break;
            case DBSupport.BY_INGREDITENT:
                _query = "delete from "+tableName+" where ingredient = '" + dele + "';";
                break;
            default:
                db.close();
                return;

        }
        db.execSQL(_query);
        db.close();
    }

    public String PrintData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        try{
            Cursor cursor = db.rawQuery("select * from EATEN_FOOD", null);
            while (cursor.moveToNext()) {
                str += cursor.getInt(cursor.getColumnIndex("_id"))
                        + " : name : "
                        + cursor.getString(cursor.getColumnIndex("name"))
                        + " : ingredient "
                        + cursor.getString(cursor.getColumnIndex("ingredient"))
                        + ", calory = "
                        + cursor.getInt(cursor.getColumnIndex("calory"))
                        + ", time : "
                        + cursor.getString(cursor.getColumnIndex("time"))
                        + "\n";
            }

            return str;
        }catch(Exception e){
            return "no table";
        }


    }
}
