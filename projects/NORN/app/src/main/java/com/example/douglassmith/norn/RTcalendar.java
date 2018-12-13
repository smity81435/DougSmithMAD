package com.example.douglassmith.norn;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RTcalendar extends AppCompatActivity{
    private static final String neededDate = "MainActivity";
    private TextView usedDate; //DATE TEXT
    public int Month = 0;
    public int Year = 0;
    public int Day = 0;
    public String DateString;
    //private Button openPickerButton; //PICK DATE BUTTON
    //private Button nornButton;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtcalendar);
        final Button openPickerButton = findViewById(R.id.openPickerButton);
        View.OnClickListener buttonClick = new View.OnClickListener() {
            public void onClick(View view) {
                pickerClicked(view);
            }
        };
        openPickerButton.setOnClickListener(buttonClick);

        final Button nornButton = findViewById(R.id.getFortuneButton);
        View.OnClickListener nornClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Month == 0 && Day == 0 && Year == 0){
                    Log.i("myApp", "Somefing is vewy vewy wong.");

                }
                Log.i("myApp","Button Pushed");
                Intent intent = new Intent();
                intent.putExtra("month", Month);
                intent.putExtra("day", Day);
                intent.putExtra("year", Year);
                intent.putExtra("dateString",DateString);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        };
        nornButton.setOnClickListener(nornClick);

        SimpleDateFormat formatter
                = new SimpleDateFormat ("EEE, MMM d, ''yy" );
        Date currentTime_1 = new Date();

        String dateString = formatter.format(currentTime_1);
        //Log.i(neededDate,"Date:"+dateString);
        usedDate = (TextView) findViewById(R.id.selectedDate);
        usedDate.setText(dateString);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=month+1;
                Log.d(neededDate, "Date: date" + year + "/" + month + "/" + day);
                String date = month+"/"+day+"/"+year;
                usedDate.setText(date);
                Year = year;
                Month = month;
                Day = day;
                DateString = date;
            }

        };

    }
    public void pickerClicked(View view){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        Year = year;
        int month = calendar.get(Calendar.MONTH);
        Month = month;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Day = day;
        DateString = month+"/"+day+"/"+year;
        DatePickerDialog picker = new DatePickerDialog(
                RTcalendar.this,
                android.R.style.Theme_Black,
                mDateSetListener,
                year,month,day);
        picker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        picker.show();
    }


}
