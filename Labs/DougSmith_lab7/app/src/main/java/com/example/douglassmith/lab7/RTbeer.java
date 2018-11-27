package com.example.douglassmith.lab7;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class RTbeer extends AppCompatActivity {
    private String brewery;
    private String breweryURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtbeer);
        Intent intent = getIntent();
        brewery = intent.getStringExtra("breweryTitle");
        breweryURL = intent.getStringExtra("brewerySugURL");
        Log.i("Brewery Recieved", brewery);
        Log.i("URL Received", breweryURL);

        TextView messageView = findViewById(R.id.breweryTitle);
        messageView.setText("You should check out " + brewery);
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
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(breweryURL));
        startActivity(intent);
    }
}
