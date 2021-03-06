package com.example.ddd.Home_map;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.ddd.Apis.ApiManager;
import com.example.ddd.Models.Medicine;
import com.example.ddd.Models.Order;
import com.example.ddd.Models.User;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home_Map_VM extends AndroidViewModel implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, com.google.android.gms.location.LocationListener {

    public MutableLiveData<List<String>> Text = new MutableLiveData<>(null);
    public MutableLiveData<List<String>> num = new MutableLiveData<>(null);
    public MutableLiveData<LatLng> Latlang = new MutableLiveData<>(null);
    public MutableLiveData<LatLng> MyLocation = new MutableLiveData<>(null);
    public MutableLiveData<String> Address = new MutableLiveData<>(null);

    public MutableLiveData<Boolean> Error = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> Loading = new MutableLiveData<>(false);
    public MutableLiveData<Integer> first = new MutableLiveData<>(0);

    public MutableLiveData<Boolean> radio = new MutableLiveData<>(false);

    public List<String> stringList = new ArrayList<>();
    public List<String> numberList = new ArrayList<>();
    List<Medicine> medicineList ;

    GoogleApiClient googleApiClient;
    LocationRequest locationRequest;

    Context context;
    public Home_Map_VM(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        buildApiClient();

    }

    public void CreateOrder(){
        SetOrderData();

        ApiManager.getApis().CreateOrder(SetOrderData()).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.isSuccessful()){
                    Loading.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });
    }

    private Order SetOrderData() {
        medicineList = new ArrayList<>();
        Order order = new Order();

        User user = new User();
        user.setPhone("");
        user.setEmail("");
        user.setAuthKey("");
        user.setImage("");
        user.setName("");

        for(int i= 0 ; i<Text.getValue().size() ; i++){
            Medicine medicine = new Medicine();
            medicine.setId(5);
            medicine.setName("");
            medicine.setPrice(100.1);
            medicine.setQuantity(Integer.parseInt(num.getValue().get(i)));
            medicineList.add(medicine);
        }

        if(radio.getValue()){
            order.setLatitude(Latlang.getValue().latitude);
            order.setLongitude(Latlang.getValue().longitude);
            EncodeAddress(Latlang.getValue());
        }
        else {
            order.setLatitude(MyLocation.getValue().latitude);
            order.setLongitude(MyLocation.getValue().longitude);
            EncodeAddress(MyLocation.getValue());
        }
        order.setId(1);
        order.setAddress(Address.getValue());
        order.setQuantity(num.getValue().size());
        order.setStatus(0);
        order.setTotal(0.0);
        order.setMedicineList(medicineList);

        return order;
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
        LocationServices.getFusedLocationProviderClient(context);
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
        MyLocation.setValue(new LatLng(location.getLatitude(),location.getLongitude()));
    }

    public synchronized void buildApiClient() {
        googleApiClient = new GoogleApiClient.Builder(getApplication().getApplicationContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    public void EncodeAddress(LatLng center) {
        String s = "";
        List<android.location.Address> addresses = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(center.latitude, center.longitude, 1);
            s = addresses.get(0).getAddressLine(0);
            Address.setValue(s);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
