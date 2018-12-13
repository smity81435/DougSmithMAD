package com.example.douglassmith.norn;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.*;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_GETDATE = 666;
    private Fortune myFortune = new Fortune();
    public String fullDate;
    public int Month;
    public int Day;
    public int Year;
    public int MercScore =0;


//    private Button pickDateButton;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
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
                    TextView moonPhase = findViewById(R.id.phaseText); //Moon Phase Text
                    TextView marsStat = findViewById(R.id.marsStat); //Moon Phase Text
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
                                        if(retro == "true"){
                                            MercScore = 1;
                                            Log.i("myApp","Retrograde Detected!");
                                            mercuryRet.setText("active");
                                        }else{
                                            MercScore = 0;
                                            Log.i("myApp","No Retrograde!");
                                            mercuryRet.setText("no");
                                        }
                                    }catch(JSONException e){ //CATCH MERCURY FAIL
                                        Log.i("myApp","Damn it!");
                                    }
                                }
                            },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                            mercuryRet.setText("Whoops");
                            Log.i("myApp","shit");
                        }
                    });
                    queue.add(jsonObjectRequest);
                    //END MERCURY REQUEST

                    //MOON JSON REQUEST
                    String moonurl = "https://api.usno.navy.mil/moon/phase?date="+month+"/"+day+"/"+year+"&nump=1";
                    Log.i("myApp",moonurl);
                    final JsonObjectRequest moonJSON = new JsonObjectRequest(Request.Method.GET, moonurl, null,
                            new Response.Listener<JSONObject>(){
                                @Override
                                public void onResponse(JSONObject response){
                                    //try{
                                        JSONObject obj = response;
                                        Log.i("myApp",obj.toString());
                                        //String phase = obj.getString("is_retrograde");
//                                        if(retro == "true"){
//                                            MercScore = 1;
//                                            Log.i("myApp","Retrograde Detected!");
//                                            mercuryRet.setText("active");
//                                        }else{
//                                            MercScore = 0;
//                                            Log.i("myApp","No Retrograde!");
//                                            mercuryRet.setText("no");
//                                        }
                                    //}
//                                    catch(JSONException e){ //CATCH MERCURY FAIL
//                                        Log.i("myApp","Damn it!");
//                                    }
                                }
                            },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            if (error instanceof TimeoutError ) {
                                Log.i("myApp","Timeout Fail");
                            } else if(error instanceof NoConnectionError) {
                                Log.i("myApp","Connection Fail");
                            }
                            else if (error instanceof AuthFailureError) {
                                Log.i("myApp","Auth Failure");
                            } else if (error instanceof ServerError) {
                                Log.i("myApp","Server Failure");
                            } else if (error instanceof NetworkError) {
                                Log.i("myApp","Network Failure");
                            } else if (error instanceof ParseError) {
                                Log.i("myApp","Parse Failure");
                            }
                        }
                    });
                    queue.add(moonJSON);
                    //END MERCURY REQUEST

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
                //implicit
                Intent intent = new Intent(MainActivity.this, Quest.class);
                startActivity(intent);
            }
        };
        pickQuestButton.setOnClickListener(questclick);

    }






}

