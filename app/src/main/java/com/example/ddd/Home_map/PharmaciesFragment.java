package com.example.ddd.Home_map;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ddd.Base.BaseFragment;
import com.example.ddd.R;

import java.util.ArrayList;
import java.util.List;

public class PharmaciesFragment extends BaseFragment {

    public PharmaciesFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.frag_pharmacies, container, false);
        getActivity().setTitle("Pharmacies");

        return view;
    }
}