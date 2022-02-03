package categories;

import connection.ConnectionProvider;
import models.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParentCategoryRepo {

    public ParentCategoryRepo() {
        ConnectionProvider.setConnection();
    }


    //add
    public int add (Category category){

        int returnID=0;
        String insert="INSERT INTO parentCategory (name) VALUES(?) returning id";

        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(insert);

            preparedStatement.setString(1,category.getName());

            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                returnID=resultSet.getInt(1);
            }
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnID;
    }


    //show info
    public Category showInfo(int categoryID){
        String showInfo="select * from parentcategory where id=?";

        Category category=new Category();
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
            preparedStatement.setInt(1,categoryID);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                int id=resultSet.getInt(1);
                String name=resultSet.getString(2);

                category.setId(id);
                category.setName(name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }


    //show info based on category name
    public Category showInfo(String name){
        String showInfo="select * from parentcategory where name=?";

        Category category=null;
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
            preparedStatement.setString(1,name);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                int id=resultSet.getInt(1);
                String categoryName=resultSet.getString(2);

                category=new Category();
                category.setId(id);
                category.setName(categoryName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }


    //show all categories
    public List <Category> showAll(){
        String showInfo="select id,name from parentcategory group by (id,name)";

        List<Category> categoryList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){

                int id=resultSet.getInt(1);
                String categoryName=resultSet.getString(2);
                Category category=new Category();
                category.setId(id);
                category.setName(categoryName);
                categoryList.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}
