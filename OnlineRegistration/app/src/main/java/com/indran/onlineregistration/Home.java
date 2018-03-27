package com.indran.onlineregistration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    final String PREFS_NAME = "UserFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    public void registration(View view) {
    }

    public void update(View view) {

        Intent intent = new Intent( Home.this, Update.class);

        startActivity(intent);

    }

    public void exit(View view) {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        settings.edit().remove("id").commit();

        Intent intent = new Intent( Home.this, MainActivity.class);

        startActivity(intent);
    }
}
