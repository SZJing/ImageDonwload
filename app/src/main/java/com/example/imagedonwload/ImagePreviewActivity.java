package com.example.imagedonwload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImagePreviewActivity extends AppCompatActivity {

    private ImageView imageView;
    private String Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        imageView = (ImageView) findViewById(R.id.image_preview);
        Bundle bundle = getIntent().getExtras();
        Image = bundle.getString("ImageUrl");
        Glide.with(this).load(Image).into(imageView);
    }
}
