package com.example.ddd;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ddd.Base.BaseFragment;

public class PharmaciesDrFragment extends BaseFragment {

    public PharmaciesDrFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fr_drawer_pharmacies, container, false);

        return view;
    }
}