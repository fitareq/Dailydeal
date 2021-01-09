package com.dailydealbd.roomdata.model;

public class Order {

    private final String user_id;
    private final String product_id;
    private  String vendor_id;
    private final String product_title;
    private final String product_image;
    private final String attribute_options;
    private final String product_quantity;
    private  String ip_address;
    private  String carts_id;
    private final String name;
    private final String phone;
    private final String email;
    private final String city_name;
    private final String shipping_address;
    private  String message;
    private final String amount;
    private  String price;
    private final String shipping_cost;
    private  String status;
    private final String currency;
    private  String is_completed;
    private final String payment_method;
    private  String delivery_status;
    private  String courier_info;
    private  String tracking_id;
    private  String transaction_id;



    public Order(String user_id, String product_id, String product_title, String product_image,
                 String attribute_options, String product_quantity, String name,
                 String phone, String email, String city_name, String shipping_address, String amount,
                 String shipping_cost, String currency, String payment_method) {

        this.user_id = user_id;
        this.product_id = product_id;
        this.product_title = product_title;
        this.product_image = product_image;
        this.attribute_options = attribute_options;
        this.product_quantity = product_quantity;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.city_name = city_name;
        this.shipping_address = shipping_address;
        this.amount = amount;
        this.shipping_cost = shipping_cost;
        this.currency = currency;
        this.payment_method = payment_method;
    }




}
