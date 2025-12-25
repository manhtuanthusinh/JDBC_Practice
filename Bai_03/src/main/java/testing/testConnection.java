package testing;

import database.JdbcUtil;

import java.sql.Connection;

public class testConnection {
    public static void main(String[] args) {
        Connection connection = JdbcUtil.getConnection();
        System.out.println(connection);

        JdbcUtil.getInfo(connection);

        JdbcUtil.closeConnection(connection);
        System.out.println(connection);
    }
}
