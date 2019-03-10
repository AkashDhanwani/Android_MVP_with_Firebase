package com.akash.rohonfancy.model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper {

    DatabaseReference databaseReference;

    List<ListItem> itemList;

    public FirebaseHelper(){
        itemList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("UploadedData");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        ListItem listItem = postSnapshot.getValue(ListItem.class);
                        itemList.add(listItem);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.toException().printStackTrace();
            }
        });
    }

    public List<ListItem> getItemList() {
        return itemList;
    }
}
