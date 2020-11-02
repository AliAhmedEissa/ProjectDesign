package com.example.ddd;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.ddd.Home_map.HomeFragment;
import com.example.ddd.Home_map.MapFragment;
import com.example.ddd.Home_map.PharmaciesDrFragment;
import com.example.ddd.Orders.OrdersFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;




public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    MeowBottomNavigation bottomNavigation;
    CardView button_search;

    NavigationView navView;
    DrawerLayout drawerLayout;


    // onCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottonNav);
        button_search = findViewById(R.id.Search_btn);
        navView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);



        setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();




        setTitle("Home");

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_map));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_orders));

        bottomNavigation.setOnClickMenuListener(model -> {
            switch (model.getId()) {
                case 1:
                    goToFragment(new HomeFragment());
                    break;
                case 2:
                    goToFragment(new MapFragment());
                    break;
                case 3:
                    goToFragment(new OrdersFragment());
                    break;
            }
            return null;
        });
        goToFragment(new HomeFragment());

        bottomNavigation.show(1, false);
    }


    private void goToFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null);
        transaction.replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        toolbar.setVisibility(View.VISIBLE);
        bottomNavigation.setVisibility(View.VISIBLE);

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code

        } else {
            getSupportFragmentManager().popBackStack();

        }

    }
}