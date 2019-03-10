package com.akash.rohonfancy;

import android.app.Application;

import com.akash.rohonfancy.model.DataManager;
import com.akash.rohonfancy.model.FirebaseHelper;

public class MainClass extends Application {

    DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseHelper firebaseHelper = new FirebaseHelper();

        dataManager = new DataManager(firebaseHelper);
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
