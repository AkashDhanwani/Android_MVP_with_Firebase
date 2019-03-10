package com.akash.rohonfancy.view_presenter.place_order;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.akash.rohonfancy.R;
import com.akash.rohonfancy.view_presenter.about_us.AboutUs;

import java.net.URLEncoder;

public class PlaceOrder extends AppCompatActivity {

    TextView tvOrder;
    Button btnWhatsApp, btnGmail, btnMessage;

    String message = "Hey RF, I want to buy the following product: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
		
		 int orientaton = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    setRequestedOrientation(orientaton);

        tvOrder = findViewById(R.id.tvOrder);
        btnWhatsApp = findViewById(R.id.btnWhatsAppPlaceOrder);
        btnGmail = findViewById(R.id.btnGmailPlaceOrder);
        btnMessage = findViewById(R.id.btnMessagePlaceOrder);

        Intent intent = getIntent();

        final String order = intent.getStringExtra("review");

        tvOrder.setText(order);

        btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    PackageManager packageManager = getApplicationContext().getPackageManager();
                    Intent i = new Intent(Intent.ACTION_VIEW);

                    String url = "https://api.whatsapp.com/send?phone="+ "919324617755" +"&text=" + URLEncoder.encode(message+order, "UTF-8");
                    i.setPackage("com.whatsapp");
                    i.setData(Uri.parse(url));
                    if (i.resolveActivity(packageManager) != null) {
                        getApplicationContext().startActivity(i);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(PlaceOrder.this, "WhatsApp is not Installed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(PlaceOrder.this, "Select the corresponding Mail app", Toast.LENGTH_SHORT).show();
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
//                emailIntent.setData(Uri.parse("nitinkamra88.nk@gmail.com"));
//                emailIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "nitinkamra88.nk@gmail.com" });
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Mail for purchasing product");
                emailIntent.putExtra(Intent.EXTRA_TEXT, message+order);

                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+"9324617755"));
                intent.putExtra("sms_body", message+order);
                startActivity(intent);
            }
        });
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
            String number = "9324617755";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" +number));
            startActivity(Intent.createChooser(intent, "Call Nitin through..."));
        }

        if(id == R.id.about){
            Intent intent = new Intent(PlaceOrder.this, AboutUs.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
