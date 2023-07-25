package com.ramazansimsir.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            // veritabanı açma
            SQLiteDatabase database= this.openOrCreateDatabase("Players",MODE_PRIVATE,null);
            // tablo oluşturma
            database.execSQL("CREATE TABLE IF NOT EXISTS  players(id INTEGER  PRIMARY KEY ,name VARCHAR,age INTEGER)");

            // tabloya veri eklemek
            //database.execSQL("INSERT INTO players (name,age) VALUES('İcardi',30)");
            //database.execSQL("INSERT INTO players (name,age) VALUES('Lars',40)");
            //database.execSQL("INSERT INTO players (name,age) VALUES('Kirk',55)");
            // veri okumak için cursor
          //  database.execSQL("UPDATE players SET name='Ramazan' WHERE name='Lars'");
               database.execSQL("DELETE FROM players WHERE id=3");
            Cursor cursor=database.rawQuery("SELECT * FROM players",null);
           // Cursor cursor=database.rawQuery("SELECT * FROM players WHERE age>39",null);
            // Cursor cursor=database.rawQuery("SELECT * FROM players WHERE name='James',null);
            // k ile baslayan isimler
            //Cursor cursor=database.rawQuery("SELECT * FROM players WHERE name  LIKE 'K%',null);
            int nameIn=cursor.getColumnIndex("name");
            int ageIn=cursor.getColumnIndex("age");
            int idIx=cursor.getColumnIndex("id");

            while (cursor.moveToNext()){
                System.out.println("Name: "+cursor.getString(nameIn));
                System.out.println("Age: "+cursor.getInt(ageIn));
                System.out.println("Id: " +cursor.getInt(idIx));
            }
            cursor.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }



    }
}