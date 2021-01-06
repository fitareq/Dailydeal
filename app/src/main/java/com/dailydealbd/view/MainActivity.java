package com.dailydealbd.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.dailydealbd.R;
import com.dailydealbd.utils.ConstantsResources;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.view.fragments.CartFragment;
import com.dailydealbd.view.fragments.CategoryFragment;
import com.dailydealbd.view.fragments.HomeFragment;
import com.dailydealbd.view.fragments.SingleProductFragment;
import com.dailydealbd.viewmodel.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener, OnClickRoutes.loadSingleProduct, OnClickRoutes.loadCategoryFromHome{

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private SearchView searchView;
    private NavigationView navigationDrawer;
    private BottomNavigationView bottomNav;
    private HomeViewModel homeViewModel;
    private Fragment selectedFragment;
    private String tag;

    private final int NAV_HOME = R.id.nav_home;
    private final int DRW_HOME = R.id.nav_home1;
    private final int NAV_ACC = R.id.nav_account;
    private final int DRW_ACC = R.id.nav_account1;
    private final int DRW_ORDER = R.id.nav_order1;
    private final int DRW_CART = R.id.nav_cart1;
    private final int NAV_CART = R.id.nav_cart;
    private final int DRW_WISHLIST = R.id.nav_wishlist1;
    private final int DRW_CONTACT = R.id.nav_contact;
    private final int DRW_CONDITION = R.id.nav_conditions;
    private final int NAV_CATEGORY = R.id.nav_category;

    private List<Fragment> fragmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayoutId);
        navigationDrawer = findViewById(R.id.navigation_drawer);
        bottomNav = findViewById(R.id.bottom_nav);


        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.start, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case DRW_ACC:
                        selectedFragment = new HomeFragment((OnClickRoutes.loadCategoryFromHome) MainActivity.this);
                        tag = ConstantsResources.HOME_FRAGMENT;
                        break;
                    case DRW_CART:
                        selectedFragment = new CartFragment();
                        tag = ConstantsResources.CART_FRAGMENT;
                        break;
                    case DRW_CONDITION:
                        selectedFragment = new HomeFragment((OnClickRoutes.loadCategoryFromHome) MainActivity.this);
                        tag = ConstantsResources.HOME_FRAGMENT;
                        break;
                    case DRW_CONTACT:
                        selectedFragment = new HomeFragment((OnClickRoutes.loadCategoryFromHome) MainActivity.this);
                        tag = ConstantsResources.HOME_FRAGMENT;
                        break;
                    case DRW_HOME:
                        selectedFragment = new HomeFragment((OnClickRoutes.loadCategoryFromHome) MainActivity.this);
                        tag = ConstantsResources.HOME_FRAGMENT;
                        break;
                    case DRW_WISHLIST:
                        selectedFragment = new HomeFragment((OnClickRoutes.loadCategoryFromHome) MainActivity.this);
                        tag = ConstantsResources.HOME_FRAGMENT;
                        break;
                    case DRW_ORDER:
                        selectedFragment = new HomeFragment((OnClickRoutes.loadCategoryFromHome) MainActivity.this);
                        tag = ConstantsResources.HOME_FRAGMENT;
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return loadFragments();
            }
        });




        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case NAV_ACC:
                        selectedFragment = new HomeFragment((OnClickRoutes.loadCategoryFromHome) MainActivity.this);
                        tag = ConstantsResources.HOME_FRAGMENT;
                        break;
                    case NAV_CART:
                        selectedFragment = new CartFragment();
                        tag = ConstantsResources.CART_FRAGMENT;
                        break;
                    case NAV_CATEGORY:
                        selectedFragment = new CategoryFragment();
                        tag = ConstantsResources.CATEGORY_FRAGMENT;
                        break;
                    case NAV_HOME:
                        selectedFragment = new HomeFragment((OnClickRoutes.loadCategoryFromHome) MainActivity.this);
                        tag = ConstantsResources.HOME_FRAGMENT;
                        break;

                }
                return loadFragments();
            }
        });




        loadFragments();

    }




    //common loadFragments method
    private boolean loadFragments() {
        if (selectedFragment != null) {

            if (tag.equals(ConstantsResources.SINGLE_PRODUCT_FRAGMENT))
                bottomNav.setVisibility(View.GONE);
            else bottomNav.setVisibility(View.VISIBLE);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
            return true;
        }else {
            selectedFragment = new HomeFragment((OnClickRoutes.loadCategoryFromHome) this);
            tag = ConstantsResources.HOME_FRAGMENT;
            loadFragments();
        }
        return false;
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



/**
 * Called when the activity has detected the user's press of the back
 * key. The {@link #getOnBackPressedDispatcher() OnBackPressedDispatcher} will be given a
 * chance to handle the back button before the default behavior of
 * {@link Activity#onBackPressed()} is invoked.
 *
 * @see #getOnBackPressedDispatcher()
 */
@Override
public void onBackPressed() {

    if (drawerLayout.isDrawerOpen(GravityCompat.START))
        drawerLayout.closeDrawer(GravityCompat.START);

    if (tag.equals(ConstantsResources.SINGLE_PRODUCT_FRAGMENT)) {
        selectedFragment = new HomeFragment(this);
        tag = ConstantsResources.HOME_FRAGMENT;
        loadFragments();
    }

   /* else if (getSupportFragmentManager().getBackStackEntryCount()>0)
        getSupportFragmentManager().popBackStack();*/
    else super.onBackPressed();
}



    @Override
    public void homeToCategory(int cId, String cTitle) {
    selectedFragment = new CategoryFragment(cId,cTitle);
    bottomNav.setSelectedItemId(NAV_CATEGORY);
        tag = ConstantsResources.CATEGORY_FRAGMENT;
    loadFragments();
    }



    @Override
    public void loadSingleProductData(String slug) {
        selectedFragment = new SingleProductFragment(slug);
        tag = ConstantsResources.SINGLE_PRODUCT_FRAGMENT;
        loadFragments();
    }




}
