package repositories;

import connection.ConnectionProvider;

public class AdminRepo {

    public AdminRepo() {
        ConnectionProvider.setConnection();
    }

}
