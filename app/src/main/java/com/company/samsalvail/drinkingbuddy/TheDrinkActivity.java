package com.company.samsalvail.drinkingbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.widget.LikeView;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

public class TheDrinkActivity extends AppCompatActivity {

    private TextView title;
    private TextView description;
    private TextView postedBy;
    private ImageView image;
    LinearLayout btnLoginToLike;
    LikeView likeView;
    CallbackManager callbackManager;
    Drink drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_drink);

        Intent intent = getIntent();
        drink = (Drink) intent.getSerializableExtra("drink");

        try {
            title = (TextView) findViewById(R.id.title);
            description = (TextView) findViewById(R.id.description);
            postedBy = (TextView) findViewById(R.id.postedBy);
            image = (ImageView) findViewById(R.id.drinkPic);

            title.setText(drink.getTitle());
            description.setText(drink.getDescription());
            postedBy.setText("Posted By: " + drink.getPostedBy());
            Picasso.with(this).load(drink.getLink()).into(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        initInstances();
        initCallbackManager();
        refreshButtonsState();


    }

    private void initInstances() {
        btnLoginToLike = (LinearLayout) findViewById(R.id.btnLoginToLike);
        likeView = (LikeView) findViewById(R.id.likeView);
        likeView.setLikeViewStyle(LikeView.Style.STANDARD);
        likeView.setAuxiliaryViewPosition(LikeView.AuxiliaryViewPosition.INLINE);
        likeView.setObjectIdAndType(
                drink.getWebsite(),
                LikeView.ObjectType.OPEN_GRAPH);

        btnLoginToLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(TheDrinkActivity.this, Arrays.asList("public_profile"));
            }
        });
    }

    private void initCallbackManager() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                refreshButtonsState();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });
    }

    private void refreshButtonsState() {
        if (!isLoggedIn()) {
            btnLoginToLike.setVisibility(View.VISIBLE);
            likeView.setVisibility(View.GONE);
        } else {
            btnLoginToLike.setVisibility(View.GONE);
            likeView.setVisibility(View.VISIBLE);
        }
    }

    public boolean isLoggedIn() {
        return AccessToken.getCurrentAccessToken() != null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Handle Facebook Login Result
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
