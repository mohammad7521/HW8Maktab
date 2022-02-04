package services;

import models.KartItem;
import models.Order;
import repositories.KartItemRepo;

import java.util.List;

public class KartItemServices  {

    private static KartItemRepo kartItemRepo=new KartItemRepo();

    //add new item to kart
    public static boolean add(int productID,int customerID,int quantity){

        int orderID;
        if (OrderServices.lastCustomerOrder(customerID).getId()<1 ||
            OrderServices.lastCustomerOrder(customerID).isPaid()==true){
            orderID=OrderServices.add(customerID);
        }
        else{
            orderID=OrderServices.lastCustomerOrder(customerID).getId();
        }
        return kartItemRepo.add(productID,orderID,quantity);
    }


    //remove an item from kart
    public static boolean remove(int productID,int orderID){
        return kartItemRepo.remove(productID,orderID);
    }



    //show kartItems of a customer
    public static List<KartItem> showKartItems(int orderID){
        return kartItemRepo.showKartOfCustomer(orderID);
    }

}
