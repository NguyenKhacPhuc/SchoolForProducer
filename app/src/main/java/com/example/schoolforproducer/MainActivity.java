package com.example.schoolforproducer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.schoolforproducer.MainFragments.DealsFragment;
import com.example.schoolforproducer.MainFragments.DictionaryFragment;
import com.example.schoolforproducer.MainFragments.EntertainmentFragment;
import com.example.schoolforproducer.MainFragments.NotificationFragment;
import com.example.schoolforproducer.MainFragments.RadioFragment;
import com.example.schoolforproducer.MainFragments.UniversityFragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.cymatics));
        initComponent();

        toolbar.setBackgroundColor(getResources().getColor(R.color.cymatics));
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.close,R.string.open);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UniversityFragment()).commit();
            navView.setCheckedItem(R.id.university);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }
    private void initComponent(){

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.university:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UniversityFragment()).commit();
                toolbar.setTitle("UNIVERSITY");
                break;
            case R.id.dictionary:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DictionaryFragment()).commit();
                toolbar.setTitle("DICTIONARY");
                break;
            case R.id.notification:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new NotificationFragment()).commit();
                toolbar.setTitle("NOTIFICATION");
                break;
            case R.id.deals:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DealsFragment()).commit();
                toolbar.setTitle("DEALS");
                break;
            case R.id.entertainment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new EntertainmentFragment()).commit();
                toolbar.setTitle("ENTERTAINMENT");
                break;
            case R.id.radio:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RadioFragment()).commit();
                toolbar.setTitle("RADIO");
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}