package repositories;

import connection.ConnectionProvider;
import models.KartItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


    //show shopping kart of a customer
    public List<KartItem> showKartOfCustomer(int orderID){
        String showKart="select * from kartitems where orderID=?";

        List<KartItem> kartItemList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showKart);
            preparedStatement.setInt(1,orderID);

            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                KartItem kartItem=new KartItem();
                kartItem.setId(resultSet.getInt(1));
                kartItem.setProductID(resultSet.getInt(2));
                kartItem.setSelectedQuantity(resultSet.getInt(3));
                kartItemList.add(kartItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kartItemList;
    }
}
