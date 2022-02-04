package services;

import exceptionHandlers.DuplicateUser;
import exceptionHandlers.UserNotFound;
import models.Customer;
import repositories.CustomerRepo;

public class CustomerServices implements  BaseServices<Customer>{

    public  static CustomerRepo customerRepo=new CustomerRepo();

    //add new customer
    public int add(Customer customer) {

        String username=customer.getUsername();
        try {
            if (CustomerServices.showInfo(username).getUsername().equals(username)) {
                throw new DuplicateUser("username already exists");
            }
        }catch (NullPointerException e){

            System.out.println("username is free!");
            System.out.println();
        }

        System.out.println("user created successfully");
        return customerRepo.add(customer);

    }
//
//
//
    //show customer info
    public static Customer showInfo(String username){
        return customerRepo.showInfo(username);
    }


    //customer logIn
    public static boolean logIn(String username,String password) {

        boolean logInCheck = false;
        Customer customer = new Customer();

        customer.setUsername(CustomerServices.showInfo(username).getUsername());
        customer.setPassword(CustomerServices.showInfo(username).getPassword());

        if (customer.getUsername() == null) {
            throw new UserNotFound("user not found");
        } else {
            if (customer.getUsername().equals(username)) {
                if (customer.getPassword().equals(password)) {
                    logInCheck = true;
                }
            } else logInCheck = false;

        }
        return logInCheck;
    }


    //recharge balance
    public static boolean recharge(int amount,int customerID){
        return customerRepo.rechargeBalance(amount,customerID);
    }


    //deduct balance
    public static boolean deduct(int amount,int customerID){
        return customerRepo.deductBalance(amount,customerID);
    }
}
