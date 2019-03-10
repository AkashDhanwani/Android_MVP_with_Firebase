package com.akash.rohonfancy.model;

import java.util.List;

public class DataManager {

    FirebaseHelper firebaseHelper;

    public List<ListItem> itemData;

    public DataManager(FirebaseHelper firebaseHelper){
        this.firebaseHelper = firebaseHelper;
    }

    public void setItemData(List<ListItem> itemData) {
        this.itemData = itemData;
    }

    public List<ListItem> getItemlistData(){
        return firebaseHelper.getItemList();
    }
}
