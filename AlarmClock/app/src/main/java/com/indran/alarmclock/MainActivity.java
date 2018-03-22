package com.indran.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;


import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    TimePicker time;
    TextView text;
    private PendingIntent pendingIntent;
    AlarmManager alarmManager;
    ToggleButton button;

    private static MainActivity inst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = (TimePicker) findViewById(R.id.time);


        text = (TextView) findViewById(R.id.text);
        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.button);

        button = (ToggleButton) findViewById(R.id.button);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);



    }



    public static MainActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }


    public void onTClicked(View view) {
        if (button.isChecked()) {
            Log.d("ALARM", "Alarm On");
            setAlarmText("Alarm On");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, time.getCurrentHour());
            calendar.set(Calendar.MINUTE, time.getCurrentMinute());
            Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        } else {




            int min = 1;
            int max = 9;



            setAlarmText("Alarm canceled");
            //setAlarmText("You clicked a " + " canceled");


            alarmManager.cancel(pendingIntent);
            setAlarmText("");
            Log.d("MyActivity", "Alarm Off");
        }
    }

    public void setAlarmText(String alarmText) {
        text.setText(alarmText);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("MyActivity", "on Destroy");
    }
}
