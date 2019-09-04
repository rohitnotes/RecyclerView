package com.recycler.view.practical.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.recycler.view.practical.R;
import com.recycler.view.practical.constants.Constants;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra(Constants.EXTRA_NAME);
        String description = intent.getStringExtra(Constants.EXTRA_DESCRIPTION);
        String price = intent.getStringExtra(Constants.EXTRA_PRICE);
        String thumbnail = intent.getStringExtra(Constants.EXTRA_THUMBNAIL);

        TextView foodNameTextView = findViewById(R.id.food_name);
        TextView foodDescriptionTextView= findViewById(R.id.food_description);
        TextView foodPriceTextView = findViewById(R.id.food_price);
        ImageView itemImageView = findViewById(R.id.food_image_view);

        foodNameTextView.setText(name);
        foodDescriptionTextView.setText(description);
        foodPriceTextView.setText("Price : \u20B9 "+price);
        Glide.with(getApplicationContext()).load(thumbnail).apply(RequestOptions.centerCropTransform()).into(itemImageView);
    }
}