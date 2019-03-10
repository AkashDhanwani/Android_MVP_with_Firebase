package com.akash.rohonfancy.view_presenter.base;

import com.akash.rohonfancy.model.DataManager;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    DataManager dataManager;
    private V mMvpView;

    public BasePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public DataManager getDataManager() {
        return this.dataManager;
    }
}
