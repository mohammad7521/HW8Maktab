package models;

public class Customer extends User {

    private String address;
    private String phoneNumber;
    private String nationalCode;
    private Order[] orders;
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

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
