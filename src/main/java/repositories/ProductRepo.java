package repositories;

import connection.ConnectionProvider;
import models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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



    //change product quantity (recharge)
    public boolean recharge(int productID,int quantity){
        String modify="update product set quantity=quantity+(?) where id=?";

        int updateCheck=0;
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(modify);
            preparedStatement.setInt(1,quantity);
            preparedStatement.setInt(2,productID);

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



    //show all products
    public  List<Product> showAll(){
        String showAll="select * from product";

        List<Product> productList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showAll);
            ResultSet resultSet=preparedStatement.executeQuery();
            preparedStatement.close();

            while (resultSet.next()){
                Product product=new Product();

                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getInt(3));
                product.setQuantity(resultSet.getInt(4));

                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
