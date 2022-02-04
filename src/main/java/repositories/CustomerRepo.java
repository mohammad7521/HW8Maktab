package repositories;

import connection.ConnectionProvider;
import models.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepo implements BaseRepository <Customer> {

    public CustomerRepo() {
        ConnectionProvider.setConnection();
    }

    //add
    public int add(Customer customer) {
        int insertedID=0;
        try {
            String insert = "insert into customer(username,password,address,phonenumber,nationalcode) values (?,?,?,?,?)" +
                    "returning id";
            PreparedStatement preparedStatement = ConnectionProvider.setConnection().prepareStatement(insert);
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getPhoneNumber());
            preparedStatement.setString(5, customer.getNationalCode());

            insertedID = preparedStatement.executeUpdate();

            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return insertedID;
    }




    //show info of a customer
    public Customer showInfo(int customerID){
        String showInfo="select * from customer where id=?";

        Customer customer=new Customer();
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
            preparedStatement.setInt(1,customerID);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){

                int id=resultSet.getInt(1);
                String username=resultSet.getString(2);
                String password=resultSet.getString(3);
                String address=resultSet.getString(4);
                String phoneNumber=resultSet.getString(5);
                String nationalCode=resultSet.getString(6);
                int balance=resultSet.getInt(7);

                customer=new Customer(username,password,address,phoneNumber,nationalCode,balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }



    //show info of a customer
    public Customer showInfo(String username){
        String showInfo="select * from customer where username=(?)";

        Customer customer=new Customer();
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
            preparedStatement.setString(1,username);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){

                String userName=resultSet.getString(1);
                String password=resultSet.getString(2);
                int id=resultSet.getInt(3);
                String address=resultSet.getString(4);
                String phoneNumber=resultSet.getString(5);
                String nationalCode=resultSet.getString(6);
                int balance=resultSet.getInt(7);

                customer=new Customer(userName,password,address,phoneNumber,nationalCode,balance);
                customer.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }



    //recharge balance
    public boolean rechargeBalance(int amount,int customerID){
        String rechargeBalance="update customer set balance=balance+(?) where id=?";

        int rechargeCheck=0;
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(rechargeBalance);
            preparedStatement.setInt(1,amount);
            preparedStatement.setInt(2,customerID);

            rechargeCheck=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rechargeCheck>0;
    }


    //deduct from balance
    public boolean deductBalance(int amount,int customerID){
        String deductBalance="update customer set balance=balance-(?) where id=?";

        int deductCheck=0;
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(deductBalance);
            preparedStatement.setInt(1,amount);
            preparedStatement.setInt(2,customerID);

            deductCheck=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deductCheck>0;
    }

}
