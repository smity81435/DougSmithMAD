package com.example.douglassmith.norn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class Quest extends AppCompatActivity {
    private String url = "http://dpower3.wordpress.com";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        final ImageButton blog = findViewById(R.id.blogbutton);
        View.OnClickListener clicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadWebSite(v);
            }
        };
        blog.setOnClickListener(clicked);

    }

    public void loadWebSite(View view){
        //implicit intent
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

}
