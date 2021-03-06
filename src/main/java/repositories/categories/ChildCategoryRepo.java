package repositories.categories;

import connection.ConnectionProvider;
import models.Category;
import models.Product;
import repositories.BaseRepository;
import services.categories.ParentCategoryServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChildCategoryRepo implements BaseRepository<Category> {

    public ChildCategoryRepo() {
        ConnectionProvider.setConnection();
    }



    //add
    public int add (Category category){

        int returnID=0;
        String insert="INSERT INTO childcategory (name,parentcategoryid) VALUES(?,?) returning id";

        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(insert);

            preparedStatement.setString(1,category.getName());
            preparedStatement.setInt(2,category.getParentCategoryID());

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
        String showInfo="select * from childCategory where id=?";

        Category category=new Category();
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
            preparedStatement.setInt(1,categoryID);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                int id=resultSet.getInt(1);
                String name=resultSet.getString(2);
                int parentCategoryID=resultSet.getInt(4);

                category.setId(id);
                category.setName(name);
                category.setParentCategory(ParentCategoryServices.showInfo(parentCategoryID));
                category.setParentCategoryId(parentCategoryID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }



    //show info based on category name
    public Category showInfo(String name){
        String showInfo="select * from childcategory where name=?";

        Category category=new Category();
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
            preparedStatement.setString(1,name);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                int id=resultSet.getInt(1);
                String categoryName=resultSet.getString(2);
                int productID=resultSet.getInt(3);
                int parentCategoryID=resultSet.getInt(4);

                category.setId(id);
                category.setName(categoryName);
                category.setParentCategoryId(parentCategoryID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }


    //show all categories
    public List <Category> showAll(){
        String showInfo="select id,name,parentcategory from childCategory group by (id,name,parentcategoryid)";

        List<Category> categoryList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){

                int id=resultSet.getInt(1);
                String categoryName=resultSet.getString(2);
                int parentCategoryID=resultSet.getInt(3);
                Category category=new Category();
                category.setId(id);
                category.setName(categoryName);
                category.setParentCategoryId(parentCategoryID);
                categoryList.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }




    //show child categories of a parent
    public List<Category> showCategoriesOfParent(int parentCategoryID){
        String showInfo="select * from childCategory where parentcategoryid=?";

        List<Category> categoryList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
            preparedStatement.setInt(1,parentCategoryID);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                int id=resultSet.getInt(1);
                String name=resultSet.getString(2);

                Category category=new Category();
                category.setId(id);
                category.setName(name);

                categoryList.add(category);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }



    //add product to a category
    public boolean addProduct(Category category,int productID){

        int addProductCheck=0;
        String addProduct="insert into childcategory (name,productid,parentcategoryid) values(?,?,?)";

        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(addProduct);
            preparedStatement.setString(1,category.getName());
            preparedStatement.setInt(2,productID);
            preparedStatement.setInt(3,category.getParentCategoryID());
            addProductCheck=preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addProductCheck>0;
    }




    //show products of a category
    public List<Product> showProducts(int categoryID){

        List<Product> productList=new ArrayList<>();
        String showProducts="\n" +
                "select productID,p.name,price,quantity from childCategory inner join product p on childCategory.productID = p.id\n" +
                "where childCategory.id=?";
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showProducts);
            preparedStatement.setInt(1,categoryID);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Product product=new Product();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setQuantity(resultSet.getInt(4));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
