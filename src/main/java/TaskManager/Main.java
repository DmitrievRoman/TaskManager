package TaskManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Storages storages = new Storages();
        MainLoop mainLoop = new MainLoop();
        DatabaseManagement dbM = new DatabaseManagement();
        dbM.dropTables();
        dbM.createUsersTable();
        dbM.createProjectsTable();
        mainLoop.getMenu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int userInput = Integer.parseInt(reader.readLine());
            switch (userInput) {
                case 1 :
                    System.out.println("Введите имя пользователя:");
                    String userName = reader.readLine();
                    dbM.createUser(userName);
                    dbM.getAllUsers();
                    break;
                case 2 :
                    dbM.getAllUsers();
                    System.out.println("Введите id пользователя для удаления");
                    int userID = Integer.parseInt(reader.readLine());
                    dbM.deleteUser(userID);
                    dbM.getAllUsers();
                    break;
                case 3:
                    System.out.println("Введите название проекта");
                    String projectTitle = reader.readLine();
                    dbM.createProject(projectTitle);
                    break;
                case 4:
                    dbM.getAllProjects();
                    System.out.println("Введите id проекта для удаления");
                    int projectID = Integer.parseInt(reader.readLine());
                    dbM.deleteProject(projectID);
                    dbM.getAllProjects();
                    break;
                case 5:
                    System.out.println("Введите название задачи");
                    String taskTitle = reader.readLine();
                    break;
            }

        }
    }
}
