package com.khair.instgrammobileapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class profile_image extends AppCompatActivity {
    public static Bitmap bitmap=null;
    ImageView coverImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_image);
        coverImage=findViewById(R.id.coverImage);


        if (bitmap!=null)coverImage.setImageBitmap(bitmap);

    }
}