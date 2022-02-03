package services.categories;

import models.Category;
import repositories.categories.ParentCategoryRepo;

import java.util.List;

public class ParentCategoryServices {

    private static ParentCategoryRepo parentCategoryRepo=new ParentCategoryRepo();



    //add new category
    public static int add(Category category){
        return parentCategoryRepo.add(category);
    }


    //show info based on category id
    public static Category showInfo(int categoryID){

        return parentCategoryRepo.showInfo(categoryID);
    }


    //show info based on category name
    public static Category showInfo(String name){

        return parentCategoryRepo.showInfo(name);
    }


    //show all categories
    public static List<Category> showAll(){
        return parentCategoryRepo.showAll();
    }
}
