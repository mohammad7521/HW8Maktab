package repositories;

import connection.ConnectionProvider;
import models.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KartItemRepo {


    //add new kart item
    public boolean add(int productID,int orderID,int quantity){
            int insertCheck = 0;
            try {
                String insert = "insert into kartitems(productid,selectedquantity,orderid) values (?,?,?)";
                PreparedStatement preparedStatement = ConnectionProvider.setConnection().prepareStatement(insert);
                preparedStatement.setInt(1, productID);
                preparedStatement.setInt(2,quantity);
                preparedStatement.setInt(3,orderID);

                insertCheck = preparedStatement.executeUpdate();

                preparedStatement.close();


            } catch (SQLException e) {
                e.printStackTrace();

            }
            return insertCheck > 0;
        }


        //remove a kart item

    public boolean remove(int productID,int orderID){

        int removeCheck = 0;
        try {
            String insert = "delete from kartitems where productID=(?)and ( orderid=(?))";
            PreparedStatement preparedStatement = ConnectionProvider.setConnection().prepareStatement(insert);
            preparedStatement.setInt(1, productID);
            preparedStatement.setInt(2,orderID);

            removeCheck = preparedStatement.executeUpdate();

            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return removeCheck > 0;
    }
}
