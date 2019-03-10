package com.akash.rohonfancy.view_presenter.splash;

import com.akash.rohonfancy.view_presenter.base.MvpPresenter;

public interface SplashMvpPresenter <V extends SplashMvpView> extends MvpPresenter<V> {

    void openActivity();

}
