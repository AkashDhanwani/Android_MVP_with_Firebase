package com.akash.rohonfancy.view_presenter.base;

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

}
