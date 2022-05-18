package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {

        userService.createUsersTable();
        userService.saveUser("Artem", "Dmitriev", (byte) 20);
        userService.saveUser("Gleb", "Borodach", (byte) 24);
        userService.saveUser("Anatoliy", "Jiglov", (byte) 36);
        userService.saveUser("Zoha", "Zohov", (byte) 58);
        userService.getAllUsers();
        userService.removeUserById(3);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
