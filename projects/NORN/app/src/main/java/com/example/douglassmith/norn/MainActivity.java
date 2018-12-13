package com.example.douglassmith.norn;

import android.app.Activity;
import android.content.Intent;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.location.LocationProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

//import com.android.volley.AuthFailureError;
//import com.android.volley.NetworkError;
//import com.android.volley.NoConnectionError;
//import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
//import com.android.volley.ServerError;
//import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.*;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    public double lat = 40.0150;
    public double lon = -105.2705;
    public static final int REQUEST_CODE_GETDATE = 666;
    private int MercScore;
    private int moonScore;
    private int MarsScore;
    private int totalScore;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.i("myApp","Returned the data");
        switch(requestCode){
            case REQUEST_CODE_GETDATE:
                if(resultCode == Activity.RESULT_OK){
                    String dateString = data.getStringExtra("dateString");
                    int month = data.getIntExtra("month", 0);
                    int day = data.getIntExtra("day", 0);
                    int year = data.getIntExtra("year", 0);
                    Log.i("myApp",dateString+" Month:"+month+" Day: "+day+" Year: "+year); //log date sent
                    if(month == 0 && day == 0 && year == 0){
                        Log.i("myApp", "Somefing is vewy vewy wong wif it."); //dates not sending
                    }
                    TextView dateplace = findViewById(R.id.currentDate); //bottom date
                    final TextView mercuryRet = findViewById(R.id.isMercRet);  //Merc Retro Text
                    final TextView moonPhase = findViewById(R.id.phaseText); //Moon Phase Text
                    final TextView marsStat = findViewById(R.id.marsStat); //Moon Phase Text
                    dateplace.setText(dateString); //set bottom date

                    //MERCURY JSON REQUEST
                    RequestQueue queue= Volley.newRequestQueue(this);
                    String url = "https://mercuryretrogradeapi.com?date="+year+"-"+month+"-"+day;
                    final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>(){
                                @Override
                                public void onResponse(JSONObject response){
                                    try{
                                        JSONObject obj = response;
                                        String retro = obj.getString("is_retrograde");
                                        if(retro.equals("true")){
                                            MercScore = 1;
                                            Log.i("myApp","Retrograde Detected!");
                                            mercuryRet.setText("active");
                                        }else{
                                            MercScore = 0;
                                            Log.i("myApp","No Retrograde!");
                                            mercuryRet.setText("inactive");
                                        }
                                    }catch(JSONException e){ //CATCH MERCURY FAIL
                                        Log.i("myApp","Damn it!");
                                    }
                                }
                            },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                            mercuryRet.setText("inactive");
                            Log.i("myApp","Failed to retrieve retrograde");
                        }
                    });
                    queue.add(jsonObjectRequest);
                    //END MERCURY REQUEST

                    //MOON JSON REQUEST
                    String moonurl = "https://api.solunar.org/solunar/"+lat+","+lon+","+year+""+month+""+day+",-8";
                    //Log.i("myApp",moonurl);
                    final JsonObjectRequest moonJSON = new JsonObjectRequest(Request.Method.GET, moonurl, null,
                            new Response.Listener<JSONObject>(){
                                @Override
                                public void onResponse(JSONObject response){
                                    try{
                                        JSONObject obj = response;
                                        //Log.i("myApp",obj.toString());
                                        String phase = obj.getString("moonPhase");
                                        moonPhase.setText(phase);
                                        Log.i("myApp",phase);
                                        if(phase.equals("Full")){
                                            moonScore = 2;
                                        }else if(phase.equals("New")){
                                            moonScore = 1;
                                        }else if(phase.equals("Half Moon")){
                                            moonScore=3;
                                        }else if(phase.equals("Waxing Gibbous")){
                                            moonScore=1;
                                        }else if(phase.equals("Waxing Crescent")){
                                            moonScore=1;
                                        }else if(phase.equals("Waning Crescent")){
                                            moonScore = 2;
                                        }
                                        totalScore = MarsScore + moonScore + MercScore;
                                        TextView fortune = findViewById(R.id.fortune);
                                        String logscore = Integer.toString(totalScore);
                                        Log.i("myApp","logscore: "+logscore+" Moon: "+moonScore+" Merc: "+MercScore+ " Mars: "+MarsScore);
                                        switch(totalScore){ //tally score
                                            case 0:
                                                fortune.setText(getString(R.string.fortune1));
                                                break;
                                            case 1:
                                                fortune.setText(getString(R.string.fortune2));
                                                break;
                                            case 2:
                                                fortune.setText(getString(R.string.fortune3));
                                                break;
                                            case 3:
                                                fortune.setText(getString(R.string.fortune4));
                                                break;
                                            case 4:
                                                fortune.setText(getString(R.string.fortune5));
                                                break;
                                            default:
                                                fortune.setText(getString(R.string.error));
                                        }
                                    }
                                    catch(JSONException e){ //CATCH MERCURY FAIL
                                        Log.i("myApp","Failed to retrieve moon phase.");
                                    }
                                }
                            },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("myApp","uh oh, somfing is wong! MOON");
                        }
                    });
                    queue.add(moonJSON);
                    //END MOON REQUEST
                    //MARS JSON REQUEST *I gave up on this request due to Volley Connection Errors
                    if((month+day)%2==0){
                        MarsScore = 1;
                        marsStat.setText("Rising");
                        Log.i("myApp","Mars Rising");
                    }else{
                        MarsScore = 0;
                        marsStat.setText("Falling");
                        Log.i("myApp","Mars Falling");
                    }
                    //END MARS REQUEST

                }
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView usedDate = (TextView) findViewById(R.id.currentDate);

        //set date string
        SimpleDateFormat formatter
                = new SimpleDateFormat ("EEE, MMM d, ''yy" );
        Date currentTime_1 = new Date();
        String dateString = formatter.format(currentTime_1);
        usedDate.setText(dateString);


        //date button clicked
        final ImageButton pickDateButton = findViewById(R.id.cal);
        View.OnClickListener dateclick = new View.OnClickListener() {
            public void onClick(View v) {
                //explicit
                Intent intent = new Intent(MainActivity.this, RTcalendar.class);
                startActivityForResult(intent, REQUEST_CODE_GETDATE);
                //TESTING DATE
                //setUsedDate(fullDate);
                //Log.i("myApp",fullDate);
                //usedDate.setText(fullDate);
            }
        };
        pickDateButton.setOnClickListener(dateclick);


        //date button clicked
        final ImageButton pickQuestButton = findViewById(R.id.question);
        View.OnClickListener questclick = new View.OnClickListener() {
            public void onClick(View v) {
                //implicit to RTCalendar
                Intent intent = new Intent(MainActivity.this, Quest.class);
                startActivity(intent);
            }
        };
        pickQuestButton.setOnClickListener(questclick);
    }
}

