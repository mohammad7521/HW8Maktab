package services.categories;

import models.Category;
import models.Product;
import repositories.BaseRepository;
import repositories.categories.ChildCategoryRepo;
import services.BaseServices;

import java.util.List;

public class ChildCategoryServices implements BaseServices<Category> {

    private static ChildCategoryRepo childCategoryRepo=new ChildCategoryRepo();

    //add new category
    public  int add(Category category){
        return childCategoryRepo.add(category);
    }


    //show info based on category id
    public static Category showInfo(int categoryID){

        return childCategoryRepo.showInfo(categoryID);
    }


    //show info based on category name
    public static Category showInfo(String name){

        return childCategoryRepo.showInfo(name);
    }


    public static List<Category> showAll(){
        return childCategoryRepo.showAll();
    }

    //add product to a category
    public static boolean addProduct(int productID,int categoryID){
        Category category=ChildCategoryServices.showInfo(categoryID);
        return childCategoryRepo.addProduct(category,productID);

    }


    //show categories of a parent category
    public static List<Category> showCategoriesOfParent(int parentCategoryID){
        return childCategoryRepo.showCategoriesOfParent(parentCategoryID);
    }


    //show products of a category
    public static List<Product> showProducts(int categoryID){
        return childCategoryRepo.showProducts(categoryID);
    }
}



