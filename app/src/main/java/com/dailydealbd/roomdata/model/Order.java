package com.dailydealbd.roomdata.model;


/*
user_id
vendor_id_buy
product_id
vendor_id
product_title
product_image
attribute_options
product_quantity
ip_address
carts_id
name
phone
email
city_name
shipping_address
message
amount
price
shipping_cost
status
currency
is_completed
payment_method
delivery_status
courier_info
tracking_id
transaction_id
 */

public class Order {

    private Integer product_id;
    private Integer user_id;
    private String name;
    private String email;
    private String phone;
    private String attribute_options;
    private String product_quantity;
    private String city_name;
    private String shipping_address;
    private String message;
    private String amount;
    private String price;
    private String shipping_cost;
    private String payment_method;


    public Order(){};

    public void setProduct_id(Integer product_id) {

        this.product_id = product_id;
    }



    public void setUser_id(Integer user_id) {

        this.user_id = user_id;
    }



    public void setName(String name) {

        this.name = name;
    }



    public void setEmail(String email) {

        this.email = email;
    }



    public void setPhone(String phone) {

        this.phone = phone;
    }



    public void setAttribute_options(String attribute_options) {

        this.attribute_options = attribute_options;
    }



    public void setProduct_quantity(String product_quantity) {

        this.product_quantity = product_quantity;
    }



    public void setCity_name(String city_name) {

        this.city_name = city_name;
    }



    public void setShipping_address(String shipping_address) {

        this.shipping_address = shipping_address;
    }



    public void setMessage(String message) {

        this.message = message;
    }



    public void setAmount(String amount) {

        this.amount = amount;
    }



    public void setPrice(String price) {

        this.price = price;
    }



    public void setShipping_cost(String shipping_cost) {

        this.shipping_cost = shipping_cost;
    }



    public void setPayment_method(String payment_method) {

        this.payment_method = payment_method;
    }



    public Integer getProduct_id() {

        return product_id;
    }



    public Integer getUser_id() {

        return user_id;
    }



    public String getName() {

        return name;
    }



    public String getEmail() {

        return email;
    }



    public String getPhone() {

        return phone;
    }



    public String getAttribute_options() {

        return attribute_options;
    }



    public String getProduct_quantity() {

        return product_quantity;
    }



    public String getCity_name() {

        return city_name;
    }



    public String getShipping_address() {

        return shipping_address;
    }



    public String getMessage() {

        return message;
    }



    public String getAmount() {

        return amount;
    }



    public String getPrice() {

        return price;
    }



    public String getShipping_cost() {

        return shipping_cost;
    }



    public String getPayment_method() {

        return payment_method;
    }



    public Order(Integer product_id, Integer user_id, String name, String email, String phone, String attribute_options,
                 String product_quantity, String city_name, String shipping_address,
                 String message, String amount, String price, String shipping_cost, String payment_method) {

        this.product_id = product_id;
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.attribute_options = attribute_options;
        this.product_quantity = product_quantity;
        this.city_name = city_name;
        this.shipping_address = shipping_address;
        this.message = message;
        this.amount = amount;
        this.price = price;
        this.shipping_cost = shipping_cost;
        this.payment_method = payment_method;
    }




}
