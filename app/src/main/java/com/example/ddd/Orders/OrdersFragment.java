package com.example.ddd.Orders;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ddd.Base.BaseFragment;
import com.example.ddd.R;


public class OrdersFragment extends BaseFragment {

    View view ;

    public OrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fr_order, container, false);

        getActivity().setTitle("Orders");
        return view;
    }
}