package testing;

import DAO.UserDAO;
import model.User;

import java.util.ArrayList;

public class TestQueryUserDAO {
    public static void main(String[] args) {
        System.out.println("============ TEST SELECT ALL ============");
        ArrayList<User> listUser = UserDAO.getInstance().SelectAll();
        for (User user : listUser) {
            System.out.println(user.toString());
        }

        System.out.println("========== TEST SELECT ID ============");
        User findUser = new User();
        findUser.setId(3);
        UserDAO.getInstance().SelectByID(findUser);
        System.out.println(findUser);

    }
}
