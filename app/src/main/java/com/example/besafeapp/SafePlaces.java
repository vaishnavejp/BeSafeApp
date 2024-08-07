package com.example.besafeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.besafeapp.NavBar;
import com.example.besafeapp.R;
import com.example.besafeapp.helpers.DBHelper;
import com.example.besafeapp.safePlacesDBHelper;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class SafePlaces extends AppCompatActivity implements LocationListener {

    private Handler handler;

    LocationManager locationManager;
    Geocoder geocoder;
    double currentLatitude=0,currentLongitude=0;
    Location currentLocation=new Location("currentLocation");

    DBHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_places);

        dbhelper=new DBHelper(getApplicationContext());
        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        Toast.makeText(this, "Fetching current location...", Toast.LENGTH_SHORT).show();

        //Access the fragment and update the button color //Otherwise the button's color doesn't change
        NavBar fragment = (NavBar) getSupportFragmentManager().findFragmentById(R.id.NavBar);
        if (fragment != null) {
            fragment.updateButtonColor("places");
        }


        if (ContextCompat.checkSelfPermission(SafePlaces.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SafePlaces.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }

        getLocation();

    }

    void AssignValues(String location){

        try {
        Cursor cursor=dbhelper.retrievePlace(location);
        while(cursor.moveToNext())
        {

                String source=getLocationActualName(currentLatitude,currentLongitude);
                Location tempLocation = new Location("tempLocation");
                double temp=currentLocation.distanceTo(tempLocation);
                List<Address> addressList;


                //1

                String FirstField=cursor.getString(0);
                addressList=geocoder.getFromLocationName(FirstField,1);

                TextView name=findViewById(R.id.NameOfSafePlace1);
                TextView dist=findViewById(R.id.DistanceToSafePlace1);
                TextView addr=findViewById(R.id.AddressSafePlace1);

                name.setText(FirstField.split(",")[0]);

                tempLocation.setLatitude(addressList.get(0).getLatitude());
                tempLocation.setLongitude(addressList.get(0).getLongitude());

                dist.setText(""+((int)currentLocation.distanceTo(tempLocation))/1000.0+"km");

                addr.setText(addressList.get(0).getAddressLine(0));

                ImageButton safePlaceDirection=findViewById(R.id.SafePlaceDirection1);
                safePlaceDirection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String destination= null;
                            destination = FirstField;
                            Uri uri= Uri.parse("https://www.google.com/maps/dir/"+source+"/"+destination);
                            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                            intent.setPackage("com.google.android.apps.maps");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                    }
                });

                //2
                String SecondField=cursor.getString(3);
                addressList=geocoder.getFromLocationName(SecondField,1);

                name=findViewById(R.id.NameOfSafePlace2);
                dist=findViewById(R.id.DistanceToSafePlace2);
                addr=findViewById(R.id.AddressSafePlace2);

                name.setText(SecondField.split(",")[0]);

                tempLocation.setLatitude(addressList.get(0).getLatitude());
                tempLocation.setLongitude(addressList.get(0).getLongitude());
                dist.setText(""+((int)currentLocation.distanceTo(tempLocation))/1000.0+"km");

                addr.setText(addressList.get(0).getAddressLine(0));

                safePlaceDirection=findViewById(R.id.SafePlaceDirection2);
                safePlaceDirection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String destination=SecondField;
                        Uri uri= Uri.parse("https://www.google.com/maps/dir/"+source+"/"+destination);
                        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                        intent.setPackage("com.google.android.apps.maps");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

                //3
                String ThirdField=cursor.getString(4);
                addressList=geocoder.getFromLocationName(ThirdField,1);

                name=findViewById(R.id.NameOfSafePlace3);
                dist=findViewById(R.id.DistanceToSafePlace3);
                addr=findViewById(R.id.AddressSafePlace3);

                name.setText(ThirdField.split(",")[0]);

                tempLocation.setLatitude(addressList.get(0).getLatitude());
                tempLocation.setLongitude(addressList.get(0).getLongitude());
                dist.setText(""+((int)currentLocation.distanceTo(tempLocation))/1000.0+"km");

                addr.setText(addressList.get(0).getAddressLine(0));

                safePlaceDirection=findViewById(R.id.SafePlaceDirection3);
                safePlaceDirection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String destination=ThirdField;
                        Uri uri= Uri.parse("https://www.google.com/maps/dir/"+source+"/"+destination);
                        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                        intent.setPackage("com.google.android.apps.maps");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

                //4
                String FourthField=cursor.getString(5);
                addressList=geocoder.getFromLocationName(FourthField,1);

                name=findViewById(R.id.NameOfSafePlace4);
                dist=findViewById(R.id.DistanceToSafePlace4);
                addr=findViewById(R.id.AddressSafePlace4);

                name.setText(FourthField.split(",")[0]);

                tempLocation.setLatitude(addressList.get(0).getLatitude());
                tempLocation.setLongitude(addressList.get(0).getLongitude());
                dist.setText(""+((int)currentLocation.distanceTo(tempLocation))/1000.0+"km");

                addr.setText(addressList.get(0).getAddressLine(0));

                safePlaceDirection=findViewById(R.id.SafePlaceDirection4);
                safePlaceDirection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String destination=FourthField;
                        Uri uri= Uri.parse("https://www.google.com/maps/dir/"+source+"/"+destination);
                        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                        intent.setPackage("com.google.android.apps.maps");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

                //5

                String FifthField=cursor.getString(6);
                addressList=geocoder.getFromLocationName(FifthField,1);

                name=findViewById(R.id.NameOfSafePlace5);
                dist=findViewById(R.id.DistanceToSafePlace5);
                addr=findViewById(R.id.AddressSafePlace5);

                name.setText(FifthField.split(",")[0]);

                tempLocation.setLatitude(addressList.get(0).getLatitude());
                tempLocation.setLongitude(addressList.get(0).getLongitude());
                dist.setText(""+((int)currentLocation.distanceTo(tempLocation))/1000.0+"km");

                addr.setText(addressList.get(0).getAddressLine(0));

                safePlaceDirection=findViewById(R.id.SafePlaceDirection5);
                safePlaceDirection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String destination=FifthField;
                        Uri uri= Uri.parse("https://www.google.com/maps/dir/"+source+"/"+destination);
                        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                        intent.setPackage("com.google.android.apps.maps");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    String findClosestPlace() {




        Cursor cursor=dbhelper.retrieveAllPlaces();

        currentLocation.setLatitude(currentLatitude);
        currentLocation.setLongitude(currentLongitude);

        String closestLocationName="";
        double closestDistance=1000000;

        if(cursor.getCount()==0)
        {
            Toast.makeText(SafePlaces.this, "This locationName does not exist", Toast.LENGTH_SHORT).show();
        }
        else
        {
//            Toast.makeText(SafePlaces.this, "Product retrieved successfully", Toast.LENGTH_SHORT).show();

            while(cursor.moveToNext())
            {
                Location tempLocation = new Location("tempLocation");
                tempLocation.setLatitude(Double.parseDouble(cursor.getString(1)));
                tempLocation.setLongitude(Double.parseDouble(cursor.getString(2)));

                double temp=currentLocation.distanceTo(tempLocation);

                if(temp<closestDistance)
                {
                    closestDistance=temp;
                    closestLocationName=cursor.getString(0);
                }
            }
//            Toast.makeText(this, "Smallest is "+closestLocationName+" "+Double.toString(closestDistance), Toast.LENGTH_SHORT).show();
        }
        return closestLocationName;
    }

    String getLocationActualName(double lat,double longi) throws IOException {

        List<Address> addresses=geocoder.getFromLocation(lat,longi,1);
        return addresses.get(0).getAddressLine(0);

    }

    private void getLocation() {
        Toast.makeText(this, "Loading the location", Toast.LENGTH_SHORT).show();
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, SafePlaces.this);
        }catch (Exception e){

        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        currentLongitude=location.getLongitude();
        currentLatitude=location.getLatitude();

        if(currentLatitude==0||currentLongitude==0)
        {
//            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }
        else{
//            Toast.makeText(this, "correct", Toast.LENGTH_SHORT).show();

            String closestLocation=findClosestPlace();
            AssignValues(closestLocation);
            LinearLayout ll=findViewById(R.id.safePlacesLinearLayout);
            ll.setVisibility(View.VISIBLE);

        }


    }



}