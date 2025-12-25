package testing;

import database.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class testSqlStatement {
    public static void main(String[] args) {

        // create a connection to DB
        Connection connection = JdbcUtil.getConnection();

        // sql statement
        // not recommended as this is hard to maintain and leads to sql injection
        String sql = "INSERT INTO persons(last_name, first_name, gender, dob, income) "
                + "VALUES ('Hoang Nguyen', 'Quynh Anh', 'F', '2005-10-02', 50000.00)";

        try {
            // create an instance and execute
            Statement st = connection.createStatement();
            int rowAffected = st.executeUpdate(sql);

            // print out the number of affected rows
            System.out.println("Number of rows affected: " + rowAffected);
            if (rowAffected > 0) {
                System.out.println("Insert successfully !");
            }
            else System.out.println("Failing to insert into DB");

            // close resource
            st.close();
            JdbcUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
