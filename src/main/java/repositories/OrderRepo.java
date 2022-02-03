package repositories;

import connection.ConnectionProvider;

public class OrderRepo  {

    public OrderRepo() {
        ConnectionProvider.setConnection();
    }

}
