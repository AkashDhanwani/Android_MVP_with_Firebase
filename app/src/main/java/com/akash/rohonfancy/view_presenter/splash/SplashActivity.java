package com.akash.rohonfancy.view_presenter.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.akash.rohonfancy.view_presenter.main.MainActivity;
import com.akash.rohonfancy.MainClass;
import com.akash.rohonfancy.R;
import com.akash.rohonfancy.model.DataManager;

public class SplashActivity extends Activity implements SplashMvpView{

    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        DataManager dataManager = ((MainClass)getApplication()).getDataManager();

        splashPresenter = new SplashPresenter(dataManager);
        splashPresenter.onAttach(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                splashPresenter.openActivity();
            }
        }).start();
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
