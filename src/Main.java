import Interfaces.login;
import Persistance.ConnectBD;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            ConnectBD connectBD = ConnectBD.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        new login();
    }
}
