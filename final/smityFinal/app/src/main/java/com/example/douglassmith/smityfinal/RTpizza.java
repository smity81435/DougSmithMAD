package com.example.douglassmith.smityfinal;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class RTpizza extends AppCompatActivity {
    private String restaurant;
    private String restaurantURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtpizza);
        Intent intent = getIntent();
        restaurant = intent.getStringExtra("restaurantTitle");
        restaurantURL = intent.getStringExtra("restaurantSugURL");
        Log.i("Res Recieved", restaurant);
        Log.i("URL Received", restaurantURL);

        TextView messageView = findViewById(R.id.restaurantTitle);
        messageView.setText("You should check out " + restaurant);
        final ImageButton imageButton = findViewById(R.id.imageButton);
        View.OnClickListener imageButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadWebSite(v);
            }
        };
        imageButton.setOnClickListener(imageButtonClick);
    }

    public void loadWebSite(View view){
        //implicit intent
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(restaurantURL));
        startActivity(intent);
    }
}