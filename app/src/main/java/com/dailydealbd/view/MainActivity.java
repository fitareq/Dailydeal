package com.dailydealbd.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dailydealbd.R;
import com.dailydealbd.utils.ConstantsResources;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.view.fragments.AccountFragment;
import com.dailydealbd.view.fragments.CartFragment;
import com.dailydealbd.view.fragments.CategoryFragment;
import com.dailydealbd.view.fragments.HomeFragment;
import com.dailydealbd.view.fragments.ImageFragment;
import com.dailydealbd.view.fragments.LoginFragment;
import com.dailydealbd.view.fragments.OrderFragment;
import com.dailydealbd.view.fragments.RegisterFragment;
import com.dailydealbd.view.fragments.SettingsFragment;
import com.dailydealbd.view.fragments.SingleProductFragment;
import com.dailydealbd.viewmodel.AccountViewModel;
import com.dailydealbd.viewmodel.MainViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayDeque;
import java.util.Deque;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,
                           OnClickRoutes.cartClickListener,
                           OnClickRoutes.registrationClickListener,
                           OnClickRoutes.loginClickListener,
                           OnClickRoutes.singleProductMyCartClick,
                           OnClickRoutes.singleProductImageClick,
                           OnClickRoutes.fullImageClickListener,
                           OnClickRoutes.singleProductBackPressed,
                           OnClickRoutes.singleProductClickListener,
                           OnClickRoutes.homeClickListener,
                           OnClickRoutes.accountFragmentListener,
                           OnClickRoutes.orderFragmentListener {


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private BottomNavigationView bottomNav;
    private Fragment selectedFragment;
    private Deque<Fragment> loadedFragment = new ArrayDeque<>();
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

    private final HomeFragment homeFragment = new HomeFragment(this);
    private final CategoryFragment categoryFragment = new CategoryFragment();
    private final CartFragment cartFragment = new CartFragment(this);
    private final AccountFragment accountFragment = new AccountFragment(this);
    private final LoginFragment loginFragment = new LoginFragment(this);
    private final RegisterFragment registerFragment = new RegisterFragment(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.fetchProductsDataFromRemote();
        viewModel.fetchSliderDataFromRemote();
        viewModel.fetchCategoriesDataFromRemote();
        viewModel.fetchBannerDataFromRemote();
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayoutId);
        NavigationView navigationDrawer = findViewById(R.id.navigation_drawer);
        bottomNav = findViewById(R.id.bottom_nav);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.start, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationDrawer.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case DRW_ACC:
                    selectedFragment = accountFragment;
                    loadedFragment.push(selectedFragment);
                    tag = ConstantsResources.ACCOUNT_FRAGMENT;
                    bottomNav.setSelectedItemId(R.id.nav_account);
                    break;
                case DRW_CART:
                    selectedFragment = cartFragment;
                    loadedFragment.push(selectedFragment);
                    tag = ConstantsResources.CART_FRAGMENT;
                    bottomNav.setSelectedItemId(R.id.nav_cart);
                    break;
                case DRW_HOME:
                case DRW_CONDITION:
                case DRW_CONTACT:
                case DRW_WISHLIST:
                case DRW_ORDER:
                    selectedFragment = homeFragment;
                    loadedFragment.push(selectedFragment);
                    toolbar.setTitle("Home");
                    tag = ConstantsResources.HOME_FRAGMENT;
                    break;
                    /*selectedFragment = new HomeFragment((OnClickRoutes.homeClickListener) MainActivity.this);
                    tag = ConstantsResources.HOME_FRAGMENT;*/

                    /*selectedFragment = new HomeFragment((OnClickRoutes.homeClickListener) MainActivity.this);
                    tag = ConstantsResources.HOME_FRAGMENT;
                    bottomNav.setSelectedItemId(R.id.nav_home);
                    break;*/

                    /*selectedFragment = new HomeFragment((OnClickRoutes.homeClickListener) MainActivity.this);
                    tag = ConstantsResources.HOME_FRAGMENT;
                    break;*/

                    /*selectedFragment = new HomeFragment((OnClickRoutes.homeClickListener) MainActivity.this);
                    tag = ConstantsResources.HOME_FRAGMENT;
                    break;*/
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return loadFragments();
        });


        bottomNav.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case NAV_ACC:
                    selectedFragment = new AccountFragment(MainActivity.this);
                    loadedFragment.push(selectedFragment);
                    tag = ConstantsResources.ACCOUNT_FRAGMENT;
                    break;
                case NAV_CART:
                    selectedFragment = cartFragment;
                    loadedFragment.push(selectedFragment);
                    tag = ConstantsResources.CART_FRAGMENT;
                    break;
                case NAV_CATEGORY:
                    selectedFragment = categoryFragment;
                    loadedFragment.push(selectedFragment);
                    toolbar.setTitle("Category");
                    tag = ConstantsResources.CATEGORY_FRAGMENT;
                    break;
                case NAV_HOME:
                    selectedFragment = homeFragment;
                    loadedFragment.push(selectedFragment);
                    toolbar.setTitle("Home");
                    tag = ConstantsResources.HOME_FRAGMENT;
                    break;

            }
            return loadFragments();
        });


        loadFragments();
    }



    //common loadFragments method
    private boolean loadFragments() {

        if (selectedFragment != null) {


            if (tag.equals(ConstantsResources.SINGLE_PRODUCT_FRAGMENT) ||
                        tag.equals(ConstantsResources.ACCOUNT_FRAGMENT) ||
                        tag.equals(ConstantsResources.ORDER_FRAGMENT) ||
                        tag.equals(ConstantsResources.LOGIN_FRAGMENT) ||
                        tag.equals(ConstantsResources.REGISTRATION_FRAGMENT)
            ) {
                bottomNav.setVisibility(View.GONE);
                toolbar.setVisibility(View.GONE);
            } else if (tag.equals(ConstantsResources.CART_FRAGMENT)) {
                toolbar.setVisibility(View.GONE);
                bottomNav.setVisibility(View.VISIBLE);
            } else {
                bottomNav.setVisibility(View.VISIBLE);
                toolbar.setVisibility(View.VISIBLE);
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
            return true;
        } else {
            selectedFragment = homeFragment;
            loadedFragment.push(selectedFragment);
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

        getMenuInflater().inflate(R.menu.toolbar_search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
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



    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);

        if (loadedFragment.size() >= 1) {
            //Toast.makeText(this, String.valueOf(loadedFragment.size()), Toast.LENGTH_SHORT).show();

            //loadedFragment.pop();
            selectedFragment = loadedFragment.pop();
            loadFragments();
        } else super.onBackPressed();

        /*if (tag.equals(ConstantsResources.SINGLE_PRODUCT_FRAGMENT)||
                    tag.equals(ConstantsResources.REGISTRATION_FRAGMENT) ||
                    tag.equals(ConstantsResources.ACCOUNT_FRAGMENT) ||
                    tag.equals(ConstantsResources.LOGIN_FRAGMENT))
        {
            selectedFragment = new HomeFragment(this);
            tag = ConstantsResources.HOME_FRAGMENT;
            bottomNav.setSelectedItemId(R.id.nav_home);
            loadFragments();
        }
        else super.onBackPressed();*/

   /* else if (getSupportFragmentManager().getBackStackEntryCount()>0)
        getSupportFragmentManager().popBackStack();*/

    }



    @Override
    public void homeToCategory(int cId, String cTitle) {

        selectedFragment = new CategoryFragment(cId, cTitle);
        loadedFragment.push(selectedFragment);
        bottomNav.setSelectedItemId(NAV_CATEGORY);
        tag = ConstantsResources.CATEGORY_FRAGMENT;
        loadFragments();
    }



    @Override
    public void loadSingleProductData(String slug) {

        selectedFragment = new SingleProductFragment(slug, this, this, this);
        loadedFragment.push(selectedFragment);
        tag = ConstantsResources.SINGLE_PRODUCT_FRAGMENT;
        loadFragments();
    }



    @Override
    public void singleProductBackPressedListener() {

        onBackPressed();
    }



    @Override
    public void fullImageOnBackClick(String slug) {

        loadSingleProductData(slug);
    }



    @Override
    public void showFullImage(String image, String slug) {

        selectedFragment = new ImageFragment(image, this, slug);
        loadedFragment.push(selectedFragment);
        tag = ConstantsResources.FULL_IMAGE_FRAGMENT;
        loadFragments();
    }



    @Override
    public void goToMyCart() {

        selectedFragment = cartFragment;
        loadedFragment.push(selectedFragment);
        tag = ConstantsResources.CART_FRAGMENT;
        bottomNav.setSelectedItemId(R.id.nav_cart);
        loadFragments();
    }



    @Override
    public void loginToRegistration() {

        selectedFragment = registerFragment;
        loadedFragment.push(selectedFragment);
        tag = ConstantsResources.REGISTRATION_FRAGMENT;
        loadFragments();
    }



    @Override
    public void loginToHome() {

        selectedFragment = homeFragment;
        loadedFragment.push(selectedFragment);
        tag = ConstantsResources.HOME_FRAGMENT;
        bottomNav.setSelectedItemId(R.id.nav_home);
        loadFragments();
    }



    @Override
    public void goToLoginFromRegistration() {

        selectedFragment = loginFragment;
        loadedFragment.push(selectedFragment);
        tag = ConstantsResources.LOGIN_FRAGMENT;
        loadFragments();
    }



    @Override
    public void cartToLogin() {

        goToLoginFromRegistration();
    }



    @Override
    public void cartToOrder(int productId, String title, String image, String totalPrice, int quantity, String attributeOption) {

        selectedFragment = new OrderFragment(this, productId, title, image, totalPrice, quantity, attributeOption);
        loadedFragment.push(selectedFragment);
        tag = ConstantsResources.ORDER_FRAGMENT;
        loadFragments();
    }



    @Override
    public void accountToLogin() {

        goToLoginFromRegistration();
    }



    @Override
    public void accountToSettings(AccountViewModel viewModel) {

        selectedFragment = new SettingsFragment(viewModel);
        loadedFragment.push(selectedFragment);
        tag = ConstantsResources.SETTINGS_FRAGMENT;
        loadFragments();
    }



    @Override
    public void orderToCart() {

        goToMyCart();
    }




}
