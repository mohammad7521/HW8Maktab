package repositories;

import connection.ConnectionProvider;
import models.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepo  {

    public CustomerRepo() {
        ConnectionProvider.setConnection();
    }

    //add
    public boolean add(Customer customer) {
        int insertCheck = 0;
        try {
            String insert = "insert into customer(username,password,address,phonenumber,nationalcode) values (?,?,?,?,?)";
            PreparedStatement preparedStatement = ConnectionProvider.setConnection().prepareStatement(insert);
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getPhoneNumber());
            preparedStatement.setString(5, customer.getNationalCode());

            insertCheck = preparedStatement.executeUpdate();

            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return insertCheck > 0;
    }




    //show info of a customer
    public Customer showInfo(int customerID){
        String showInfo="select * from customer where id=?";

        Customer customer=null;
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

}
