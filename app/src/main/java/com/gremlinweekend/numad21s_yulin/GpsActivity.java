package com.gremlinweekend.numad21s_yulin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class GpsActivity extends AppCompatActivity {

    TextView gpsTextView;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);


    }

    @Override
    protected void onStart() {
        super.onStart();
        getDelegate().onStart();
        gpsTextView = findViewById(R.id.button_gps);
    }


}