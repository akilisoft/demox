package com.akilisoft.demox.ui.login;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.akilisoft.demox.ui.login.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TableSQLiteController extends DatabaseHandler {

    public TableSQLiteController(Context context) {
        super(context);
    }


    public boolean userCreate(String nom, String prenom, String phone, String sexe, String email, String password, String role, String country,String city,String picture) {
        ContentValues values = new ContentValues();

        values.put(User.COLUMN_NAME, nom);
        values.put(User.COLUMN_LASTNAME, prenom);
        values.put(User.COLUMN_PHONE, phone);
        values.put(User.COLUMN_GENDER, sexe);
        values.put(User.COLUMN_EMAIL, email);
        values.put(User.COLUMN_PASSWORD, password);
        values.put(User.COLUMN_TYPE, role);
        values.put(User.COLUMN_COUNTRY_RESIDENCE, country);
        values.put(User.COLUMN_CITY_RESIDENCE, city);
        values.put(User.COLUMN_PICTURE, picture);


        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert(User.TABLE_NAME, null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public boolean auth(String email, String password) {


        String sql = "SELECT "+User.COLUMN_PASSWORD+" FROM "+User.TABLE_NAME+" WHERE "+User.COLUMN_EMAIL+" = " +"'"+ email+"'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            String reponse = cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD));
            if(!reponse.isEmpty() && reponse.equals(password))
                return true;
        }

        cursor.close();
        db.close();
        return false;
    }


}
