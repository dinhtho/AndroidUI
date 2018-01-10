package com.example.pcpv.androidui;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FrameLayoutExample extends AppCompatActivity {

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);

        LinearLayout linearLayout =findViewById(R.id.layout);
        linearLayout.setAlpha((float) 0.4);

        FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
        frame.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int width = frame.getMeasuredWidth();
        int height = frame.getMeasuredHeight();
        Toast.makeText(getApplicationContext(),"width="+width+"  height="+height,Toast.LENGTH_LONG).show();
    }
}
