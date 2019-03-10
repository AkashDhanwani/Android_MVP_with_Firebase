package com.akash.rohonfancy.view_presenter.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.content.pm.ActivityInfo;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.akash.rohonfancy.view_presenter.about_us.AboutUs;
import com.akash.rohonfancy.MainClass;
import com.akash.rohonfancy.R;
import com.akash.rohonfancy.model.DataManager;
import com.akash.rohonfancy.model.ListItem;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MainActivity extends Activity implements MainMvpView {

    MainMvpPresenter mainMvpPresenter;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    ProgressDialog progressDialog;

    DatabaseReference databaseReference;

    List<ListItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		 int orientaton = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
		 setRequestedOrientation(orientaton);

		 DataManager dataManager = ((MainClass)getApplication()).getDataManager();
		 mainMvpPresenter = new MainPresenter(dataManager);
		 mainMvpPresenter.onAttach(this);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.rf_logo);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);

        if(!isConnectedToInternet(MainActivity.this)){
            showSnackBar("No Internet Connection", (LinearLayout) findViewById(R.id.llmainActivity));

//            //LinearLayOut Setup
//            LinearLayout linearLayout= findViewById(R.id.llmainActivity);
//
////ImageView Setup
//            ImageView imageView = new ImageView(this);
//
////setting image resource
//            imageView.setImageResource(R.drawable.nointernet);
//
////setting image position
//            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT));
//
////adding view to layout
//            linearLayout.addView(imageView);
////make visible to program
//            setContentView(linearLayout);
        }

        else {
            mainMethod();
        }

    }

    private void mainMethod() {

//        progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setMessage("Please wait...");
//        progressDialog.show();
//        progressDialog.setCancelable(false);

        recyclerView = findViewById(R.id.rvMain);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = mainMvpPresenter.getItemList();

        if(itemList.isEmpty()){
            Log.d("Firebaba", "In mainActivity firebase helper");
            Toast.makeText(this, "Nothingness", Toast.LENGTH_SHORT).show();
//            //dismissing the progress dialog
////                    progressDialog.dismiss();
//
//                    LinearLayout linearLayout = new LinearLayout(MainActivity.this);
//                    setContentView(linearLayout);
//                    linearLayout.setOrientation(LinearLayout.VERTICAL);
//                    // linearLayout.setGravity(LinearLayout.);
//
//                    TextView textView = new TextView(MainActivity.this);
//                    textView.setText(getString(R.string.noProduct));
//                    //textView.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
//                    linearLayout.addView(textView);
        }

//        databaseReference = FirebaseDatabase.getInstance().getReference("UploadedData");
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                if (dataSnapshot.exists()){
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    //dismissing the progress dialog
//                    progressDialog.dismiss();
//
//                    ListItem listItem = postSnapshot.getValue(ListItem.class);
//                    itemList.add(listItem);
//                    adapter.notifyDataSetChanged();
//                }
//                }else{
//                    //dismissing the progress dialog
//                    progressDialog.dismiss();
//
//                    LinearLayout linearLayout = new LinearLayout(MainActivity.this);
//                    setContentView(linearLayout);
//                    linearLayout.setOrientation(LinearLayout.VERTICAL);
//                    // linearLayout.setGravity(LinearLayout.);
//
//                    TextView textView = new TextView(MainActivity.this);
//                    textView.setText(getString(R.string.noProduct));
//                    //textView.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
//                    linearLayout.addView(textView);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                databaseError.toException().printStackTrace();
//            }
//        });
        adapter = new Adapter(itemList, getApplicationContext());

        recyclerView.setAdapter(adapter);
    }

    public void showSnackBar(String string, LinearLayout linearLayout)
    {
        Snackbar.make(linearLayout, string, Snackbar.LENGTH_INDEFINITE).
                        setAction("Retry", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(!isConnectedToInternet(MainActivity.this)){
                                    showSnackBar("No Internet Connection",(LinearLayout) findViewById(R.id.llmainActivity));

                                }
                                else mainMethod();
                            }
                        }).show();
    }

    private boolean isConnectedToInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.call){
            String number = "919324617755";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" +number));
            startActivity(Intent.createChooser(intent, "Call Nitin through..."));
        }

        if(id == R.id.about){
            Intent intent = new Intent(MainActivity.this, AboutUs.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
