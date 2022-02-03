package repositories;

import connection.ConnectionProvider;
import models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepo {

    public ProductRepo() {
        ConnectionProvider.setConnection();
    }



    //delete a product
    public boolean delete(int id) {

        String delete = "DELETE FROM product WHERE id=?";

        int removeCheck = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(delete);
            preparedStatement.setInt(1, id);

            removeCheck = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return removeCheck > 0;

    }



    //add new product
    public int add (Product product){

        int addedProduct=0;
        String insert="INSERT INTO product (name,price,quantity) VALUES(?,?,?) returning id";

        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(insert);

            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getQuantity());

            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                addedProduct=resultSet.getInt(1);
            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addedProduct;
    }



    //change product information
    public boolean modify(int productID,String name,int price,int quantity){
        String modify="update product set name=?,price=?,quantity=? where id=?";

        int updateCheck=0;
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(modify);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,price);
            preparedStatement.setInt(3,quantity);
            preparedStatement.setInt(4,productID);

            updateCheck=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateCheck>0;
    }




    //show info of a product
    public Product showInfo(int productID){
        String showInfo="select * from product where id=?";

        Product product=null;
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
            preparedStatement.setInt(1,productID);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                int id=resultSet.getInt(1);
                String name=resultSet.getString(2);
                int price=resultSet.getInt(3);
                int quantity=resultSet.getInt(4);

                product=new Product(name,price,quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
