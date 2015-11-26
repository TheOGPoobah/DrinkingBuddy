package com.company.samsalvail.drinkingbuddy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TheDrinkActivity extends AppCompatActivity {

    private TextView title;
    private TextView description;
    private TextView postedBy;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_drink);
        Intent intent = getIntent();
        Drink drink = (Drink) intent.getSerializableExtra("drink");

        RetrievePicture task = new RetrievePicture();

        try {
            Bitmap drinkPic = task.execute(drink.getLink()).get();
            title = (TextView) findViewById(R.id.title);
            description = (TextView) findViewById(R.id.description);
            postedBy = (TextView) findViewById(R.id.postedBy);
            image = (ImageView) findViewById(R.id.drinkPic);

            title.setText(drink.getTitle());
            description.setText(drink.getDescription());
            postedBy.setText(drink.getPostedBy());
            image.setImageBitmap(drinkPic);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class RetrievePicture extends AsyncTask<String, Void, Bitmap> {
        private Bitmap x;

        protected Bitmap doInBackground(String... urls) {
            try {

                HttpURLConnection connection = (HttpURLConnection)new URL(urls[0]).openConnection();
                connection.setRequestProperty("User-agent","Mozilla/4.0");

                connection.connect();
                InputStream input = connection.getInputStream();

                x = BitmapFactory.decodeStream(input);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return x;
        }
    }


}
