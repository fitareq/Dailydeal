package com.dailydealbd.viewmodel.repositories;

import android.app.AlertDialog;
import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.aamarpay.library.AamarPay;
import com.aamarpay.library.DialogBuilder;
import com.dailydealbd.network.APIInstance;
import com.dailydealbd.network.DailyDealApi;
import com.dailydealbd.roomdata.LocalDatabase;
import com.dailydealbd.roomdata.model.Order;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.roomdata.model.dao.UserDao;
import com.dailydealbd.utils.OnClickRoutes;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderRepository {


    private Application application;
    private final DailyDealApi api;
    private UserDao userDao;
    private LiveData<User> user;
    private OnClickRoutes.orderFragmentListener orderFragmentListener;
    public OrderRepository(Application application)
    {
        this.application = application;
        api = APIInstance.retroInstance().create(DailyDealApi.class);
        LocalDatabase db = LocalDatabase.getINSTANCE(application);
        userDao = db.userDao();
        user = userDao.getCurrentUser();
    }



    public void setOrderFragmentListener(OnClickRoutes.orderFragmentListener orderFragmentListener) {

        this.orderFragmentListener = orderFragmentListener;
    }



    public LiveData<User> getUser()
    {
        return user;
    }

    public void checkOut(Order order)
    {

        if (order.getPayment_method().equals("Online Payment"))
        {
            AlertDialog alertDialog = null;
            DialogBuilder dialogBuilder = new DialogBuilder(application.getApplicationContext(), alertDialog);

            dialogBuilder.showLoading();
            AamarPay aamarPay = new AamarPay(application.getApplicationContext(), "aamarpay", "28c78bb1f45112f5d40b956fe104645a");
            aamarPay.testMode(true);
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
                    placeOrder(order);

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
        }else placeOrder(order);


    }

    public void placeOrder(Order order)
    {
        Call<Order> call = api.checkoutProduct(order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(application.getApplicationContext(), "Order Placed Successfully", Toast.LENGTH_LONG).show();
                    orderFragmentListener.orderToCart();
                }else Toast.makeText(application.getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
            }



            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });
    }
}
