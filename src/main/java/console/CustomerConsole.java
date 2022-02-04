package console;


import exceptionHandlers.DuplicateUser;
import exceptionHandlers.NoOrders;
import exceptionHandlers.UserNotFound;
import models.Customer;
import models.KartItem;
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
                        }catch (UserNotFound e){
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
            try {
                Customer customer = CustomerServices.showInfo(username);
                int orderID;
                Order order=new Order();
                orderID=order.getId();
            System.out.println("1-recharge balance: ");
            System.out.println("2-show all products: ");
            System.out.println("3-show shopping kart: ");

            System.out.println("0-Log out");

            Scanner scanner = new Scanner(System.in);


                int userEntry = scanner.nextInt();
                switch (userEntry) {

                    case 1:
                        System.out.println("enter amount");
                        int amount=scanner.nextInt();
                        if(CustomerServices.recharge(amount,customer.getId())){
                            System.out.println("recharge successful!");
                            System.out.println();
                            break;
                        }
                        else System.out.println("something went wrong try again!");
                        System.out.println();
                        break;
                    case 2:
                        List<Product> productList=ProductServices.showAll();

                        for(Product product:productList){
                            System.out.println(product.toString());
                        }

                        System.out.println("enter the product id:");
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

                    case 3:
                        orderID=OrderServices.lastCustomerOrder(customer.getId()).getId();
                    List<KartItem> kartItemList=KartItemServices.showKartItems(orderID);

                    for (KartItem k:kartItemList){
                        System.out.println(k.toString());
                        System.out.println();
                        System.out.println("total price:");
                        int totalOrderPrice=OrderServices.totalOrderPrice(OrderServices.lastCustomerOrder(customer.getId()).getId());
                        System.out.println(totalOrderPrice);
                        System.out.println("finalize (Y/N)");
                        char finalize=scanner.next().charAt(0);
                        if (finalize=='y' || finalize=='Y'){
                            if(CustomerServices.deduct(totalOrderPrice,customer.getId())&&
                            OrderServices.finalize(orderID,true )){
                                System.out.println("congratulations!");
                                System.out.println("your order will be delivered soon!");
                            }
                        }
                    }
                }
            }catch (InputMismatchException e) {
                System.out.println("pleas enter a valid number");
        }
        }
    }
}
