package com.indran.onlineregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doFirst();
    }


    private void doFirst (){

        Intent intent = new Intent( MainActivity.this, Registration.class);
      //  intent.putExtra("message", ip.getText().toString());
        startActivity(intent);

    }
}
