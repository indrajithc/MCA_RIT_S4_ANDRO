package com.indran.onlineregistration;

import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;

public class LoginActivity extends Activity {

    final String PREFS_NAME = "UserFile";
    DatabaseHelper databaseHelper;

    AutoCompleteTextView email, passwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        databaseHelper = new DatabaseHelper(LoginActivity.this);


        email = (AutoCompleteTextView) findViewById(R.id.email);
        passwd = (AutoCompleteTextView) findViewById(R.id.password);


    }

    public void login(View view) {

        int isLogin = 0;



        Cursor res = databaseHelper.getAllData();
        if(res.getCount() == 0) {
            // show message
            Log.d("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {



            String id = res.getString(res.getColumnIndex(DatabaseHelper.COL_1));
            String username = res.getString(res.getColumnIndex(DatabaseHelper.COL_3));
            String password = res.getString(res.getColumnIndex(DatabaseHelper.COL_6));

            if (username.equals(email.getText().toString()) && password.equals(passwd.getText().toString()))
                isLogin = Integer.parseInt(id);


        }


        if (isLogin > 0 ) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            settings.edit().putString("id", String.valueOf(isLogin)).commit();


            Intent intent = new Intent( LoginActivity.this, Home.class);
            startActivity(intent);
        } else {

            passwd.setError(" invalid username or password ");
        }



    }

    public void register(View view) {


        Intent intent = new Intent( LoginActivity.this, Registration.class);
        startActivity(intent);
    }
}

