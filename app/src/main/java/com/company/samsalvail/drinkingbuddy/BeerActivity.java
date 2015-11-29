package com.company.samsalvail.drinkingbuddy;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.andtinder.model.CardModel;
import com.andtinder.model.Orientations;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BeerActivity extends Activity {
    private int drinkCounter = 0;
    private String mongo_url = "https://api.mongolab.com/api/1/databases/drinkingbuddy/collections/beer?q={'id':";
    private String apiKey = "}&apiKey=q2_qGBqQCw838mVF3Pjsa9Ydhq12ZaMZ";
    private CardContainer mCardContainer2;
    private ArrayList<Drink> drinks = new ArrayList<>();
    private JSONObject jsonDrink;
    private SimpleCardStackAdapter adapter;

    private static int endTo = 5;
    private static int startFrom = 0;
    private static final int next = 5;
    private int prev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_drink);
        adapter = new SimpleCardStackAdapter(this);
        getDrinks();
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
                connection.setRequestProperty("User-agent", "Mozilla/4.0");

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

    public void onClickNext(final View v) {
        adapter.clearData();
        drinks.clear();
        endTo = endTo + next;
        getDrinks();
    }

    public void onClickRefresh(final View v) {
        if (startFrom == endTo) {
            prev = startFrom - 5;
        }
        startFrom = prev;
        drinkCounter = prev;
        adapter.clearData();
        drinks.clear();
        getDrinks();
    }
    private void getDrinks() {

        mCardContainer2 = (CardContainer) findViewById(R.id.layoutview);
        mCardContainer2.setOrientation(Orientations.Orientation.Ordered);
        try {

            while (startFrom < endTo) {
                RequestDatabase dbTask = new RequestDatabase();
                String url = (mongo_url + startFrom + apiKey);
                jsonDrink = dbTask.execute(url).get();
                if(!(jsonDrink.isNull("title"))) {
                    Drink drinkTmp = new Drink(jsonDrink.getInt("id"), jsonDrink.getString("category"),
                            jsonDrink.getString("title"), jsonDrink.getString("postedBy"),
                            jsonDrink.getString("link"), jsonDrink.getString("description"),
                            jsonDrink.getString("website"));
                    drinks.add(drinkTmp);
                }
                startFrom++;
            }

            for(int i = 0; i < drinks.size(); i ++) {
                RetrievePicture task = new RetrievePicture();
                CardModel beer = new CardModel(drinks.get(i).getTitle(), drinks.get(i).getDescription(), task.execute(drinks.get(i).getLink()).get());
                final int keeper = (drinks.size() - 1) - drinkCounter;
                drinkCounter ++;
                beer.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
                    //@Override
                    public void onLike() {
                        //Toast.makeText(BeerActivity.this, drinks.get(keeper).getTitle() + " liked", Toast.LENGTH_LONG).show();
                        new AlertDialog.Builder(BeerActivity.this)
                                .setTitle("Extra Information!")
                                .setMessage("Do you want to know more about this?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //send drink object
                                        Drink drinkTmp = drinks.get(keeper);
                                        Intent intent = new Intent(BeerActivity.this, TheDrinkActivity.class);
                                        intent.putExtra("drink", drinkTmp);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // do nothing
                                        dialog.cancel();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }

                    //@Override
                    public void onDislike() {
                        //Toast.makeText(BeerActivity.this, drinks.get(keeper).getTitle() + " disliked", Toast.LENGTH_LONG).show();
                    }
                });

                adapter.add(beer);
            }

            mCardContainer2.setAdapter(adapter);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }



}
