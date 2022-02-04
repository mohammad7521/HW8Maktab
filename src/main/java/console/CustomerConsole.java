package console;


import exceptionHandlers.DuplicateUser;
import models.Customer;
import models.Order;
import models.Product;
import services.CustomerServices;
import services.KartItemServices;
import services.OrderServices;
import services.ProductServices;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CustomerConsole {

    public static void customerLogInMenu() {

        boolean flag = true;
        while (flag) {
            System.out.println("1-Register: ");
            System.out.println("2-Sign in: ");
            System.out.println("0-exit");

            Scanner scanner = new Scanner(System.in);

            try {
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        System.out.println("enter a username: ");
                        String username=scanner.next();
                        System.out.println("enter a password: ");
                        String password=scanner.next();
                        System.out.println("enter your address: ");
                        String address=scanner.next();
                        System.out.println("enter your phone number: ");
                        String phoneNumber=scanner.next();
                        System.out.println("enter your national code:");
                        String nationalCode=scanner.next();
                        try {
                            CustomerServices.addNew(username,password,address,phoneNumber,nationalCode);
                        }catch (DuplicateUser e){
                            System.out.println("username already exists! ");
                        }
                        break;
                    case 2:
                        System.out.println("please enter your username: ");
                        username=scanner.next();
                        System.out.println("please enter your password: ");
                        password=scanner.next();
                        try {
                            if (CustomerServices.logIn(username, password)) {
                                System.out.println("log in successful! ");
                                customerMainMenu(username);
                                break;
                            } else System.out.println("password is wrong! ");
                            break;
                        }catch (NullPointerException e){
                            System.out.println("username does not exist! ");
                        }
                    case 0:
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a valid number ! ");
            }
        }
    }


    // customer main menu
    public static void customerMainMenu(String username) {

        while (true) {
            System.out.println("1-show all products: ");
            System.out.println("2-show shopping kart: ");
            System.out.println("0-Log out");

            Scanner scanner = new Scanner(System.in);
            try {
                Customer customer = CustomerServices.showInfo(username);
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        List<Product> productList=ProductServices.showAll();

                        for(Product product:productList){
                            System.out.println(product.toString());
                        }
                        int orderID;
                        System.out.println("enter the product id:");
                        if(OrderServices.showOrdersCustomer(customer.getId())==null) {
                            orderID=OrderServices.add(customer.getId());
                        }else {
                            orderID=OrderServices.showOrdersCustomer(customer.getId()).getId();
                        }

                        int productID=scanner.nextInt();
                        System.out.println("enter quantity: ");
                        int quantity=scanner.nextInt();

                        if (ProductServices.showInfo(productID).getQuantity()>=quantity){
                            if (KartItemServices.add(productID,orderID,quantity) && ProductServices.deduct(productID,quantity)){
                                System.out.println("item has been added to your shopping kart! ");
                                break;
                            }

                        }else {
                            System.out.println("out of stock! ");
                        }

                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("pleas enter a valid number");
            }
        }
    }
}
