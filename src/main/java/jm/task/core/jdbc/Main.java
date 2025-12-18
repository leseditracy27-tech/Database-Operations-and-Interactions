package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

                UserService userService = new UserServiceImpl();
                // 1. Create users table
                userService.createUsersTable();
                System.out.println("Users table created");

                // 2. Add 4 users
                userService.saveUser("John", "Doe", (byte) 25);
                System.out.println("User with name - John added to the database");

                userService.saveUser("Jane", "Smith", (byte) 30);
                System.out.println("User with name - Jane added to the database");

                userService.saveUser("Bob", "Johnson", (byte) 35);
                System.out.println("User with name - Bob added to the database");

                userService.saveUser("Alice", "Williams", (byte) 28);
                System.out.println("User with name - Alice added to the database");

                // 3. Get all users and print them
                List<User> users = userService.getAllUsers();
                System.out.println("\nAll users in the database:");
                for (User user : users) {
                    System.out.println(user);
                }

                // 4. Clean users table
                userService.cleanUsersTable();
                System.out.println("\nUsers table cleaned");

                // 5. Drop users table
                userService.dropUsersTable();
                System.out.println("Users table dropped");
            }
        }

