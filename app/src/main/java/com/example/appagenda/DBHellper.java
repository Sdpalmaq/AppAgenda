package com.example.appagenda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHellper extends SQLiteOpenHelper {

    //Constructor
    public DBHellper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS contactos( id text(10) not null primary key,"+
                "nombre text(30), telefono text(10), direccion text(30), email text(30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){

    }
}
