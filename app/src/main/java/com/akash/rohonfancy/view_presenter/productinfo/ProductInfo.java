package com.akash.rohonfancy.view_presenter.productinfo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akash.rohonfancy.view_presenter.about_us.AboutUs;
import com.akash.rohonfancy.view_presenter.image_detail.ImageDetail;
import com.akash.rohonfancy.view_presenter.place_order.PlaceOrder;
import com.akash.rohonfancy.R;

import java.net.URLEncoder;
import java.util.ArrayList;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class ProductInfo extends AppCompatActivity implements ProductInfoMvpView {

    FlipperLayout flipperLayout;
    TextView tvProductName, tvPriceInfo, tvDescInfo, tvSizeInfo;
    Button btnWhatsApp, btnGmail, btnMessage, btnPlaceOrder;
    EditText userSize, userColor, userQuantity;

    Bundle bundle;
    ArrayList<String> path;

    int num_of_pages;

   // String message = "Hey RF, I want to buy the following product: ";
    //String lastLine = "Kindly give the details about colours available";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
		
        int orientaton = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientaton);

        if(!isConnectedToInternet(ProductInfo.this)) {
            showSnackBar("No Internet Connection", (LinearLayout) findViewById(R.id.llproductInfo));
        }
        else mainMethod();

    }
    public void mainMethod() {

        flipperLayout = findViewById(R.id.flipper_layout);
        tvProductName = findViewById(R.id.tvProductName);
        tvDescInfo = findViewById(R.id.tvDescInfo);
        tvPriceInfo = findViewById(R.id.tvPriceInfo);
        tvSizeInfo = findViewById(R.id.tvSizeInfo);
        btnWhatsApp = findViewById(R.id.btnWhatsApp);
        btnGmail = findViewById(R.id.btnGmail);
        btnMessage = findViewById(R.id.btnMessage);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        userSize = findViewById(R.id.userSize);
        userColor = findViewById(R.id.userColor);
        userQuantity = findViewById(R.id.userQuantity);

        bundle = getIntent().getExtras();

        tvProductName.setText(bundle.getString("namepro"));
        tvPriceInfo.setText("\u20B9 "+bundle.getString("price"));
        tvDescInfo.setText(bundle.getString("desc"));
        tvSizeInfo.setText(bundle.getString("size"));
        path = bundle.getStringArrayList("imagePath");
        num_of_pages = path.size();

        for (int i = 0; i < num_of_pages; i++) {
            final int j = i;
            FlipperView view = new FlipperView(getBaseContext());
            view.setImageUrl(path.get(i))
                    .setImageScaleType(ImageView.ScaleType.CENTER_INSIDE) //You can use any ScaleType
                    .setDescription(getString(R.string.app_name))
                    .setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                        @Override
                        public void onFlipperClick(FlipperView flipperView) {
                            Intent intent = new Intent(ProductInfo.this, ImageDetail.class);
                            intent.putExtra("url", path.get(j));
                            startActivity(intent);
                        }
                    });
            // flipperLayout.setScrollTimeInSec(3); //setting up scroll time, by default it's 3 seconds
            //flipperLayout.getScrollTimeInSec(); //returns the scroll time in sec
            //flipperLayout.getCurrentPagePosition(); //returns the current position of pager
            flipperLayout.addFlipperView(view);
        }

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String color, quantity;

                if(userColor.getText().toString().length() == 0)
                    color = "Not mentioned";
                else
                    color = userColor.getText().toString();

                if (userQuantity.getText().toString().length() == 0)
                    quantity = "Not Mentioned";
                else
                    quantity = userQuantity.getText().toString();

                if (userSize.getText().toString().length() == 0){
                    userSize.setError("Enter size");
                    userSize.requestFocus();
                    return;
                }

                String productInfo = "Product Name: "+bundle.getString("namepro")+"\n"+
                        "Priced at: "+bundle.getString("price")+"\n"+
                        "Size: "+userSize.getText().toString()+"\n"+
                        "Colour: "+color+"\n"+
                        "Quantity: "+quantity+"\n";



                Intent buyIntent = new Intent(ProductInfo.this, PlaceOrder.class);
                buyIntent.putExtra("review", productInfo);
                startActivity(buyIntent);
            }
        });

        btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (userSize.getText().toString().length() == 0){
//                    userSize.setError("Enter size");
//                    userSize.requestFocus();
//                    return;
//                }
//
//                String productInfo = "Product Name: "+bundle.getString("namepro")+"\n"+
//                        "Priced at: "+bundle.getString("price")+"\n"+
//                        "Size: "+userSize.getText().toString();
//
//                String send = message+"\n"+productInfo+"\n";



//                Intent sendIntent = new Intent("android.intent.action.MAIN");
//                sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
//                sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators("919324617755")+"@s.whatsapp.net");
//                sendIntent.putExtra(Intent.EXTRA_TEXT, send);
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.setPackage("com.whatsapp");
//                sendIntent.setType("text/plain");
//                startActivity(sendIntent);

                try {

                    PackageManager packageManager = getApplicationContext().getPackageManager();
                    Intent i = new Intent(Intent.ACTION_VIEW);

                    String url = "https://api.whatsapp.com/send?phone="+ "919324617755" +"&text=" + URLEncoder.encode("", "UTF-8");
                    i.setPackage("com.whatsapp");
                    i.setData(Uri.parse(url));
                    if (i.resolveActivity(packageManager) != null) {
                        getApplicationContext().startActivity(i);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(ProductInfo.this, "WhatsApp is not Installed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (userSize.getText().toString().length() == 0){
//                    userSize.setError("Enter size");
//                    userSize.requestFocus();
//                    return;
//                }
//
//                String productInfo = "Product Name: "+bundle.getString("namepro")+"\n"+
//                        "Priced at: "+bundle.getString("price")+"\n"+
//                        "Size: "+userSize.getText().toString();
//
//                String send = message+"\n"+productInfo+"\n";

                Toast.makeText(ProductInfo.this, "Select the corresponding Mail app", Toast.LENGTH_SHORT).show();
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
//                emailIntent.setData(Uri.parse("nitinkamra88.nk@gmail.com"));
//                emailIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "nitinkamra88.nk@gmail.com" });
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Mail for purchasing product");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, send);

                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (userSize.getText().toString().length() == 0){
//                    userSize.setError("Enter size");
//                    userSize.requestFocus();
//                    return;
//                }
//
//                String productInfo = "Product Name: "+bundle.getString("namepro")+"\n"+
//                        "Priced at: "+bundle.getString("price")+"\n"+
//                        "Size: "+userSize.getText().toString();
//
//                String send = message+"\n"+productInfo+"\n";

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+"9324617755"));
                startActivity(intent);
            }
        });
    }

    public void showSnackBar(String string, LinearLayout linearLayout)
    {
        Snackbar.make(linearLayout, string, Snackbar.LENGTH_INDEFINITE).
                setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!isConnectedToInternet(ProductInfo.this)){
                            showSnackBar("No Internet Connection",(LinearLayout) findViewById(R.id.llproductInfo));
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

        Drawable drawable = item.getIcon();
        if(drawable instanceof Animatable){
            ((Animatable) drawable).start();
        }

        int id = item.getItemId();

        if(id == R.id.call){
            String number = "9324617755";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" +number));
            startActivity(Intent.createChooser(intent, "Call Nitin through..."));
        }

        if(id == R.id.about){
            Intent intent = new Intent(ProductInfo.this, AboutUs.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
