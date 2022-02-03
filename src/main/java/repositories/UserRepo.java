package repositories;

import connection.ConnectionProvider;

public class UserRepo {

    public UserRepo() {
        ConnectionProvider.setConnection();
    }


}
