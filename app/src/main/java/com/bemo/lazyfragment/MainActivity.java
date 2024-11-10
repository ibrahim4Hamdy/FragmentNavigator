package com.bemo.lazyfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.qena.navigation.fragments.FragmentNavigator;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView navigationView;
    FragmentNavigator navigator;
    Fragment home =new HomeFragment();
    Fragment offers =new OffersFragment();
    Fragment saved =new SavedFragment();
    Fragment cart =new CartFragment();
    Fragment profile =new ProfileFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView =findViewById(R.id.BottomBar);

        navigationView.setOnNavigationItemSelectedListener(this);
        navigator =new FragmentNavigator(getSupportFragmentManager());

        navigator.addFragment(R.id.ic_home,home,false);
        navigator.addFragment(R.id.ic_offers,offers,false);
        navigator.addFragment(R.id.ic_saved,saved,true);
        navigator.addFragment(R.id.ic_cart,cart,false);
        navigator.addFragment(R.id.ic_profile,profile,true);
        navigator.setCurrentFragment(R.id.ic_home,home,R.id.frame_container);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return navigator.navigateToFragment(item.getItemId(),R.id.frame_container);
    }
}