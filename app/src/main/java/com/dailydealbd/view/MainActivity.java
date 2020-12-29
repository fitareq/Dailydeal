package com.dailydealbd.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dailydealbd.R;
import com.dailydealbd.view.fragments.HomeFragment;
import com.dailydealbd.viewmodel.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private SearchView searchView;
    private NavigationView navigationView;
    private BottomNavigationView bottomNav;
    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayoutId);
        navigationView = findViewById(R.id.navigation_drawer);
        bottomNav = findViewById(R.id.bottom_nav);


        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.start, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);
        bottomNav.setOnNavigationItemSelectedListener(this);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        loadFragments(new HomeFragment(homeViewModel));

    }

    //for toolbar
    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        //drawerLayout.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }*/

    /*private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
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
            };*/

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

        Fragment fragment = null;
        final int NAV_HOME = R.id.nav_home;
        final int DRW_HOME = R.id.nav_home1;

        final int NAV_ACC = R.id.nav_account;
        final int DRW_ACC = R.id.nav_account1;

        final int DRW_ORDER = R.id.nav_order1;

        final int DRW_CART = R.id.nav_cart1;
        final int NAV_CART = R.id.nav_cart;

        final int DRW_WISHLIST = R.id.nav_wishlist1;
        final int DRW_CONTACT = R.id.nav_contact;
        final int DRW_CONDITION = R.id.nav_conditions;
        final int NAV_CATEGORY = R.id.nav_category;
        int itemId = item.getItemId();
        if (itemId==NAV_HOME || itemId==DRW_HOME)
        {
            fragment = new HomeFragment(homeViewModel);
        }else if (itemId==DRW_ACC || itemId==NAV_ACC)
        {

        }else if (itemId==DRW_ORDER)
        {

        }else if (itemId==DRW_CART || itemId==NAV_CART)
        {

        }else if (itemId==DRW_WISHLIST)
        {

        }else if (itemId==DRW_CONTACT)
        {

        }else if (itemId==DRW_CONDITION)
        {

        }else if (itemId==NAV_CATEGORY)
        {

        }

        return loadFragments(fragment);
    }



/**
 * For Search menu
 */
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



/**
 * Called when a view has been clicked.
 *
 * @param v The view that was clicked.
 */
@Override
public void onClick(View v) {

}




}
