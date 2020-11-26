package com.example.ddd;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.ddd.Base.BaseActivity;
import com.example.ddd.Home_map.HomeFragment;
import com.example.ddd.Home_map.MapFragment;
import com.example.ddd.Home_map.PharmaciesDrFragment;
import com.example.ddd.Orders.OrdersFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.ddd.SplashActivity.MY_LOCATION_PERMISSIONS_REQUEST_Code;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    public static MeowBottomNavigation bottomNavigation;
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
        navView.setNavigationItemSelectedListener(this);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Home:
                goToFragment(new HomeFragment());
                bottomNavigation.setVisibility(View.VISIBLE);
                break;
            case R.id.Profile:
                goToFragment(new ProfileFragment());
                bottomNavigation.setVisibility(View.GONE);
                break;
            case R.id.Pharmacies:
                Toast.makeText(this, "All Pharmacies", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Faq:
                Toast.makeText(this, "Faq", Toast.LENGTH_SHORT).show();
                break;
            case R.id.aboutUs:
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SignOut:
                Toast.makeText(this, "Sign Out", Toast.LENGTH_SHORT).show();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

//    @Override
//    public void onBackPressed() {
//
//        int count = getSupportFragmentManager().getBackStackEntryCount();
//            super.onBackPressed();
//            goToFragment(new HomeFragment());
//    }

}