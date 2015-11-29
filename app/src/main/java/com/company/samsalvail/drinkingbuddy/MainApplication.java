package com.company.samsalvail.drinkingbuddy;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by Kamil on 2015-11-29.
 */
public class MainApplication extends Application {

    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
