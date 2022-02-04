package services;

import models.Order;
import repositories.OrderRepo;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class OrderServices {

    private static OrderRepo orderRepo=new OrderRepo();


    //add new order
    public static int add(int customerID){
        return orderRepo.addOrder(customerID);
    }


    //finalize order
    public static boolean finalize(int orderID, boolean isPaid){
        Date date=new Date();
        long now= date.getTime();
        Timestamp timestamp=new Timestamp(now);

        return orderRepo.finalize(orderID,timestamp,isPaid);
    }


    //show info
    public static Order showInfo(int orderID){

        return orderRepo.showInfo(orderID);
    }



    //show orders of a customer
    public static Order showOrdersCustomer(int customerID){
        return orderRepo.orderOfCustomer(customerID);
    }

}
