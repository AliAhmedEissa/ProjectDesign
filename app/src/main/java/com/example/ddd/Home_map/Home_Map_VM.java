package com.example.ddd.Home_map;

import android.annotation.SuppressLint;
import android.app.Application;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;


public class Home_Map_VM extends AndroidViewModel implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, com.google.android.gms.location.LocationListener {

    public MutableLiveData<List<String>> mText = new MutableLiveData<>(null);
    public MutableLiveData<List<String>> mnum = new MutableLiveData<>(null);
    public MutableLiveData<Boolean> Error = new MutableLiveData<>(false);
    public MutableLiveData<Integer> first = new MutableLiveData<>(0);
    public List<String> stringList = new ArrayList<>();
    public List<String> numberList = new ArrayList<>();


    public MutableLiveData<Location> MyLocation = new MutableLiveData<>(null);
    public MutableLiveData<Boolean> radio = new MutableLiveData<>(false);

    GoogleApiClient googleApiClient;
    Location location;
    LocationRequest locationRequest;


    public Home_Map_VM(@NonNull Application application) {
        super(application);
        buildApiClient();

    }


  /*  public void pharmacy(Context context, double la, double lo) {
        nearpharmacy = new ArrayList<>();

        List<pharmacy> pharmacies = MyDatabase.getInstance(getApplication().getApplicationContext()).memoryDao().getAllpharmacy();
        float[] results = new float[9];


        for (int i = 0; i < pharmacies.size(); i++) {
            Location.distanceBetween(la, lo, pharmacies.get(i).getLatitude(), pharmacies.get(i).getLongitude(), results);
            if (results[0] < 1000) {
                nearpharmacy.add(pharmacies.get(i));
            }
        }
        pharmacy.setValue(nearpharmacy);
    }
*/

    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();

        locationRequest.setSmallestDisplacement(50);
        locationRequest.setFastestInterval(1000);
        locationRequest.setInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {
        Error.setValue(true);
        buildApiClient();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Error.setValue(true);
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        MyLocation.setValue(location);
    }


    public synchronized void buildApiClient() {
        googleApiClient = new GoogleApiClient.Builder(getApplication().getApplicationContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }
}
