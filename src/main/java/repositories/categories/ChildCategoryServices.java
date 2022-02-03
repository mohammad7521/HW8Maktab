package repositories.categories;

import models.Category;

import java.util.List;

public class ChildCategoryServices {

    private static ChildCategoryRepo childCategoryRepo=new ChildCategoryRepo();

    //add new category
    public static int add(Category category){
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
}



