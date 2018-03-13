package com.indran.mcarit.activitylifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        TextView op;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        op = (TextView) findViewById(R.id.op);

        op.setText( op.getText() + " \n > on  Create" );
    }


    @Override
    protected void onStart() {
        super.onStart();
        op.setText( op.getText() + " \n > on Start" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        op.setText( op.getText() + " \n > on Stop" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        op.setText( op.getText() + " \n > on Destroy" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        op.setText( op.getText() + " \n > on Pause" );
    }

    @Override
    protected void onResume() {
        super.onResume();
        op.setText( op.getText() + " \n > on Resume" );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        op.setText( op.getText() + " \n > on Restart" );
    }
}
