package com.example.vcampusexpenses;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;


import com.example.vcampusexpenses.authentication.LogInLogOut;
import com.example.vcampusexpenses.session.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.example.vcampusexpenses.adapter.ViewPagerAdapter;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //test hiển thị user
    private Button btnLogout;
    private TextView  txtUserEmail2;
    private SessionManager sessionManager;

    //Thanh điều hướng phía dưới
    BottomNavigationView bottomNavigationView;
    ViewPager2 viewPager2;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUserEmail2 = findViewById(R.id.txtUserEmail2);
        //btnLogout = findViewById(R.id.btnLogout); //sửa thành nút logout ở drawer navigation
        sessionManager = new SessionManager(this);

        LoadUserEmail();
        Logout();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager2 = findViewById(R.id.viewPager);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        //getSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        setupViewPager(); // goi ham
        Menu menu = navigationView.getMenu();
        MenuItem itemLogout = menu.findItem(R.id.menu_logout);

        // xu ly click vao tab bottom menu
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_home){
                viewPager2.setCurrentItem(0);
            } else if (item.getItemId() == R.id.menu_analytics) {
                viewPager2.setCurrentItem(1);
            } else if (item.getItemId() == R.id.menu_exchange) {
                viewPager2.setCurrentItem(2);
            } else if (item.getItemId() == R.id.menu_analytics) {
                viewPager2.setCurrentItem(3);
            } else {
                viewPager2.setCurrentItem(0);
            }
            return true;
        });

    }

    private void setupViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0){
                    bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                } else if (position == 1) {
                    bottomNavigationView.getMenu().findItem(R.id.menu_analytics).setChecked(true);
                } else if (position == 2) {
                    bottomNavigationView.getMenu().findItem(R.id.menu_exchange).setChecked(true);
                } else if (position == 3) {
                    bottomNavigationView.getMenu().findItem(R.id.menu_category).setChecked(true);
                } else {
                    bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }


    @SuppressLint("SetTextI18n")
    private void LoadUserEmail(){
        String email = sessionManager.getSavedEmail();
        if (email != null) {
            txtUserEmail2.setText("UserEmail: "+ email);
        }
    }
    private void Logout(){
        btnLogout.setOnClickListener(v -> {
            LogInLogOut.LogOut(this);
            finish();
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_home){
            viewPager2.setCurrentItem(0);
        } else if (item.getItemId() == R.id.menu_analytics) {
            viewPager2.setCurrentItem(1);
        } else if (item.getItemId() == R.id.menu_exchange) {
            viewPager2.setCurrentItem(2);
        } else if (item.getItemId() == R.id.menu_category) {
            viewPager2.setCurrentItem(3);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
}
