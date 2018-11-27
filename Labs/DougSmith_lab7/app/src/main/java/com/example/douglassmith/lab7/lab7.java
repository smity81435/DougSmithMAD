package com.example.douglassmith.lab7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;


public class lab7 extends AppCompatActivity {
    private Brewery myBrewery = new Brewery();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab7);
        final Button button = findViewById(R.id.button);
        View.OnClickListener buttonclick = new View.OnClickListener(){
            public void onClick(View view){
                findBeer(view);
            }
        };
        button.setOnClickListener(buttonclick);

    }
    public void findBeer(View view){
        Spinner crowdSpinner = findViewById(R.id.spinner);
        Integer crowd = crowdSpinner.getSelectedItemPosition();
        myBrewery.setBrewery(crowd);
        String suggestedBrewery = myBrewery.getBrewery();
        String suggestedBreweryURL = myBrewery.getBreweryUrl();
        Log.i("shop", suggestedBrewery);
        Log.i("url", suggestedBreweryURL);

        Intent intent = new Intent(this, RTbeer.class);
        intent.putExtra("breweryTitle",suggestedBrewery);
        intent.putExtra( "brewerySugURL", suggestedBreweryURL);
        startActivity(intent);
    }
}
