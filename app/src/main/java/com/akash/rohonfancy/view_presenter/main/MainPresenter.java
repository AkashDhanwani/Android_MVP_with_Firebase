package com.akash.rohonfancy.view_presenter.main;

import com.akash.rohonfancy.model.DataManager;
import com.akash.rohonfancy.model.ListItem;
import com.akash.rohonfancy.view_presenter.base.BasePresenter;
import com.akash.rohonfancy.view_presenter.base.MvpView;

import java.util.List;

public class MainPresenter <V extends MainMvpView & MvpView> extends BasePresenter<V> implements MainMvpPresenter<V>{

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public List<ListItem> getItemList() {
        return getDataManager().getItemlistData();
    }
}
