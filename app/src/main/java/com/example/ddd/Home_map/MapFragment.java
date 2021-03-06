package com.example.ddd.Home_map;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ddd.Base.BaseFragment;
import com.example.ddd.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends BaseFragment implements OnMapReadyCallback {


    View view ;
    MapView mapView ;
    GoogleMap googleMap ;
    Home_Map_VM Vm ;


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Vm = new ViewModelProvider(getActivity()).get(Home_Map_VM.class);
        view = inflater.inflate(R.layout.fr_map, container, false);

        init(savedInstanceState);

        
        return view;
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap =googleMap;
        googleMap.setMyLocationEnabled(true);



        Vm.MyLocation.observe(getActivity(), new Observer<LatLng>() {
            @Override
            public void onChanged(LatLng latLng) {
                if(latLng!=null){
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(Vm.MyLocation.getValue()));
                    addMarker(latLng,"my location");
                }
            }
        });


    }

    private void init(Bundle savedInstanceState) {

        mapView = view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }



    private void addMarker(LatLng latLng, String title) {

        googleMap.addMarker(new MarkerOptions().position(latLng)
                .title(title).flat(true));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));


    }


}