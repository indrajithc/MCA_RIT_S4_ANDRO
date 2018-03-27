package com.indran.onlineregistration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    final String PREFS_NAME = "UserFile";
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        databaseHelper = new DatabaseHelper(MainActivity.this);

        doFirst();
    }


    private void doFirst (){








        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        Intent login = null;

        if(settings.contains("id") ){
            login = new Intent(getApplicationContext(),Home.class);



        }  else {
            login = new Intent(getApplicationContext(),LoginActivity.class);

        }

        startActivity(login);






    }
}
