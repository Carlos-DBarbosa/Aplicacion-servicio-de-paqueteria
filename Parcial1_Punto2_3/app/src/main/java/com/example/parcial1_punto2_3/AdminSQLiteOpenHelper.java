package com.example.parcial1_punto2_3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table paquetes(id integer primary key autoincrement, nombreP text, peso integer, continente text, pais text, costoE integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists paquetes");
        db.execSQL("create table paquetes(id integer primary key autoincrement, nombreP text, peso integer, continente text, pais text, costoE integer)");
    }
}
