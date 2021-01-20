package com.dailydealbd.view.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.aamarpay.library.AamarPay;
import com.aamarpay.library.DialogBuilder;
import com.dailydealbd.R;
import com.dailydealbd.network.APIInstance;
import com.dailydealbd.network.DailyDealApi;
import com.dailydealbd.roomdata.model.Order;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.OrderViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.reginald.editspinner.EditSpinner;

import org.json.JSONObject;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderFragment extends Fragment {


    private ImageButton OrderBackBtn;
    private Button PlaceOderBtn;
    private EditText checkoutName, checkoutAddress, checkoutCity, checkoutPhone, checkoutEmail, checkoutMessage;
    private EditSpinner spinner;


    private int productId, quantity;
    private String title;
    private String image;
    private String totalPrice;
    private String attributeOption;
    private String paymentMethod;
    private String name;
    private String address;
    private String city;
    private String phone;
    private String email;
    private String message;
    private OrderViewModel viewModel;

    Order order = new Order();

    private OnClickRoutes.orderFragmentListener orderFragmentListener;



    public OrderFragment(OnClickRoutes.orderFragmentListener orderFragmentListener, int productId, String title, String image, String totalPrice, int quantity, String attributeOption) {
        // Required empty public constructor
        this.orderFragmentListener = orderFragmentListener;
        this.productId = productId;
        this.quantity = quantity;
        this.title = title;
        this.image = image;
        this.totalPrice = totalPrice;
        this.attributeOption = attributeOption;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order, container, false);
        OrderBackBtn = v.findViewById(R.id.order_back_btn);
        PlaceOderBtn = v.findViewById(R.id.place_order_btn);
        checkoutName = v.findViewById(R.id.checkout_name);
        checkoutAddress = v.findViewById(R.id.checkout_address);
        checkoutCity = v.findViewById(R.id.checkout_city);
        checkoutPhone = v.findViewById(R.id.checkout_contact_number);
        checkoutEmail = v.findViewById(R.id.checkout_email);
        checkoutMessage = v.findViewById(R.id.checkout_message);
        spinner = v.findViewById(R.id.checkout_spinner);

        String[] arrayList = {"Cash On Delivery", "Online Payment"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, arrayList);
        spinner.setEditable(false);
        spinner.setAdapter(adapter);
        return v;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        viewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        viewModel.setOrderFragmentListener(orderFragmentListener);
        viewModel.getUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {

                if (user != null) {
                    order.setUser_id(user.getUserId());
                    checkoutName.setText(user.getUserName());
                    checkoutEmail.setText(user.getUserEmail());
                    checkoutPhone.setText(user.getPhoneNumber());
                }
            }
        });

        PlaceOderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DailyDealApi api = APIInstance.retroInstance().create(DailyDealApi.class);

                paymentMethod = spinner.getText().toString();
                name = checkoutName.getText().toString();
                address = checkoutAddress.getText().toString();
                city = checkoutCity.getText().toString();
                phone = checkoutPhone.getText().toString();
                email = checkoutEmail.getText().toString();
                message = checkoutMessage.getText().toString();


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
                } else if (TextUtils.equals(paymentMethod, "Payment option")) {
                    spinner.setError("Choose a payment option");
                } else {
                    order.setProduct_id(productId);
                    order.setProduct_quantity(String.valueOf(quantity));
                    order.setAmount(totalPrice);
                    order.setAttribute_options(attributeOption);
                    order.setName(name);
                    order.setEmail(email);
                    order.setPhone(phone);
                    order.setShipping_address(address);
                    order.setPayment_method(paymentMethod);
                    order.setCity_name(city);
                    order.setShipping_cost("80");
                    if (!TextUtils.isEmpty(checkoutMessage.getText().toString()))
                        order.setMessage(checkoutMessage.getText().toString());
                    else order.setMessage(null);



                    checkOut(order);

                    //viewModel.checkOut(order);
                }

            }
        });


        OrderBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderFragmentListener.orderToCart();
            }
        });
    }



    private void checkOut(Order order) {
        if (order.getPayment_method().equals("Online Payment"))
        {
            AlertDialog alertDialog = null;
            DialogBuilder dialogBuilder = new DialogBuilder(getContext(), alertDialog);
            dialogBuilder.showLoading();
            AamarPay aamarPay = new AamarPay(getContext(), "dailydeal", "4e7e973fafe5aaac2e0b84fc3ee666c6");
            aamarPay.testMode(false);
            aamarPay.autoGenerateTransactionID(true);
            String trxId = aamarPay.generate_trx_id();
            aamarPay.setTransactionID(trxId);
            String trAmount = order.getAmount();
            aamarPay.setTransactionParameter(trAmount, "BDT", "title");
            aamarPay.setCustomerDetails(order.getName(), order.getEmail(), order.getPhone(), order.getShipping_address(), order.getCity_name(), "Bangladesh");
            aamarPay.initPGW(new AamarPay.onInitListener() {
                @Override
                public void onInitFailure(Boolean error, String message) {
                    dialogBuilder.dismissDialog();
                    dialogBuilder.errorPopUp(message);
                }



                @Override
                public void onPaymentSuccess(JSONObject jsonObject) {
                    dialogBuilder.dismissDialog();
                    viewModel.checkOut(order);

                }



                @Override
                public void onPaymentFailure(JSONObject jsonObject) {
                    dialogBuilder.dismissDialog();
                    dialogBuilder.errorPopUp("Payment Failure");

                }



                @Override
                public void onPaymentProcessingFailed(JSONObject jsonObject) {
                    dialogBuilder.dismissDialog();
                    dialogBuilder.errorPopUp("Payment Processing Failed");
                }



                @Override
                public void onPaymentCancel(JSONObject jsonObject) {
                    dialogBuilder.dismissDialog();
                    dialogBuilder.errorPopUp("Payment Canceled");
                }
            });
        }else viewModel.checkOut(order);
    }




}