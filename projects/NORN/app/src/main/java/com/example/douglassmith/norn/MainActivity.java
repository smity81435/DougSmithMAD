package com.example.douglassmith.norn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleDateFormat formatter
                = new SimpleDateFormat ("EEE, MMM d, ''yy" );
        Date currentTime_1 = new Date();
        String dateString = formatter.format(currentTime_1);
        TextView usedDate = (TextView) findViewById(R.id.currentDate);
        usedDate.setText(dateString);
    }
    public void sendMessage(View view)
    {
        Intent intent = new Intent(MainActivity.this, RTcalendar.class);
        startActivity(intent);
    }
}

