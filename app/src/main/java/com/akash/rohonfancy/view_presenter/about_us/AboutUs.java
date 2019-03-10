package com.akash.rohonfancy.view_presenter.about_us;

import android.content.Intent;
import android.net.Uri;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.akash.rohonfancy.R;

public class AboutUs extends AppCompatActivity {

    Button btnGmail, btnLinkedin, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
		
				 int orientaton = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    setRequestedOrientation(orientaton);

        btnGmail = findViewById(R.id.btnGmailaboutme);
        btnLinkedin = findViewById(R.id.btnLinkedin);
        btnCall = findViewById(R.id.btncallaboutme);

        btnGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
//                emailIntent.setData(Uri.parse("nitinkamra88.nk@gmail.com"));
//                emailIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "akash.developer11@gmail.com" });
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Mail to hire for a Project");

                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });

        btnLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.linkedin.com/in/akash-dhanwani-235218145/");
                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "9561414485";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" +number));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.call_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.call2){
            String number = "9324617755";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" +number));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
