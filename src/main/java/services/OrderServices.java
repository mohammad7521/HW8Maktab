package services;

import exceptionHandlers.NoOrders;
import models.Order;
import repositories.OrderRepo;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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



    //show last order of a customer
    public static Order lastCustomerOrder(int customerID){
        Order order= orderRepo.orderOfCustomer(customerID);
        if (order==null){
            throw new NoOrders();
        }
        return order;
    }


    //show orders of a customer
    public static List<Order> customerOrders(int customerID){

            List<Order> orderList;
            orderList=orderRepo.ordersOfCustomer(customerID);
            if (orderList.size()==0){
                throw new NoOrders();
            }
            return orderList;
    }



    //show total price of an order
    public static int totalOrderPrice(int orderID){
        return orderRepo.totalOrderPrice(orderID);
    }
}
