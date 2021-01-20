package com.dailydealbd.view.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aamarpay.library.AamarPay;
import com.aamarpay.library.DialogBuilder;
import com.dailydealbd.R;
import com.dailydealbd.adapter.CartAdapter;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.Order;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.CartViewModel;
import com.reginald.editspinner.EditSpinner;

import org.json.JSONObject;

import java.util.List;
import java.util.Random;


public class CartFragment extends Fragment implements OnClickRoutes.cartAdapterClickListener {


    public CartFragment(OnClickRoutes.cartFragmentListener cartFragmentListener) {

        // Required empty public constructor
        this.cartFragmentListener = cartFragmentListener;
    }



    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private CartViewModel viewModel;
    private Button cartAllCheckout;
    private TextView cartAllPrice;
    private String price;
    private OnClickRoutes.cartFragmentListener cartFragmentListener;

    private boolean isUserLoggedIn = false;
    private int position = 0;

    private List<Cart> cartList;
    private LinearLayout cartContainer, checkoutContainer;

    private ImageButton OrderBackBtn, cartBackBtn;
    private Button PlaceOderBtn;
    private EditText checkoutName, checkoutAddress, checkoutPhone, checkoutEmail, checkoutMessage;
    private EditSpinner spinner, checkoutCity;

    private User cUser;
    private boolean isCheckoutAll = false;
    private String paymentMethod;
    private String name;
    private String address;
    private String city;
    private String phone;
    private String email;
    private String message;

    Order order = new Order();
    Cart cart;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = v.findViewById(R.id.cart_recycler_view);
        cartAllCheckout = v.findViewById(R.id.cart_all_product_checkout);
        cartAllPrice = v.findViewById(R.id.cart_all_product_total_price);
        cartContainer = v.findViewById(R.id.cart_container);
        checkoutContainer = v.findViewById(R.id.checkout_container);

        OrderBackBtn = v.findViewById(R.id.order_back_btn);
        PlaceOderBtn = v.findViewById(R.id.place_order_btn);
        checkoutName = v.findViewById(R.id.checkout_name);
        checkoutAddress = v.findViewById(R.id.checkout_address);
        checkoutCity = v.findViewById(R.id.checkout_city);
        checkoutPhone = v.findViewById(R.id.checkout_contact_number);
        checkoutEmail = v.findViewById(R.id.checkout_email);
        checkoutMessage = v.findViewById(R.id.checkout_message);
        spinner = v.findViewById(R.id.checkout_spinner);
        cartBackBtn = v.findViewById(R.id.cart_back_btn);


        cartBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartFragmentListener.cartToHome();
            }
        });

        String[] arrayListCity = {"Dhaka", "Dhaka (outside)","Chittagong","Chittagong (outside)","Rajshahi","Rajshahi (outside)","Khulna","Khulna (outside)"};
        ArrayAdapter<String> cityOptionAdapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, arrayListCity);
        checkoutCity.setEditable(false);
        checkoutCity.setText(arrayListCity[0]);
        checkoutCity.setAdapter(cityOptionAdapter);


        String[] arrayList = {"Cash On Delivery", "Online Payment"};
        ArrayAdapter<String> paymentOptionAdapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, arrayList);
        spinner.setEditable(false);
        spinner.setText(arrayList[0]);
        spinner.setAdapter(paymentOptionAdapter);

        manager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        viewModel.getUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {

                if (user != null) {
                    isUserLoggedIn = true;
                    cUser = user;
                    //cartFragmentListener.cartToLogin();
                }
            }
        });
        viewModel.getAllCart().observe(getViewLifecycleOwner(), carts -> {
            cartList = carts;
            adapter = new CartAdapter(carts, CartFragment.this);
            recyclerView.setAdapter(adapter);
            if (carts!=null)
            {
                int tPrice = 0;
                for (Cart c: carts)
                {
                    tPrice+=Integer.parseInt(c.getProductTotalPrice());
                }
                cartAllPrice.setText(String.valueOf(tPrice));
            }
            if (position != 0)
                recyclerView.scrollToPosition(position);
        });

        cartAllCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                isCheckoutAll = true;

                initializeCheckout();
               /*Dialog dialog = new Dialog(getContext());


               final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(),R.style.Widget_MaterialComponents_BottomSheet_Modal);
               View bottomSheet = LayoutInflater.from(getContext()).inflate(R.layout.fragment_order,v.findViewById(R.id.bottom_sheet_container));

               dialog.setContentView(bottomSheet);

               dialog.show();
               bottomSheetDialog.setContentView(bottomSheet);
               bottomSheetDialog.show();*/

            }
        });


        PlaceOderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*ProgressDialog dialog = new ProgressDialog(getContext());
                dialog.setTitle("Placing Order");
                dialog.setMessage("Processing...");
                dialog.setCancelable(false);
                dialog.show();*/

                paymentMethod    = spinner.getText().toString();
                name             = checkoutName.getText().toString();
                address          = checkoutAddress.getText().toString();
                city             = checkoutCity.getText().toString();
                phone            = checkoutPhone.getText().toString();
                email            = checkoutEmail.getText().toString();
                message          = checkoutMessage.getText().toString();


                if (TextUtils.isEmpty(name)) {
                    checkoutName.setError("Required field");
                } else if (TextUtils.isEmpty(email)) {
                    checkoutEmail.setError("Required field");
                } else if (TextUtils.isEmpty(phone)) {
                    checkoutPhone.setError("Required field");
                } else if (TextUtils.isEmpty(city)) {
                    checkoutCity.setError("Required field");
                } else if (TextUtils.isEmpty(address)) {
                    checkoutAddress.setError("Required field");
                }else
                {
                    order.setPayment_method(paymentMethod);
                    order.setUser_id(cUser.getUserId());
                    order.setName(name);
                    order.setEmail(email);
                    order.setPhone(phone);
                    order.setShipping_address(address);
                    order.setCity_name(city);
                    order.setMessage(message);
                    if (city.equals("Dhaka"))
                        order.setShipping_cost("0");
                    else order.setShipping_cost("80");
                    if (paymentMethod.equals("Online Payment"))
                    {
                        /*dialog.dismiss();*/
                        DialogBuilder dialogBuilder = new DialogBuilder(getContext(), null);
                        dialogBuilder.showLoading();
                        AamarPay aamarPay = new AamarPay(getContext(), "aamarpay", "28c78bb1f45112f5d40b956fe104645a");
                        aamarPay.testMode(true);
                        aamarPay.autoGenerateTransactionID(true);
                        String trxId = aamarPay.generate_trx_id();
                        order.setTransaction_id(trxId);
                        aamarPay.setTransactionID(trxId);
                        String trAmount;
                        if (isCheckoutAll)
                            trAmount = cartAllPrice.getText().toString();
                        else trAmount = cart.getProductTotalPrice();
                        aamarPay.setTransactionParameter(trAmount, "BDT", "title");
                        aamarPay.setCustomerDetails(name,email,phone,address,city, "Bangladesh");
                        aamarPay.initPGW(new AamarPay.onInitListener() {
                            @Override
                            public void onInitFailure(Boolean error, String message) {
                                dialogBuilder.dismissDialog();
                                dialogBuilder.errorPopUp(message);
                            }



                            @Override
                            public void onPaymentSuccess(JSONObject jsonObject) {
                                dialogBuilder.dismissDialog();
                                order.setTransaction_id(trxId);
                                placeOrder();
                            }



                            @Override
                            public void onPaymentFailure(JSONObject jsonObject) {
                                dialogBuilder.dismissDialog();
                                dialogBuilder.errorPopUp("payment failed");
                            }



                            @Override
                            public void onPaymentProcessingFailed(JSONObject jsonObject) {
                                dialogBuilder.dismissDialog();
                                dialogBuilder.errorPopUp("payment processing failed");
                            }



                            @Override
                            public void onPaymentCancel(JSONObject jsonObject) {
                                dialogBuilder.dismissDialog();
                                dialogBuilder.errorPopUp("payment canceled");
                            }
                        });

                    }else {
                        Random random = new Random();
                        int a = random.nextInt(123456789);
                        int b = random.nextInt(99999999);
                        order.setTransaction_id(String.valueOf(a+b));
                        placeOrder();
                    }
                }



            }
        });

        OrderBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartContainer.setVisibility(View.VISIBLE);
                checkoutContainer.setVisibility(View.GONE);
            }
        });
        return v;
    }



    @Override
    public void deleteCart(Cart cart) {

        viewModel.deleteCart(cart);
    }



    @Override
    public void updateCart(Cart cart, int position) {

        this.position = position;
        viewModel.updateCart(cart);
    }



    @Override
    public void checkoutCart(Cart cart) {


        /*cartFragmentListener.cartToOrder(productId, title, image, totalPrice, quantity,attributeOption);*/
        this.cart = cart;
        isCheckoutAll = false;
        initializeCheckout();

    }


    void placeOrder()
    {


        if (isCheckoutAll) {
            for (Cart c : cartList) {
                order.setProduct_id(c.getProductId());
                order.setProduct_quantity(String.valueOf(c.getProductQuantity()));
                order.setAttribute_options(c.getAttributesOption());
                order.setPrice(c.getProductPrice());
                order.setAmount(String.valueOf(c.getProductTotalPrice()));
                viewModel.checkOut(order);
            }
        }else
        {
            order.setProduct_id(cart.getProductId());
            order.setProduct_quantity(String.valueOf(cart.getProductQuantity()));
            order.setAttribute_options(cart.getAttributesOption());
            order.setPrice(cart.getProductPrice());
            viewModel.checkOut(order);
        }


    }

    public void initializeCheckout()
    {
        cartContainer.setVisibility(View.GONE);
        checkoutContainer.setVisibility(View.VISIBLE);

        if (isUserLoggedIn) {
            checkoutName.setText(cUser.getUserName());
            checkoutEmail.setText(cUser.getUserEmail());
            checkoutPhone.setText(cUser.getPhoneNumber());
        } else cartFragmentListener.cartToLogin();
    }




}