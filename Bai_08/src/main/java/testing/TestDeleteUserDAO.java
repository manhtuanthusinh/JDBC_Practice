package testing;

import DAO.UserDAO;
import model.User;

public class TestDeleteUserDAO {
    public static void main(String[] args) {
        System.out.println("============ TEST DELETE ============");
        User newUser = new User();
        newUser.setId(10000);
        UserDAO.getInstance().delete(newUser);
    }
}
