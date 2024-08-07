package com.example.besafeapp;

import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.accounts.Account;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavBar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavBar extends Fragment implements LocationListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LocationManager locationManager;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NavBar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavBar.
     */
    // TODO: Rename and change types and number of parameters
    public static NavBar newInstance(String param1, String param2) {
        NavBar fragment = new NavBar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private Button homeButton,placesButton,callButton,accountButton;
    public void updateButtonColor(String screen) {
        if(screen.equalsIgnoreCase("home"))
        {
            homeButton.setBackgroundColor(getResources().getColor(R.color.pink));
        }
        else if(screen.equalsIgnoreCase("places")){
            placesButton.setBackgroundColor(getResources().getColor(R.color.pink));
        }
        else if(screen.equalsIgnoreCase("call")){
            callButton.setBackgroundColor(getResources().getColor(R.color.pink));
        }
        else if(screen.equalsIgnoreCase("account")){
            accountButton.setBackgroundColor(getResources().getColor(R.color.pink));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_nav_bar, container, false);

        homeButton=view.findViewById(R.id.NavBarHomeButton);
        placesButton=view.findViewById(R.id.NavBarPlacesButton);
        callButton=view.findViewById(R.id.NavBarCallButton);
        accountButton=view.findViewById(R.id.NavBarAccountButton);

        //callButton.setBackgroundColor(Color.parseColor("#FFFFFF"));

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeButton.setBackgroundColor(getResources().getColor(R.color.pink));
                placesButton.setBackgroundColor(getResources().getColor(R.color.white));
                callButton.setBackgroundColor(getResources().getColor(R.color.white));
                accountButton.setBackgroundColor(getResources().getColor(R.color.white));
//
                Intent intent=new Intent(getActivity(),HomePage.class);
                getActivity().startActivity(intent);
            }
        });

        placesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeButton.setBackgroundColor(getResources().getColor(R.color.white));
                placesButton.setBackgroundColor(getResources().getColor(R.color.pink));
                callButton.setBackgroundColor(getResources().getColor(R.color.white));
                accountButton.setBackgroundColor(getResources().getColor(R.color.white));


//                if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(getActivity(), new String[]{
//                            Manifest.permission.ACCESS_FINE_LOCATION
//                    },100);
//                }
//                else{
//                    Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
//                }

                Intent intent=new Intent(getActivity(),SafePlaces.class);
                getActivity().startActivity(intent);
            }
        });


        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeButton.setBackgroundColor(getResources().getColor(R.color.white));
                placesButton.setBackgroundColor(getResources().getColor(R.color.white));
                callButton.setBackgroundColor(getResources().getColor(R.color.pink));
                accountButton.setBackgroundColor(getResources().getColor(R.color.white));
                Intent intent=new Intent(getActivity(), com.example.besafeapp.Helpline.class);
                getActivity().startActivity(intent);
            }
        });

        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeButton.setBackgroundColor(getResources().getColor(R.color.white));
                placesButton.setBackgroundColor(getResources().getColor(R.color.white));
                callButton.setBackgroundColor(getResources().getColor(R.color.white));
                accountButton.setBackgroundColor(getResources().getColor(R.color.pink));
                Intent intent=new Intent(getActivity(), AccountSettings.class);
                getActivity().startActivity(intent);
            }
        });

        return view;



    }

//    private void getLocation() {
//        try {
//            locationManager = (LocationManager) getActivity().getApplicationContext().getSystemService(LOCATION_SERVICE);
//            if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, );
//        }catch (Exception e){
//
//        }
//    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
//        currentLongitude=location.getLongitude();
//        currentLatitude=location.getLatitude();
//
//        Toast.makeText(this, currentLongitude+" "+currentLatitude+"", Toast.LENGTH_SHORT).show();
    }
}