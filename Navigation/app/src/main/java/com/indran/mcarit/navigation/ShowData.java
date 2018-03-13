package com.indran.mcarit.navigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowData extends AppCompatActivity {
    TextView ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        ip = (TextView) findViewById(R.id.op);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");

        ip.setText(message);
    }

    public void goBack(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
