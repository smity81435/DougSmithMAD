package com.example.douglassmith.lab6;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Lab6 extends AppCompatActivity {

    //strings
    String perfectDog;
    String deemer;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab6);
    }

    //compute function
    public void computeBreed(View view) {

        //imageview
        ImageView img = findViewById(R.id.dogPic);
        TextView deem = findViewById(R.id.deem);

        //checkboxes
        CheckBox guard =findViewById(R.id.box1);
        boolean guarding = guard.isChecked();

        CheckBox lap =findViewById(R.id.box2);
        boolean lapping = lap.isChecked();

        CheckBox hunt =findViewById(R.id.box3);
        boolean hunting = hunt.isChecked();


        //Spinner
        Spinner size = findViewById(R.id.spinner);
        String sizeType = String.valueOf(size.getSelectedItem());

        //Toggle button
        ToggleButton toggle = findViewById(R.id.toggleButton);
        boolean hair = toggle.isChecked();

        // Radios (demeanor)
        RadioGroup demeanor = findViewById(R.id.demeanorgroup);

        int didId = demeanor.getCheckedRadioButtonId();

        if(didId == R.id.one){
            //demeanor is family
            deemer = "The kids will love it!";
        }else if(didId == R.id.two){
            //demeanor is sporty
            deemer = "I hope you like running!";
        }else if(didId == R.id.three){
            //demeanor is show dog
            deemer = "A real champion!";
        }
        //if they didnt chose a demeanor
        if(didId == -1){

            //warn the user in a "toast"
            Context context = getApplicationContext();
            CharSequence text = "Please Select a demeanor.";
            //toast duration
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            //if they did chose a demeanor
                if (hair) {
                    //hair is default to longhair
                    switch (sizeType) {
                        //sizeType is the size of the dog


                        case "Small":
                            //small long-hair dogs
                            img.setImageResource(R.drawable.terrier);
                            perfectDog = "A Terrier would be alright.";
                            if (hunting) {
                                perfectDog= "Hunt with a bigger dog.";
                                img.setImageResource(R.drawable.chi);
                            }

                            if(guarding){
                                perfectDog = "Get a larger dog.";
                                img.setImageResource(R.drawable.chi);
                            }

                            if (lapping) {
                                perfectDog = "A Cat is the right dog for you.";
                                img.setImageResource(R.drawable.cat);
                            }
                            break;

                        //medium-sized long-hair dogs
                        case "Medium":

                            if(hunting){
                                perfectDog = "English Setter";
                                img.setImageResource(R.drawable.setter);
                            }
                            if (guarding){
                                perfectDog = "Komondor";
                                img.setImageResource(R.drawable.komodor);
                            }
                            if(lapping){
                                perfectDog = "Afghan";
                                img.setImageResource(R.drawable.afghan);

                            }
                            break;
                        case "Large":
                            //large long-hair dogs
                            if(guarding ) {
                                perfectDog = "St. Bernard";
                                img.setImageResource(R.drawable.bernard);
                            }
                            if(hunting){
                                perfectDog = "German Shepard";
                                img.setImageResource(R.drawable.german);
                            }
                            if(lapping){
                                perfectDog = "Bernese Mtn. Dog";
                                img.setImageResource(R.drawable.bernese);
                            }

                            break;
                        default:
                            //if no size is selected give them a cat
                            perfectDog = "A Cat";
                            img.setImageResource(R.drawable.cat);
                    }
                } else {
                    //shorthair dogs
                    switch (sizeType) {
                        //small short-hair dogs
                        case "Small":
                            if(lapping) {
                                perfectDog = "Chihuahua";
                                img.setImageResource(R.drawable.chi);
                            }
                            if(hunting){
                                perfectDog = "American Foxhound";
                                img.setImageResource(R.drawable.foxhound);
                            }
                            if(guarding) {
                                perfectDog = "Shiba Inus";
                                img.setImageResource(R.drawable.shiba);
                            }
                            break;

                        case "Medium":
                            //medium-sized short-hair dogs
                            perfectDog = "Boxer";
                            img.setImageResource(R.drawable.boxer);
                            if (hunting) {
                                perfectDog = "Labs";
                                img.setImageResource(R.drawable.lab);
                            }
                            if(guarding){
                                perfectDog = "Pitbull";
                                img.setImageResource(R.drawable.pitbull);
                            }
                            break;
                        case "Large":
                            //large shorthair dogs
                            if(guarding){
                                perfectDog = "Rottweiler";
                                img.setImageResource(R.drawable.rott);
                            }
                            if(hunting){
                                perfectDog = "Doberman";
                                img.setImageResource(R.drawable.dober);

                            }
                            if(lapping) {
                                perfectDog = "Great Dane";
                                img.setImageResource(R.drawable.dane);
                            }
                            break;
                        default:
                            //again, default is the cat
                            perfectDog = "A Cat";
                            img.setImageResource(R.drawable.cat);
                    }
                }

        }

        TextView dogSelection = findViewById(R.id.dogbreedname);
        dogSelection.setText(perfectDog);
        deem.setText(deemer);
        System.out.print(didId);

    }
}
