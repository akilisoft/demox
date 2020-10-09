package com.akilisoft.demox.ui.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import  com.akilisoft.demox.ui.login.User;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "demox.sqlite";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //First created parent table , independante table

        db.execSQL(User.CREATE_TABLE);
        //db.execSQL(Symptom.CREATE_TABLE);
        //db.execSQL(Prevention.CREATE_TABLE);
        //Second created child table, Table with FOREIGN KEY


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // First Drop Table with Foreign Key

        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        //db.execSQL("DROP TABLE IF EXISTS " + Symptom.TABLE_NAME);
        //db.execSQL("DROP TABLE IF EXISTS " + Prevention.TABLE_NAME);

        onCreate(db);
    }

}
