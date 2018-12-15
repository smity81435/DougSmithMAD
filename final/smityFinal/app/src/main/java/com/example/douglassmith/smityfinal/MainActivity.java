package com.example.douglassmith.smityfinal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {


    private Restaurant myRestaurant = new Restaurant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button makePizza = (Button)findViewById(R.id.zaButt);
        View.OnClickListener makePizzaClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePizza(view);
            }
        };
        makePizza.setOnClickListener(makePizzaClick);

        final Button findPizza = (Button)findViewById(R.id.findPizza);
        View.OnClickListener findZa = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findDaPizza(view);
            }
        };
        findPizza.setOnClickListener(findZa);

    }

    public void makePizza(View view){
        String selection = "";
        TextView zaSum = (TextView)findViewById(R.id.pizzaSummary);
        EditText zaName = (EditText)findViewById(R.id.editText);
        String pizzaName= zaName.getText().toString();
        ToggleButton sauceType = (ToggleButton)findViewById(R.id.sauce);
        boolean sauce = sauceType.isChecked();
        String zasauce = "";
        if(sauce){
            zasauce = "Red";
        }else{
            zasauce = "white";
        }
        RadioGroup crustType = (RadioGroup)findViewById(R.id.crustType);
        int crust = crustType.getCheckedRadioButtonId();
        CheckBox pepperoni = (CheckBox)findViewById(R.id.pepperoni);
        boolean peponza = pepperoni.isChecked();
        String PepString = "";
        CheckBox sausage = (CheckBox)findViewById(R.id.sausage);
        boolean sausonza = sausage.isChecked();
        ImageView pizzaPic = (ImageView)findViewById(R.id.pizzaPic);
        pizzaPic.setImageResource(R.drawable.pizza_meat);
        String SausString = "";
        CheckBox mushrooms = (CheckBox)findViewById(R.id.mushrooms);
        boolean mushonza = mushrooms.isChecked();
        pizzaPic.setImageResource(R.drawable.pizza_veggie);
        String MushString = "";
        CheckBox olives = (CheckBox)findViewById(R.id.olives);
        boolean olivesonza = olives.isChecked();
        pizzaPic.setImageResource(R.drawable.pizza_supreme);
        String OlivesString = "";
        if(peponza){
            PepString = " Pepperoni ";
        }
        if(sausonza){
            SausString = " Sausage ";
        }
        if(mushonza){
            MushString = " Mushrooms ";
        }
        if(olivesonza){
            OlivesString = " Olives ";
        }
        Switch glutenSwitch = (Switch)findViewById(R.id.gluten);
        boolean gluten = glutenSwitch.isChecked();
        String glutenString = "";
        if(gluten){
            glutenString=" and is Gluten Free.";
        }
        Spinner zasize = (Spinner)findViewById(R.id.size);
        Integer size = zasize.getSelectedItemPosition();
        String pizzaSize="Small";
        switch(size){
            case 0:
                pizzaSize="Small";
                break;
            case 1:
                pizzaSize="Medium";
                break;
            case 2:
                pizzaSize="Large";
                break;
            case 3:
                pizzaSize="Extra Large";
                break;
            default:
                break;
        }


        if(pizzaName == "" || crust == -1) {
            Context context = getApplicationContext();
            CharSequence message = "Name your za, and pick a crust!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();
        }else{
                View radioButton = crustType.findViewById(crust);
                int radioId = crustType.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) crustType.getChildAt(radioId);
                selection = (String) btn.getText();
                Log.i("myApp",selection);
                zaSum.setText("Your "+pizzaSize+" Pizza, "+pizzaName+" has "+zasauce+" sauce, "+selection+" crust, "+" "+PepString+MushString+OlivesString+SausString+glutenString);
        }
    }

    public void findDaPizza(View view){
        Switch glutenSwitch = (Switch)findViewById(R.id.gluten);
        boolean gluten = glutenSwitch.isChecked();
        RadioGroup crustType = (RadioGroup)findViewById(R.id.crustType);
        int crust = crustType.getCheckedRadioButtonId();
        View radioButton = crustType.findViewById(crust);
        int radioId = crustType.indexOfChild(radioButton);
        RadioButton btn = (RadioButton) crustType.getChildAt(radioId);
        String selection = (String) btn.getText();
        myRestaurant.setRestaurant(selection,gluten);
        String suggestedRestaurant = myRestaurant.getRestaurant();
        String suggestedRestaurantURL = myRestaurant.getRestaurantURL();
        Log.i("shop", suggestedRestaurant);
        Log.i("url", suggestedRestaurantURL);
        //explicit intent
        Intent intent = new Intent(this, RTpizza.class);
        intent.putExtra("restaurantTitle",suggestedRestaurant);
        intent.putExtra( "restaurantSugURL", suggestedRestaurantURL);
        startActivity(intent);
    }

}


