package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private static Connection connection;

    private ConnectionProvider(){}


    public static Connection setConnection() throws SQLException, ClassNotFoundException {


        if (connection==null){
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","6642");
        }
        return connection;
    }

}
