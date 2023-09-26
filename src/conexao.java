import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class conexao {

    public Connection conectaDB() {
        Connection conBD = null;

        try {

            String url = "jdbc:mysql://localhost:3306/dbams?user=root&password=L453rm0p4d4@";
            conBD = DriverManager.getConnection(url);

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO!", 0);

        }

        return conBD;
    }
    
}
