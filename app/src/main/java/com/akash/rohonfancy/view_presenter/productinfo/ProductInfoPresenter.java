package com.akash.rohonfancy.view_presenter.productinfo;

import com.akash.rohonfancy.model.DataManager;
import com.akash.rohonfancy.view_presenter.base.BasePresenter;
import com.akash.rohonfancy.view_presenter.base.MvpView;

public class ProductInfoPresenter<V extends ProductInfoMvpView> extends BasePresenter<V> implements ProductInfoMvpPresenter<V> {


    public ProductInfoPresenter(DataManager dataManager) {
        super(dataManager);
    }


}
