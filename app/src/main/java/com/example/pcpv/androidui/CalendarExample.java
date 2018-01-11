package com.example.pcpv.androidui;

import android.app.DatePickerDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class CalendarExample extends AppCompatActivity {
    private static final String TAG = "CalendarExample";
    private DatePickerDialog mDatePickerDialog;
    private TextView textViewCalendar;
    private TextView textViewPickTime;
    private Calendar c;
    private int year,month,day;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_example);

        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);


        Button buttonCalendar = findViewById(R.id.bt_calendar);
        textViewCalendar = findViewById(R.id.tv_calendar);
        textViewPickTime = findViewById(R.id.tv_pickTime);
        buttonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        textViewPickTime = findViewById(R.id.tv_pickTime);
        TimePicker timePicker = findViewById(R.id.timePicker);
        timePicker.setHour(c.get(Calendar.HOUR));
        timePicker.setMinute(c.get(Calendar.MINUTE));
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
                textViewPickTime.setText(hourOfDay + ":" + minute);
            }
        });
    }

    private void showDatePicker() {

        mDatePickerDialog = new DatePickerDialog(this, myDateListener, year, month, day);
        mDatePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // arg1 = year
            // arg2 = month
            // arg3 = day
            Log.d(TAG, "date: " + arg1 + "/" + arg2 + "/" + arg3);
            textViewCalendar.setText(arg1 + "-" + (arg2 + 1) + "-" + arg3);
        }
    };
}
