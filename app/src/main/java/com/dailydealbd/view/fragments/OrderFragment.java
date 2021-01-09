package com.dailydealbd.view.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aamarpay.library.AamarPay;
import com.aamarpay.library.DialogBuilder;
import com.dailydealbd.R;
import com.dailydealbd.network.APIInstance;
import com.dailydealbd.network.DailyDealApi;
import com.dailydealbd.roomdata.model.Order;
import com.google.android.material.textfield.TextInputEditText;
import com.reginald.editspinner.EditSpinner;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderFragment extends Fragment {


    private ImageButton OrderBackBtn;
    private Button PlaceOderBtn;
    private TextInputEditText CheckoutName, checkoutAddress, checkoutCity, checkoutPhone, checkoutEmail;
    private EditSpinner spinner;


    private int productId, quantity;
    private String title, image, totalPrice, attributeOption;



    public OrderFragment(int productId, String title, String image, String totalPrice, int quantity, String attributeOption) {
        // Required empty public constructor
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
        CheckoutName = v.findViewById(R.id.checkout_name);
        checkoutAddress = v.findViewById(R.id.checkout_address);
        checkoutCity = v.findViewById(R.id.checkout_city);
        checkoutPhone = v.findViewById(R.id.checkout_contact_number);
        checkoutEmail = v.findViewById(R.id.checkout_email);
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

        PlaceOderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DailyDealApi api = APIInstance.retroInstance().create(DailyDealApi.class);

                String paymentMethod = spinner.getText().toString();
                String name = CheckoutName.getText().toString();
                String address = checkoutAddress.getText().toString();
                String city = checkoutCity.getText().toString();
                String phone = checkoutPhone.getText().toString();
                String email = checkoutEmail.getText().toString();



                Order order = new Order("1", String.valueOf(productId), title, image, attributeOption, String.valueOf(quantity), name, phone, email, city, address, totalPrice, "80", "BDT", paymentMethod);
                if (paymentMethod.equals("Online Payment")) {
                    AlertDialog alertDialog = null;
                    DialogBuilder dialogBuilder = new DialogBuilder(getContext(), alertDialog);

                    dialogBuilder.showLoading();
                    AamarPay aamarPay = new AamarPay(getContext(), "aamarpay", "28c78bb1f45112f5d40b956fe104645a");
                    aamarPay.testMode(true);
                    aamarPay.autoGenerateTransactionID(true);
                    String trxId = aamarPay.generate_trx_id();
                    aamarPay.setTransactionID(trxId);
                    String trAmount = String.valueOf(totalPrice);
                    aamarPay.setTransactionParameter(trAmount, "BDT", title);
                    aamarPay.setCustomerDetails(name, email, phone, address, city, "Bangladesh");

                    aamarPay.initPGW(new AamarPay.onInitListener() {
                        @Override
                        public void onInitFailure(Boolean error, String message) {

                            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                            dialogBuilder.errorPopUp(message);
                            //dialogBuilder.dismissDialog();
                        }



                        @Override
                        public void onPaymentSuccess(JSONObject jsonObject) {

                            Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                            dialogBuilder.dismissDialog();

                            Call<Order> call = api.checkoutProduct(order);
                            call.enqueue(new Callback<Order>() {
                                @Override
                                public void onResponse(Call<Order> call, Response<Order> response) {

                                    if (response.isSuccessful())
                                        Toast.makeText(getContext(), "Order placed successfully", Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(getContext(), "Error!!", Toast.LENGTH_SHORT).show();

                                }



                                @Override
                                public void onFailure(Call<Order> call, Throwable t) {

                                }
                            });


                        }



                        @Override
                        public void onPaymentFailure(JSONObject jsonObject) {

                            dialogBuilder.errorPopUp(jsonObject.toString());
                        }



                        @Override
                        public void onPaymentProcessingFailed(JSONObject jsonObject) {

                        }



                        @Override
                        public void onPaymentCancel(JSONObject jsonObject) {

                            dialogBuilder.errorPopUp(jsonObject.toString());
                        }
                    });
                }
                else {
                    Call<Order> call = api.checkoutProduct(order);
                    call.enqueue(new Callback<Order>() {
                        @Override
                        public void onResponse(Call<Order> call, Response<Order> response) {

                            if (response.isSuccessful())
                                Toast.makeText(getContext(), "Order placed successfully", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getContext(), "Error!!", Toast.LENGTH_SHORT).show();

                        }



                        @Override
                        public void onFailure(Call<Order> call, Throwable t) {

                        }
                    });
                }


            }
        });
    }




}