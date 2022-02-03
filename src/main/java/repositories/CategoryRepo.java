package repositories;

import connection.ConnectionProvider;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoryRepo {

    public CategoryRepo() {
        ConnectionProvider.setConnection();
    }



//    //add
//    public int add (Category category){
//
//        int returnID=0;
//        String insert="INSERT INTO parentCategory (name) VALUES(?) returning id";
//
//        PreparedStatement preparedStatement= null;
//        try {
//            preparedStatement = ConnectionProvider.setConnection().prepareStatement(insert);
//
//            preparedStatement.setString(1,category.getName());
//
//            ResultSet resultSet=preparedStatement.executeQuery();
//
//            while (resultSet.next()){
//                returnID=resultSet.getInt(1);
//            }
//            preparedStatement.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return returnID;
//    }

//
//    //add without parent category
//    //add
//    public int addWithoutParent (Category category){
//
//        int returnID=0;
//        String insert="INSERT INTO category (name) VALUES(?) returning id";
//
//        PreparedStatement preparedStatement= null;
//        try {
//            preparedStatement = ConnectionProvider.setConnection().prepareStatement(insert);
//
//            preparedStatement.setString(1,category.getName());
//
//            ResultSet resultSet=preparedStatement.executeQuery();
//
//            while (resultSet.next()){
//                returnID=resultSet.getInt(1);
//            }
//            preparedStatement.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return returnID;
//    }



//    //show info
//    public Category showInfo(int categoryID){
//        String showInfo="select * from category where id=?";
//
//        Category category=null;
//        try {
//            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
//            preparedStatement.setInt(1,categoryID);
//            ResultSet resultSet=preparedStatement.executeQuery();
//
//            while (resultSet.next()){
//                int id=resultSet.getInt(1);
//                String name=resultSet.getString(2);
//                int productID=resultSet.getInt(3);
//                String parentCategory=resultSet.getString(4);
//
//                category=new Category(name,productID);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return category;
//    }



//    //show info based on category name
//    public Category showInfo(String name){
//        String showInfo="select * from category where name=?";
//
//        Category category=null;
//        try {
//            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
//            preparedStatement.setString(1,name);
//            ResultSet resultSet=preparedStatement.executeQuery();
//
//            while (resultSet.next()){
//                int id=resultSet.getInt(1);
//                String categoryName=resultSet.getString(2);
//                int productID=resultSet.getInt(3);
//                String parentCategory=resultSet.getString(4);
//
//                category=new Category(categoryName,productID);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return category;
//    }



//    //show all categories
//    public List <Category> showAll(){
//        String showInfo="select id,name,parentcategory from category group by (id,name,parentcategory)";
//
//        List<Category> categoryList=new ArrayList<>();
//        try {
//            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
//            ResultSet resultSet=preparedStatement.executeQuery();
//
//            while (resultSet.next()){
//
//                int id=resultSet.getInt(1);
//                String categoryName=resultSet.getString(2);
//                String parentCategory=resultSet.getString(3);
//                Category category=new Category();
//                category.setId(id);
//                category.setName(categoryName);
//                category.setParentCategory(CategoryServices.showInfo(parentCategory));
//                categoryList.add(category);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return categoryList;
//    }



//    //delete a category
//    public boolean delete(int id) {
//
//        String delete = "DELETE FROM category WHERE id=?";
//
//        int removeCheck = 0;
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = ConnectionProvider.setConnection().prepareStatement(delete);
//            preparedStatement.setInt(1, id);
//
//            removeCheck = preparedStatement.executeUpdate();
//
//            preparedStatement.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//        return removeCheck > 0;
//
//    }



    //set product to a category
    public  boolean addProduct(int productID,int categoryID){
        String addProduct="UPDATE category set productid=(?) where id=(?)";

        int addProductCheck=0;
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(addProduct);
            preparedStatement.setInt(1,productID);
            preparedStatement.setInt(2,categoryID);

            addProductCheck=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addProductCheck>0;
    }



//    //add product to a category
//    public  boolean addProduct(int productID,int categoryID){
//        String addProduct="INSERT INTO category ()";
//
//        int addProductCheck=0;
//        try {
//            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(addProduct);
//            preparedStatement.setInt(1,productID);
//            preparedStatement.setInt(2,categoryID);
//
//            addProductCheck=preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return addProductCheck>0;
//    }
}
