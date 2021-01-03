package TaskManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Storages storages = new Storages();
        MainLoop mainLoop = new MainLoop();
        Creator creator = new Creator();
//        DatabaseManagement dbM = new DatabaseManagement();
//        dbM.dropTables();
//        dbM.createUsersTable();
//        dbM.createProjectsTable();
        mainLoop.getMenu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int userInput = Integer.parseInt(reader.readLine());
            switch (userInput) {
                case 1 :
                    System.out.println("Введите имя пользователя:");
                    String userName = reader.readLine();
                    creator.createUser(userName, storages);
                    break;
                case 2 :

                    System.out.println("Введите id пользователя для удаления");
                    String idForDelete = reader.readLine();
                    if(creator.deleteUser(idForDelete, storages)){
                        System.out.println("Удаление прошло успешно");
                    } else {
                        System.out.println("Вы ввели не число или несуществующий id");
                    }
                    break;
                case 3:
                    System.out.println("Введите название проекта");
                    String projectTitle = reader.readLine();
                    creator.createProject(projectTitle, storages);
                    break;
                case 4:
                    storages.getAllProjects();
                    System.out.println("Введите id проекта для удаления");
                    String projectIdForDelete = reader.readLine();
                    if(creator.deleteProject(projectIdForDelete, storages)) {
                        System.out.println("Удаление прошло успешно");
                    } else {
                        System.out.println("Вы ввели не число или несуществующий id");
                    }
                    break;
                case 5:
                    System.out.println("Введите название задачи");
                    String taskTitle = reader.readLine();
                    break;
            }

        }
    }
}
