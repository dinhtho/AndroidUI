package com.example.pcpv.androidui.ImageTranform;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.pcpv.androidui.R;

public class ImageTranformActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_tranform);

        ImageView imageView = findViewById(R.id.imageView);

        Resources res = this.getResources();
        // Image -> Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.background);

        // Image -> Drawable
        Drawable drawable = res.getDrawable(R.drawable.background);

        bitmap = CircleTransform.transform(bitmap);

        // Bitmap -> Drawable
        Drawable drawable1 = new BitmapDrawable(getResources(), bitmap);
        // Drawable -> Bitmap
        Bitmap bitmap1 = ((BitmapDrawable) drawable1).getBitmap();


        imageView.setImageBitmap(bitmap);


    }
}
