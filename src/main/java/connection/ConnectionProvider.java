package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private static Connection connection;

    private ConnectionProvider(){}


    public static Connection setConnection()  {


        if (connection==null){
            try {
                Class.forName("org.postgresql.Driver");
                connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","6642");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return connection;
    }

}
