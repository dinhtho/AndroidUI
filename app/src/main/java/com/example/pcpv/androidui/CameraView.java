package com.example.pcpv.androidui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraView extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "CameraView";
    private String imagePath;
    private static int CAMERA = 1;
    private static int REQUEST_PERMISSION_CAMERA_CODE = 1;
    private Button btTakePicture;
    private Button btSharePhoto;
    private ImageView ivCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_camera_view);
        btTakePicture = findViewById(R.id.bt_take_picture);
        ivCamera = findViewById(R.id.iv_camera);
        btSharePhoto = findViewById(R.id.bt_share_photo);

        btSharePhoto.setOnClickListener(this);

        btTakePicture.setOnClickListener(this);


    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        // save object or field on screen orientation and before camera is open
//        // changes
//        outState.putString("image_path", imagePath);
//        Log.i(TAG, "onSaveInstanceState: ");
//    }
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        // get the field when camera is close and back to main activity
//        imagePath = savedInstanceState.getString("image_path");
//        Log.i(TAG, "onRestoreInstanceState: "+imagePath);
//    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(CameraView.this, new String[]{Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CAMERA_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                File imgFile = new File(imagePath);
                Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                ivCamera.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CAMERA_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            }
        }
    }

    private void openCamera() {
        // custom output file directory
        File storageDir = CameraView.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (storageDir == null) {
            return;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + ".jpg";
        imagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
        File file = new File(imagePath);
        Uri outputFileUri = Uri.fromFile(file);
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        // change default intent on EXTRA_OUTPUT
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(cameraIntent, CAMERA);
    }

    private void sharePhoto(String imagePath) {
        if (imagePath == null) {
            Toast.makeText(CameraView.this, "Please take photo", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(imagePath);
        Uri imageUri = Uri.fromFile(file);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello");
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("image/jpeg");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "send"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_take_picture:
                requestCameraPermission();
                break;
            case R.id.bt_share_photo:
                sharePhoto(imagePath);
                break;
        }
    }
}
