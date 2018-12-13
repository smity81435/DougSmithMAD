package com.example.douglassmith.norn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_GETDATE = 666;
    private Fortune myFortune = new Fortune();
//    private Button pickDateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set date string
        SimpleDateFormat formatter
                = new SimpleDateFormat ("EEE, MMM d, ''yy" );
        Date currentTime_1 = new Date();
        String dateString = formatter.format(currentTime_1);
        TextView usedDate = (TextView) findViewById(R.id.currentDate);
        usedDate.setText(dateString);
        //date button clicked
        final ImageButton pickDateButton = findViewById(R.id.cal);
        View.OnClickListener dateclick = new View.OnClickListener() {
            public void onClick(View v) {
                //explicit
                Intent intent = new Intent(MainActivity.this, RTcalendar.class);
                
                startActivityForResult(intent, REQUEST_CODE_GETDATE);
            }
        };
        pickDateButton.setOnClickListener(dateclick);

        //date button clicked
        final ImageButton pickQuestButton = findViewById(R.id.question);
        View.OnClickListener questclick = new View.OnClickListener() {
            public void onClick(View v) {
                //implicit
                Intent intent = new Intent(MainActivity.this, Quest.class);
                startActivity(intent);
            }
        };
        pickQuestButton.setOnClickListener(questclick);

        //pickDateButton = new ImageButton(this);

//        pickDateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dateButtonClicked();
//            }
//        });

    }
//    public void sendMessage(View view)
//    {
//        Intent intent = new Intent(MainActivity.this, RTcalendar.class);
//        startActivity(intent);
//    }

}

