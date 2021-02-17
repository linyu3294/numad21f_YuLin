package com.gremlinweekend.numad21s_yulin;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.gremlinweekend.numad21s_yulin.linkActivity.LinkActivity;

public class FirstFragment extends Fragment {
    protected LocationManager locationManager;
    boolean access = false;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        view.findViewById(R.id.button_new_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });

        view.findViewById(R.id.button_link_collector).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { openLinkActivity();}
        });

        view.findViewById(R.id.button_gps).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getParentFragment().getContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getParentFragment().getContext(), "You Have Access to Fine Location Permission!",
                            Toast.LENGTH_SHORT).show();
                    onRequestPermissionsResult(MY_PERMISSIONS_REQUEST_LOCATION,
                            new String []{Manifest.permission.ACCESS_FINE_LOCATION},
                            new int[]{0});
                } else {
                    grantLocationPermission();
                    Toast.makeText(getParentFragment().getContext(), "Please Grant Location Access To Use This Feature!", Toast.LENGTH_SHORT).show();
                    onRequestPermissionsResult(MY_PERMISSIONS_REQUEST_LOCATION,
                            new String []{Manifest.permission.ACCESS_FINE_LOCATION},
                            new int[]{1});
                }
                }
        });


    }
    public void openNewActivity(){
        Intent intent = new Intent(FirstFragment.this.getActivity(), NewActivity.class);
        startActivity(intent);
    }
    public void openLinkActivity(){
        Intent intent = new Intent(FirstFragment.this.getActivity(), LinkActivity.class);
        startActivity(intent);
    }

    public void openGPSActivity(){
            Intent intent = new Intent(FirstFragment.this.getActivity(), GpsActivity.class);
            startActivity(intent);
    }



    public void grantLocationPermission() {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   openGPSActivity();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}