package com.londonappbrewery.climapm;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.cache.Resource;


public class WeatherController extends AppCompatActivity {


    // Constants:
    final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    // App ID to use OpenWeather data
    final String APP_ID = "291bab08e606853f0c06bd27e98e8be6";
    // Time between location updates (5000 milliseconds or 5 seconds)
    final long MIN_TIME = 5000;
    // Distance between location updates (1000m or 1km)
    final float MIN_DISTANCE = 1000;
    //request code
    final int REQUESTCODE=1;


    // TODO: Set LOCATION_PROVIDER here:
    String LocationProvider = LocationManager.NETWORK_PROVIDER;

    LocationManager mLocationManager;
    LocationListener mLocationListener;


    // Member Variables:
    TextView mCityLabel;
    ImageView mWeatherImage;
    TextView mTemperatureLabel;

    // TODO: Declare a LocationManager and a LocationListener here:


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_controller_layout);

        // Linking the elements in the layout to Java code
        mCityLabel = (TextView) findViewById(R.id.locationTV);
        mWeatherImage = (ImageView) findViewById(R.id.weatherSymbolIV);
        mTemperatureLabel = (TextView) findViewById(R.id.tempTV);
        ImageButton changeCityButton = (ImageButton) findViewById(R.id.changeCityButton);


        // TODO: Add an OnClickListener to the changeCityButton here:

        changeCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent =  new Intent(WeatherController.this,ChangeCityControler.class);
                startActivity(myIntent);
            }
        });

    }


    // TODO: Add onResume() here:
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Clima", "onResume Called");
        Log.d("Clima", "Getting weather on location");

        Intent myIntent = getIntent();
        String city = myIntent.getStringExtra("City");
        if(city!=null) {
            getWeatherForNewCity(city);
        }
        else{
            this.getWeatherForCurrentLocation();
        }
    }


    // TODO: Add getWeatherForNewCity(String city) here:
    private void getWeatherForNewCity(String city){
        RequestParams params = new RequestParams();
        params.put("q",city);
        params.put("appid",this.APP_ID);
        letsDoSomeNetworking(params);

    }


    // TODO: Add getWeatherForCurrentLocation() here:
    private void getWeatherForCurrentLocation() {
        this.mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        this.mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Clima", "onLocationChanged called");
                String longitude = String.valueOf(location.getLongitude());
                String latitude = String.valueOf(location.getLatitude());
                System.out.println(longitude+"  "+latitude);
                RequestParams params = new RequestParams();
                params.put("lat",latitude);
                params.put("lon",longitude);
                params.put("appid",APP_ID);
                letsDoSomeNetworking(params);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Log.d("Clima", "onProviderDisabled called");
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    this.REQUESTCODE);
            return;
        }
        mLocationManager.requestLocationUpdates(LocationProvider, this.MIN_TIME, this.MIN_DISTANCE, this.mLocationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==this.REQUESTCODE)
        {
            if(grantResults.length>0
                &&grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Log.d("Clima","onRequestPermissionsResult called, granted");
                this.getWeatherForCurrentLocation();
            }
            else
            {
                Log.d("Clima","pro false");
            }
        }
    }
// TODO: Add letsDoSomeNetworking(RequestParams params) here:
    private void letsDoSomeNetworking(RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(this.WEATHER_URL,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                System.out.println("HTTP TRUE");
                System.out.println(response.toString());
                WeatherDataModel weatherDataModel = WeatherDataModel.fromJson(response);
                System.out.println("Weather not null: "+weatherDataModel!=null);
                updateUI(weatherDataModel);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e,JSONObject response){
                System.out.println("HTTP false");
                Toast.makeText(WeatherController.this,"HTTP Fail",Toast.LENGTH_SHORT).show();
            }
        });

    }


    // TODO: Add updateUI() here:

    private void updateUI(WeatherDataModel model){
        this.mCityLabel.setText(model.getmCity());
        this.mTemperatureLabel.setText(model.getmTemperature());
        int imageID = getResources().getIdentifier(model.getmIconName(),"drawable",getPackageName());
        this.mWeatherImage.setImageResource(imageID);
    }


    // TODO: Add onPause() here:
    @Override
    public void onPause(){
        super.onPause();
        if(mLocationManager!=null) {
            mLocationManager.removeUpdates(mLocationListener);
        }

    }



}
