package TaskManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public class Main {
//    public static final String USER_NAME = "root";
//    public static final String PASSWORD = "Root(root)1";
//    public static final String URL = "jdbc:mysql://localhost:3306/test";
//    public static Statement statement;
//    public static Connection connection;
//    static {
//        try {
//            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
//            statement = connection.createStatement();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Storages storages = new Storages();
        MainLoop mainLoop = new MainLoop();
        Creator creator = new Creator();
        //Class.forName("com.mysql.cj.jdbc.Driver");
//        DatabaseManagement dbM = new DatabaseManagement();
//        dbM.dropTables();
//        dbM.createUsersTable();
//        dbM.createProjectsTable();
//        dbM.createTasksTable();
        Database database = new Database();
//        database.createUsersTable();
//        database.createProjectsTable();
//        database.createTasksTable();
//        statement.executeUpdate("CREATE TABLE users(user_id int AUTO_INCREMENT PRIMARY KEY," +
//                " name VARCHAR(20) NOT NULL)");
        mainLoop.getMenu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String userInput = reader.readLine();
            if (!userInput.isEmpty()) {
                switch (userInput) {
                    case "1":
                        System.out.println("Введите имя пользователя:");
                        String userName = reader.readLine();
                        creator.createUser(userName, storages);
                        break;
                    case "2":
                        storages.getAllUsers();
                        System.out.println("Введите id пользователя для удаления");
                        String idForDelete = reader.readLine();
                        if (creator.deleteUser(idForDelete, storages)) {
                            System.out.println("Удаление прошло успешно");
                        } else {
                            System.out.println("Вы ввели не число или несуществующий id");
                        }
                        break;
                    case "3":
                        System.out.println("Введите название проекта");
                        String projectTitle = reader.readLine();
                        creator.createProject(projectTitle, storages);
                        break;
                    case "4":
                        storages.getAllProjects();
                        System.out.println("Введите id проекта для удаления");
                        String projectIdForDelete = reader.readLine();
                        if (creator.deleteProject(projectIdForDelete, storages)) {
                            System.out.println("Удаление прошло успешно");
                        } else {
                            System.out.println("Вы ввели не число или несуществующий id");
                        }
                        break;
                    case "5":
                        System.out.println("Введите название задачи");
                        String taskTopic = reader.readLine();
                        System.out.println("Введите тип задачи");
                        String taskType = reader.readLine();
                        System.out.println("Введите приоритет задачи");
                        String taskPriority = reader.readLine();
                        System.out.println("Введите описание задачи");
                        String taskDescription = reader.readLine();
                        System.out.println("Введите id исполнителя задачи");
                        storages.getAllUsers();
                        String executorId = reader.readLine();
                        System.out.println("Введите id проекта");
                        storages.getAllProjects();
                        String projectId = reader.readLine();
                        if (creator.createTask(projectId, taskTopic, taskType, taskPriority, executorId, taskDescription, storages)) {
                            System.out.println("Задача создана успешно");
                        } else {
                            System.out.println("Вы ввели не число или несуществующий id");
                            System.out.println("Задача не создана");
                        }
                        break;
                    case "6":
                        storages.getAllTasks();
                        String taskIdForDelete = reader.readLine();
                        if (creator.deleteTask(taskIdForDelete, storages)) {
                            System.out.println("Удаление прошло успешно");
                        } else {
                            System.out.println("Вы ввели не число или несуществующий id");
                        }
                        break;
                    case "7":
                        storages.getAllUsers();
                        System.out.println("Введите id пользователя");
                        String userId = reader.readLine();
                        try {
                            int intUserId = Integer.parseInt(userId);
                            storages.displayAllTasksForUserById(intUserId);
                        } catch (NumberFormatException e) {
                            System.out.println("Вы ввели не число или несуществующий id");
                        }
                        break;
                    case "8":// работает не корректно
                        storages.getAllProjects();
                        System.out.println("Введите id проекта");
                        String projectIdForInfo = reader.readLine();
                        try {
                            int intProjectIdForInfo = Integer.parseInt(projectIdForInfo);
                            storages.displayAllProjectUsersById(intProjectIdForInfo);
                        } catch (NumberFormatException e) {
                            System.out.println("Вы ввели не число или несуществующий id");
                        }
                        break;
                    case "9":
                        storages.getAllProjects();
                        System.out.println("Введите id проекта");
                        String projectIdForUsersList = reader.readLine();
                        try {
                            int intProjectIdForUsersList = Integer.parseInt(projectIdForUsersList);
                            storages.displayAllUsersInProjectById(intProjectIdForUsersList);
                        } catch (NumberFormatException e) {
                            System.out.println("Вы ввели не число или несуществующий id");
                        }
                        break;
                    case "10":
                        storages.getAllProjects();
                        break;
                    case "11":
                        storages.getAllUsers();
                        break;
                    case"save":
                        storages.saveToDatabase(database);
                        break;
                    case "load":
                        database.load(storages);
                }
            } else {
                System.out.println("Введите число");
            }

        }
    }
}
