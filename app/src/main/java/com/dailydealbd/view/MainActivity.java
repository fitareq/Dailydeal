package com.dailydealbd.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.utils.ConstantsResources;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.view.fragments.AccountFragment;
import com.dailydealbd.view.fragments.CartFragment;
import com.dailydealbd.view.fragments.CategoryFragment;
import com.dailydealbd.view.fragments.ContactUsFragment;
import com.dailydealbd.view.fragments.HomeFragment;
import com.dailydealbd.view.fragments.ImageFragment;
import com.dailydealbd.view.fragments.LoginFragment;
import com.dailydealbd.view.fragments.OrderFragment;
import com.dailydealbd.view.fragments.RegisterFragment;
import com.dailydealbd.view.fragments.SettingsFragment;
import com.dailydealbd.view.fragments.SingleProductFragment;
import com.dailydealbd.view.fragments.WishlistFragment;
import com.dailydealbd.viewmodel.AccountViewModel;
import com.dailydealbd.viewmodel.MainViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,
                           OnClickRoutes.contactUsFragmentListener,
                           OnClickRoutes.cartFragmentListener,
                           OnClickRoutes.registrationClickListener,
                           OnClickRoutes.loginFragmentListener,
                           OnClickRoutes.singleProductMyCartClick,
                           OnClickRoutes.singleProductImageClick,
                           OnClickRoutes.fullImageClickListener,
                           OnClickRoutes.singleProductBackPressed,
                           OnClickRoutes.singleProductClickListener,
                           OnClickRoutes.homeFragmentListener,
                           OnClickRoutes.accountFragmentListener,
                           OnClickRoutes.orderFragmentListener,
                           OnClickRoutes.wishlistFragmentListener {



    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private BottomNavigationView bottomNav;
    private Fragment selectedFragment;
    private String tag;
    private MainViewModel viewModel;
    private Deque<Fragment> fragmentDeque = new ArrayDeque<>();

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
    private final CategoryFragment categoryFragment = new CategoryFragment(0, null);
    private final CartFragment cartFragment = new CartFragment(this);
    private final AccountFragment accountFragment = new AccountFragment(this);
    private final LoginFragment loginFragment = new LoginFragment(this);
    private final RegisterFragment registerFragment = new RegisterFragment(this);
    private final ContactUsFragment contactUsFragment = new ContactUsFragment(this);
    private final WishlistFragment wishlistFragment = new WishlistFragment(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
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
                    break;
                case DRW_CART:
                    selectedFragment = cartFragment;
                    break;
                case DRW_WISHLIST:
                    selectedFragment = wishlistFragment;
                    break;
                case DRW_CONTACT:
                    selectedFragment = contactUsFragment;
                    break;
                case DRW_HOME:
                case DRW_CONDITION:


                case DRW_ORDER:
                    selectedFragment = homeFragment;
                    break;

            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return loadFragments();
        });


        bottomNav.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case NAV_ACC:
                    selectedFragment = accountFragment;
                    break;
                case NAV_CART:
                    selectedFragment = cartFragment;
                    break;
                case NAV_CATEGORY:
                    selectedFragment = categoryFragment;
                    break;
                case NAV_HOME:
                    selectedFragment = homeFragment;
                    break;

            }
            return loadFragments();
        });


        viewModel.getCarts().observe(this, new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> cartList) {

                if (cartList != null) {
                    int size = cartList.size();
                    if (size > 0)
                        bottomNav.getOrCreateBadge(R.id.nav_cart).setNumber(size);
                    else
                        bottomNav.removeBadge(R.id.nav_cart);
                }
            }
        });

        loadFragments();
    }



    //common loadFragments method
    private boolean loadFragments() {

        if (selectedFragment != null) {
            if (!fragmentDeque.contains(selectedFragment))
                fragmentDeque.push(selectedFragment);
            tag = selectedFragment.getClass().getCanonicalName();


            if (tag.equals(ConstantsResources.SINGLE_PRODUCT_FRAGMENT) ||
                        tag.equals(ConstantsResources.ACCOUNT_FRAGMENT) ||
                        tag.equals(ConstantsResources.ORDER_FRAGMENT) ||
                        tag.equals(ConstantsResources.LOGIN_FRAGMENT) ||
                        tag.equals(ConstantsResources.REGISTRATION_FRAGMENT) ||
                        tag.equals(ConstantsResources.CART_FRAGMENT) ||
                        tag.equals(ConstantsResources.WISHLIST_FRAGMENT) ||
                        tag.equals(ConstantsResources.CONTACTUS_FRAGMENT)
            ) {
                bottomNav.setVisibility(View.GONE);
                toolbar.setVisibility(View.GONE);
            } else {
                bottomNav.setVisibility(View.VISIBLE);
                toolbar.setVisibility(View.VISIBLE);
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
            return true;
        } else {
            selectedFragment = homeFragment;
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

        if (fragmentDeque.size() > 1) {
            fragmentDeque.pop();
            selectedFragment = fragmentDeque.pop();
            if (selectedFragment.getClass().getName().equals(ConstantsResources.HOME_FRAGMENT)) {
                bottomNav.setSelectedItemId(R.id.nav_home);
            } else if (selectedFragment.getClass().getName().equals(ConstantsResources.CATEGORY_FRAGMENT)) {
                bottomNav.setSelectedItemId(R.id.nav_category);
            }
            loadFragments();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to close the app?");
            builder.setCancelable(false);
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                }
            });
            builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }


    }



    @Override
    public void homeToCategory(int cId, String cTitle) {

        selectedFragment = categoryFragment;
        bottomNav.setSelectedItemId(R.id.nav_category);
        loadFragments();
    }



    @Override
    public void loadSingleProductData(String slug) {

        selectedFragment = new SingleProductFragment(slug, this, this, this);
        loadFragments();
    }



    @Override
    public void singleProductBackPressedListener() {

        onBackPressed();
    }



    @Override
    public void fullImageOnBackClick(String slug) {

        onBackPressed();
    }



    @Override
    public void showFullImage(String image, String slug) {

        selectedFragment = new ImageFragment(image, this, slug);
        loadFragments();
    }



    @Override
    public void goToMyCart() {

        selectedFragment = cartFragment;
        loadFragments();
    }



    @Override
    public void loginToRegistration() {

        selectedFragment = registerFragment;
        loadFragments();
    }



    @Override
    public void loginToHome() {

        selectedFragment = homeFragment;
        bottomNav.setSelectedItemId(R.id.nav_home);
        loadFragments();
    }



    @Override
    public void goToLoginFromRegistration() {

        selectedFragment = loginFragment;
        loadFragments();
    }



    @Override
    public void cartToLogin() {

        goToLoginFromRegistration();
    }



    @Override
    public void cartToHome() {

        onBackPressed();
    }



    @Override
    public void cartToOrder(int productId, String title, String image, String totalPrice, int quantity, String attributeOption) {

        selectedFragment = new OrderFragment(this, productId, title, image, totalPrice, quantity, attributeOption);
        loadFragments();
    }



    @Override
    public void accountToLogin() {

        goToLoginFromRegistration();
    }



    @Override
    public void accountToSettings(AccountViewModel viewModel) {

        selectedFragment = new SettingsFragment(viewModel);
        loadFragments();
    }



    @Override
    public void accountBackBtnPressed() {

        loginToHome();
    }



    @Override
    public void accountToCart() {

        goToMyCart();
    }



    @Override
    public void accountToOrder() {

    }



    @Override
    public void accountToWishList() {

        selectedFragment = wishlistFragment;
        loadFragments();
    }



    @Override
    public void orderToCart() {

        goToMyCart();
    }



    @Override
    public void wishlishtBackBtnPressed() {

        onBackPressed();
    }



    @Override
    public void wishlistToSingleProduct(String slug) {

        selectedFragment = new SingleProductFragment(slug, this, this, this);
        loadFragments();
    }



    @Override
    public void conctactUsMapClickListener() {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Sultan Ahmed Plaza, Dhaka"));
        startActivity(intent);
    }



    @Override
    public void conctactUsPhoneClickListener() {

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01705401056"));
        startActivity(intent);
    }



    @Override
    public void contactUsBackPressed() {

        onBackPressed();
    }




}
