package database;

import com.mysql.cj.jdbc.Driver;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
    public static Connection getConnection() {
        Connection c = null;
        Dotenv dotenv = Dotenv.configure()
                    .directory("./Bai_03/")
                    .ignoreIfMissing()
                    .load();

        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        try {
            // register mysql to Driver Manager
            DriverManager.registerDriver(new Driver());
            c = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null)
                c.close();
        }
        catch (Exception e) {
                e.printStackTrace();
        }
    }

    public static void getInfo(Connection c) {
        try {
            if (c != null && !c.isClosed()) {
                DatabaseMetaData metaData = c.getMetaData();

                System.out.println("========== Connection Info ==========");
                System.out.println("DB Product Name: " + metaData.getDatabaseProductName());
                System.out.println("DB Product Version: " + metaData.getDatabaseProductVersion());
                System.out.println("-----------------------");
                System.out.println(c.getMetaData().toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
