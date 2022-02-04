package console;


import exceptionHandlers.DuplicateUser;
import exceptionHandlers.NationalCodeLength;
import exceptionHandlers.NoOrders;
import exceptionHandlers.UserNotFound;
import models.*;
import services.CustomerServices;
import services.KartItemServices;
import services.OrderServices;
import services.ProductServices;
import services.categories.ChildCategoryServices;
import services.categories.ParentCategoryServices;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CustomerConsole {

    public static CustomerServices customerServices=new CustomerServices();
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
                        Customer customer=new Customer(username,password,address,phoneNumber,nationalCode);
                        try {
                            customerServices.add(customer);
                        }catch (DuplicateUser e){
                            System.out.println("username already exists! ");
                        }
                        catch (NationalCodeLength e){
                            System.out.println("national code is not long enough!");
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
            System.out.println("3-show categories: ");
            System.out.println("4-show shopping kart: ");

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

                        try {
                            if (ProductServices.showInfo(productID).getQuantity() >= quantity) {
                                if (KartItemServices.add(productID, customer.getId(), quantity) && ProductServices.deduct(productID, quantity)) {
                                    System.out.println("item has been added to your shopping kart! ");
                                    break;
                                }

                            } else {
                                System.out.println("out of stock! ");
                            }
                        }catch (NullPointerException e){
                            System.out.println("item does not exist! ");
                        }

                        break;

                    case 3:
                        System.out.println("select category by ID:");
                        List<Category> parentCategories=ParentCategoryServices.showAll();

                        for (Category p:parentCategories){
                            System.out.println(p.toString());
                        }
                        int selectedParent=scanner.nextInt();

                        if (ParentCategoryServices.showInfo(selectedParent).getId()!=selectedParent){
                            System.out.println("selected category does not exist!");
                            break;
                        }
                        if (ChildCategoryServices.showCategoriesOfParent(selectedParent).size()<1){
                            System.out.println("please select another category!");
                            break;
                        }
                            List<Category> childCategories = ChildCategoryServices.showCategoriesOfParent(selectedParent);

                            for (Category c : childCategories) {
                                System.out.println(c.toString());
                            }

                            System.out.println("select category by id: ");
                            int selectedChild = scanner.nextInt();

                            if (ChildCategoryServices.showInfo(selectedChild).getId()!=selectedChild){
                                System.out.println("selected category id does not exist!");
                                break;
                            }
                            productList = ChildCategoryServices.showProducts(selectedChild);

                            for (Product p : productList) {
                                System.out.println(p.toString());
                            }


                            System.out.println("enter the product id:");
                            productID = scanner.nextInt();
                            System.out.println("enter quantity: ");
                            quantity = scanner.nextInt();

                            try {
                                if (ProductServices.showInfo(productID).getQuantity() >= quantity) {
                                    if (KartItemServices.add(productID, customer.getId(), quantity) && ProductServices.deduct(productID, quantity)) {
                                        System.out.println("item has been added to your shopping kart! ");
                                        break;
                                    }

                                } else {
                                    System.out.println("out of stock! ");
                                    break;
                                }
                            }catch (NullPointerException e){
                                System.out.println("product does not exist!");
                                System.out.println();
                                break;
                            }
                    case 4:
                        orderID=OrderServices.lastCustomerOrder(customer.getId()).getId();
                    List<KartItem> kartItemList=KartItemServices.showKartItems(orderID);

                    for (KartItem k:kartItemList){
                        System.out.println(k.toString());
                        System.out.println();
                        System.out.println("total price:");
                        int totalOrderPrice=OrderServices.totalOrderPrice(OrderServices.lastCustomerOrder(customer.getId()).getId());
                        System.out.println(totalOrderPrice);
                        if (totalOrderPrice>customer.getBalance()) {
                            System.out.println("not enough balance!");
                            System.out.println("please recharge!");
                            System.out.println();
                            break;
                        }
                        System.out.println("finalize (y for yes enter any other for no!)");
                        char finalize=scanner.next().charAt(0);
                        if (finalize=='y' || finalize=='Y'){
                            if(CustomerServices.deduct(totalOrderPrice,customer.getId())&&
                            OrderServices.finalize(orderID,true )){
                                System.out.println("congratulations!");
                                System.out.println("your order will be delivered soon!");
                            }
                        }
                    }

                    case 0:
                        break;
                }

            }catch (InputMismatchException e) {
                System.out.println("pleas enter a valid number");
        }
        }
    }
}
