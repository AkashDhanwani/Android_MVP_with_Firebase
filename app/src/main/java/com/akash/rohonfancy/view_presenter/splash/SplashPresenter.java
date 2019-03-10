package com.akash.rohonfancy.view_presenter.splash;

import com.akash.rohonfancy.model.DataManager;
import com.akash.rohonfancy.view_presenter.base.BasePresenter;
import com.akash.rohonfancy.view_presenter.base.MvpView;

public class SplashPresenter <V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V> {

    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }

    //This is actually not needed
    //You could have directly made intent to the main activity from splash as there is no exchange of data here
    @Override
    public void openActivity() {
        getMvpView().openMainActivity();
    }

}
