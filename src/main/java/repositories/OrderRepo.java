package repositories;

import connection.ConnectionProvider;
import models.Category;
import models.Order;
import services.categories.ParentCategoryServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OrderRepo  {

    public OrderRepo() {
        ConnectionProvider.setConnection();
    }


    //add new order
    public int addOrder(int customerID){

        String addOrder="insert into customerorder(customerid,ispaid) values(?,false) returning id";

        int orderID=0;
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(addOrder);
            preparedStatement.setInt(1,customerID);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                orderID=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderID;
    }



    //finalize order
    public boolean finalize(int orderID, Timestamp orderTime,boolean isPaid){
        String finalize="update customerorder set orderDate=?,ispaid=? where orderID=?";

        int finalizeCheck=0;
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(finalize);
            preparedStatement.setTimestamp(1,orderTime);
            preparedStatement.setBoolean(2,isPaid);
            preparedStatement.setInt(3,orderID);

            finalizeCheck=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return finalizeCheck>0;
    }



    //show info
    public Order showInfo(int orderID){
        String showInfo="select * from customerOrder where id=?";

        Order order=new Order();
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
            preparedStatement.setInt(1,orderID);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                int id=resultSet.getInt(1);
                Timestamp orderDate=resultSet.getTimestamp(2);
                boolean isPaid=resultSet.getBoolean(3);

                order.setId(id);
                order.setDateOrder(orderDate);
                order.setPaid(isPaid);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }



    //show order of a customer
    public Order orderOfCustomer(int customerID){
        String orderOfCustomer="select * from  customerorder where customerid=(?) and (ispaid=false);";

        Order order=null;
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(orderOfCustomer);
            preparedStatement.setInt(1,customerID);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){

                int id=resultSet.getInt(1);
                Timestamp orderTime=resultSet.getTimestamp(2);
                boolean isPaid=resultSet.getBoolean(3);

                order.setId(id);
                order.setDateOrder(orderTime);
                order.setPaid(isPaid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
}
