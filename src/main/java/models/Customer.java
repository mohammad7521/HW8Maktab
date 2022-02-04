package models;

import java.util.List;

public class Customer extends User {

    private String address;
    private String phoneNumber;
    private String nationalCode;
    private List<Order> orderList;
    private double balance;


    public Customer(String username,String password,String address,
                    String phoneNumber, String nationalCode, double balance) {

        setUsername(username);
        setPassword(password);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.nationalCode = nationalCode;
        this.balance = balance;
    }

    public Customer() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void addToOrders(Order order){
        this.orderList.add(order);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Order lastOrder(){
        return orderList.get(-1);
    }
}
