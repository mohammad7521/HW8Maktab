package services;

import models.Product;
import repositories.ProductRepo;

import java.util.List;

public class ProductServices implements BaseServices<Product> {


    private static ProductRepo productRepo=new ProductRepo();


    //delete a product
    public boolean delete(int id) {
        return productRepo.delete(id);
    }


    //add new product
    public int add(Product product){
        return productRepo.add(product);
    }



//    //modify a product
//    public static boolean modify(int productID,String name,int quantity,int price)  {
//
//        Product product=showInfo(productID);
//
//        if(product==null){
//            return false;
//        }
//
//        return productRepo.modify(productID,name,price,quantity);
//    }



    //show info of a product
    public static Product showInfo(int productID){

        return productRepo.showInfo(productID);
    }



    //recharge a product
    public static boolean recharge (int productID,int quantity){

        return productRepo.recharge(productID,quantity);
    }


    //deduct from a product
    public static boolean deduct (int productID,int quantity){

        return productRepo.deduct(productID,quantity);
    }


    //show all products
    public static List<Product> showAll(){
        return productRepo.showAll();
    }


    //add
}
