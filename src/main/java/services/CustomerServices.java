package services;

import exceptionHandlers.DuplicateUser;
import models.Customer;
import repositories.CustomerRepo;

public class CustomerServices {

    public static CustomerRepo customerRepo=new CustomerRepo();

    //add new customer
    public static void addNew(String username,String password,String address,
                              String phoneNumber,String nationalCode) {

        Customer customer=new Customer();

        try {
            if (CustomerServices.showInfo(username).getUsername().equals(username)) {
                throw new DuplicateUser("username already exists");
            }
        }catch (NullPointerException e){

            System.out.println("username is free!");
            System.out.println();
        }
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNumber);
        customer.setNationalCode(nationalCode);

        customerRepo.add(customer);
        System.out.println("user created successfully");
    }
//
//
//
    //show customer info
    public static Customer showInfo(String username){

        return customerRepo.showInfo(username);
    }


    //customer logIn
    public static boolean logIn(String username,String password){

        boolean logInCheck=false;
        Customer customer=new Customer();

        customer.setUsername(CustomerServices.showInfo(username).getUsername());
        customer.setPassword(CustomerServices.showInfo(username).getPassword());

        if (customer.getUsername().equals(username)) {
            if (customer.getPassword().equals(password)) {
                logInCheck = true;
            }
        } else logInCheck = false;

        return logInCheck;
    }
}
