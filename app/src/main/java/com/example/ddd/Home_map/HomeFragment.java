package com.example.ddd.Home_map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ddd.Base.BaseFragment;
import com.example.ddd.R;

public class HomeFragment extends BaseFragment {

    CardView Search_btn;
    View view;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fr_home, container, false);

        Search_btn = view.findViewById(R.id.Search_btn);

        Search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFragment(new PharmaciesFragment());
            }
        });
        return view;

    }

    private void goToFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction()
                .addToBackStack(null);
        transaction.replace(R.id.fragment_container, fragment)
                .commit();
    }

}