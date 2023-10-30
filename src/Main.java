import Logic.*;
import Interfaces.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import Persistance.*;

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
