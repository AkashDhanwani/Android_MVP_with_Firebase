package com.akash.rohonfancy.view_presenter.main;

import com.akash.rohonfancy.model.ListItem;
import com.akash.rohonfancy.view_presenter.base.MvpPresenter;

import java.util.List;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    List<ListItem> getItemList();
}
