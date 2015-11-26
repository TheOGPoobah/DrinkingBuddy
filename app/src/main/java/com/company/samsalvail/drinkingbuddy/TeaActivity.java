package com.company.samsalvail.drinkingbuddy;

import android.content.res.Resources;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;
import com.andtinder.model.Orientations;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TeaActivity extends Activity {

    private String mongo_url = "https://api.mongolab.com/api/1/databases/drinkingbuddy/collections/tea?q={'id':";
    private String apiKey = "}&apiKey=q2_qGBqQCw838mVF3Pjsa9Ydhq12ZaMZ";
    private CardContainer mCardContainer2;
    private int numOfThings = 5;
    private ArrayList<Drink> drinks = new ArrayList<>();
    private JSONObject jsonDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        mCardContainer2 = (CardContainer) findViewById(R.id.layoutview);
        mCardContainer2.setOrientation(Orientations.Orientation.Ordered);

        Resources r = getResources();

        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);
        try {

            for(int j = 0; j < numOfThings; j++) {
                RequestDatabase dbTask = new RequestDatabase();
                String url = (mongo_url + j + apiKey);
                jsonDrink = dbTask.execute(url).get();
                Drink drinkTmp = new Drink(jsonDrink.getInt("id"), jsonDrink.getString("category"),
                        jsonDrink.getString("title"), jsonDrink.getString("postedBy"),
                        jsonDrink.getString("link"), jsonDrink.getString("description"),
                        jsonDrink.getInt("likes"), jsonDrink.getInt("dislikes"));
                drinks.add(drinkTmp);
            }
            //Toast.makeText(this, tmpthing.getString("title"), Toast.LENGTH_LONG).show();
//            for(int j = 0; j < tmpthing.size(); j++) {
//                Toast.makeText(this, tmpthing.get(j).getString("title"), Toast.LENGTH_LONG).show();
//            }

            for(int i = 0; i < drinks.size(); i ++) {
                RetrievePicture task = new RetrievePicture();
                CardModel beer = new CardModel(drinks.get(i).getTitle(), drinks.get(i).getDescription(), task.execute(drinks.get(i).getLink()).get());
                beer.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
                    //@Override
                    public void onLike() {
                        Toast.makeText(TeaActivity.this, "Right", Toast.LENGTH_LONG).show();
                    }

                    //@Override
                    public void onDislike() {
                        Toast.makeText(TeaActivity.this, "Left", Toast.LENGTH_LONG).show();
                    }
                });

                adapter.add(beer);

                mCardContainer2.setAdapter(adapter);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pick_drink, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    private class RequestDatabase extends AsyncTask<String, Void, JSONObject> {
        private String tmp;
        private JSONObject obj;
        private String jsonString;

        protected JSONObject doInBackground(String... urls) {
            try {
                HttpURLConnection connection = (HttpURLConnection)new URL(urls[0]).openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                if(connection.getResponseCode() != 200) {
                    throw new RuntimeException("Failed: HTTP error code: " + connection.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()
                ));

                while ((tmp = br.readLine()) != null) {
                    jsonString = tmp;
                }

                jsonString = jsonString.replaceAll("[\\[\\]]", "");
                obj = new JSONObject(jsonString);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }


}
