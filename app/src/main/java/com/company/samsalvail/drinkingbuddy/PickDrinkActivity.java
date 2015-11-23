package com.company.samsalvail.drinkingbuddy;

import android.content.Intent;
import android.content.res.Resources;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;
import com.andtinder.model.Orientations;

public class PickDrinkActivity extends Activity {

    private CardContainer mCardContainer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_drink);

        mCardContainer2 = (CardContainer) findViewById(R.id.layoutview);
        mCardContainer2.setOrientation(Orientations.Orientation.Ordered);

        Resources r = getResources();

        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);

        CardModel beer = new CardModel("Beer", "The party starter", r.getDrawable(R.drawable.beer));
        beer.setOnCardDismissedListener(new CardModel.OnCardDismissedListener ()  {
            //@Override
            public void onLike() {
                Toast.makeText(PickDrinkActivity.this, "Right", Toast.LENGTH_LONG).show();
            }

            //@Override
            public void onDislike() {
                Toast.makeText(PickDrinkActivity.this, "Left", Toast.LENGTH_LONG).show();
            }
        });

        CardModel cocktail = new CardModel("Cocktail", "The party starter", r.getDrawable(R.drawable.cocktail));
        cocktail.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
            //@Override
            public void onLike() {
                Toast.makeText(PickDrinkActivity.this, "Right", Toast.LENGTH_LONG).show();
            }

            //@Override
            public void onDislike() {
                Toast.makeText(PickDrinkActivity.this, "Left", Toast.LENGTH_LONG).show();
            }
        });

        CardModel coffee = new CardModel("Coffee", "The party starter", r.getDrawable(R.drawable.coffee));
        coffee.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
            //@Override
            public void onLike() {
                Toast.makeText(PickDrinkActivity.this, "Right", Toast.LENGTH_LONG).show();
            }

            //@Override
            public void onDislike() {
                Toast.makeText(PickDrinkActivity.this, "Left", Toast.LENGTH_LONG).show();
            }
        });

        CardModel energy = new CardModel("Energy", "The party starter", r.getDrawable(R.drawable.energy));
        energy.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
            //@Override
            public void onLike() {
                Toast.makeText(PickDrinkActivity.this, "Right", Toast.LENGTH_LONG).show();
            }

            //@Override
            public void onDislike() {
                Toast.makeText(PickDrinkActivity.this, "Left", Toast.LENGTH_LONG).show();
            }
        });

        CardModel sport = new CardModel("Sport", "The party starter", r.getDrawable(R.drawable.sport));
        beer.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
            //@Override
            public void onLike() {
                Toast.makeText(PickDrinkActivity.this, "Right", Toast.LENGTH_LONG).show();
            }

            //@Override
            public void onDislike() {
                Toast.makeText(PickDrinkActivity.this, "Left", Toast.LENGTH_LONG).show();
            }
        });

        CardModel soda = new CardModel("Soda", "The party starter", r.getDrawable(R.drawable.soda));
        soda.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
            //@Override
            public void onLike() {
                Toast.makeText(PickDrinkActivity.this, "Right", Toast.LENGTH_LONG).show();
            }

            //@Override
            public void onDislike() {
                Toast.makeText(PickDrinkActivity.this, "Left", Toast.LENGTH_LONG).show();
            }
        });

        CardModel tea = new CardModel("Tea", "The party starter", r.getDrawable(R.drawable.tea));
        tea.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
            //@Override
            public void onLike() {
                Toast.makeText(PickDrinkActivity.this, "Right", Toast.LENGTH_LONG).show();
            }

            //@Override
            public void onDislike() {
                Toast.makeText(PickDrinkActivity.this, "Left", Toast.LENGTH_LONG).show();
            }
        });

        adapter.add(beer);
        adapter.add(cocktail);
        adapter.add(coffee);
        adapter.add(energy);
        adapter.add(sport);
        adapter.add(soda);
        adapter.add(tea);

        mCardContainer2.setAdapter(adapter);
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
}
