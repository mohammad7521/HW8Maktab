package services;

import repositories.KartItemRepo;

public class KartItemServices  {

    private static KartItemRepo kartItemRepo=new KartItemRepo();

    //add new item to kart
    public static boolean add(int productID,int orderID,int quantity){
        return kartItemRepo.add(productID,orderID,quantity);
    }


    //remove an item from kart
    public static boolean remove(int productID,int orderID){
        return kartItemRepo.remove(productID,orderID);
    }
}
