package com.groupdev.pillpall;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.groupdev.pillpall.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private ActivityMainBinding binding;
    private FirebaseAuth auth;
    TextView drawerUsername, drawerEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        initializeLayout();
        setupNavigation();
        drawerUsername.setText(currentUser.getDisplayName());
        drawerEmail.setText(currentUser.getEmail());
    }

    private void initializeLayout(){
        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation_drawer);
        View header = navigationView.getHeaderView(0);
        drawerEmail = header.findViewById(R.id.drawer_header_email);
        drawerUsername = header.findViewById(R.id.drawer_header_username);
    }

    private void setupNavigation(){
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        setSupportActionBar(toolbar);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_medications, R.id.navigation_vitals)
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        NavigationUI.setupWithNavController(binding.navigationDrawer, navController);

        /* drawer navigation
        navigationView.setNavigationItemSelectedListener(item -> {
            Bundle bundle = new Bundle();

            if (item.getItemId() == R.id.fragment_patients) {
                bundle.putString("patient", "user");
            }
            if (item.getItemId() == R.id.fragment_something) {
                bundle.putString("key", "value");
            }
            if (item.getItemId() == R.id.fragment_profile) {
                bundle.putString("profileId", null);
            }
            navController.navigate(item.getItemId(), bundle);
            drawerLayout.closeDrawer();
         */

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.logout_button){
                    auth.signOut();
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }


                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }
}