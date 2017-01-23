package com.example.cashmove;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.content.Context;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
	public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
        super(context, nombre, factory, version);
	}
	
	public void onCreate(SQLiteDatabase db) {
     db.execSQL("create table casa_empeno(cod integer primary key,nombre text,resivir integer,mes integer, interes integer,tot integer)");
        db.execSQL("create table registros(codigo integer primary key,nuevo text,inter integer)");
        
    }
	

	
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
       db.execSQL("drop table if exists casa_empeno");
        
       db.execSQL("create table casa_empeno(cod integer primary key ,nombre text,evaluada integre,resivir integer,mes integer, interes integer,tot integer)");
      
       db.execSQL("drop table if exists registros");
       
        db.execSQL("create table registros(codigo integer primary key, nuevo text,inter integer)");
    }
}
