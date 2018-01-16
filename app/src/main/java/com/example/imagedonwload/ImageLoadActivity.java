package com.example.imagedonwload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ImageLoadActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageAdapter imageAdapter;
    String Fruit[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load);
        Bundle bundle = getIntent().getExtras();
        Fruit = bundle.getStringArray("list");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_image);
        imageAdapter = new ImageAdapter(Fruit);
        recyclerView.setLayoutManager(new LinearLayoutManager(ImageLoadActivity.this));
        recyclerView.setAdapter(imageAdapter);
    }
}
