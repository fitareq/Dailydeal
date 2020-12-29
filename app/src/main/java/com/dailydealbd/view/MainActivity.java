package com.dailydealbd.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.dailydealbd.R;
import com.dailydealbd.view.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        drawerLayout = findViewById(R.id.drawerLayoutId);
        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close) {
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        loadFragments(new HomeFragment());

    }

    //for toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment SelectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                        case R.id.nav_account:
                        case R.id.nav_category:
                        case R.id.nav_cart:
                            Toast.makeText(MainActivity.this, "sunam", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    return loadFragments(SelectedFragment);
                }
            };

    //common loadFragments method
    private boolean loadFragments(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    //for Drawer Navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home1:
            case R.id.nav_account1:

            case R.id.nav_order1:

            case R.id.nav_cart1:

            case R.id.nav_wishlist1:

            case R.id.nav_contact:
            case R.id.nav_conditions:
                Toast.makeText(MainActivity.this, "sunam", Toast.LENGTH_SHORT).show();

                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //For Search menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);

        searchView =(SearchView) menuItem.getActionView();
        searchView.setQueryHint("What would you like to buy?");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

}
