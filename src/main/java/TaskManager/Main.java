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
                    if(creator.isDigit(executorId) && creator.isDigit(projectId)) {
                        if (creator.createTask(Integer.parseInt(projectId), taskTopic, taskType, taskPriority, Integer.parseInt(executorId), taskDescription, storages)) {
                            System.out.println("Задача создана успешно");
                        } else {
                            System.out.println("Вы ввели несуществующий id");
                            System.out.println("Задача не создана");
                        }
                    } else {
                        System.out.println("Вы ввели не число или несуществующий id");
                        System.out.println("Задача не создана");
                    }
                    break;
                case 6:
                    storages.getAllTasks();
                    String taskIdForDelete = reader.readLine();
                    if(creator.deleteTask(taskIdForDelete, storages)) {
                        System.out.println("Удаление прошло успешно");
                    } else {
                        System.out.println("Вы ввели не число или несуществующий id");
                    }
                    break;
                case 7:
                    storages.getAllUsers();
                    System.out.println("Введите id пользователя");
                    String userId = reader.readLine();
                    try{
                        int intUserId = Integer.parseInt(userId);
                        storages.getUserById(intUserId).getUserTasksList();
                    } catch (NumberFormatException e) {
                        System.out.println("Вы ввели не число или несуществующий id");
                    }
                    break;
                case 8:
                    storages.getAllProjects();
                    System.out.println("Введите id проекта");
                    String projectIdForInfo = reader.readLine();
                    try{
                        int intProjectIdForInfo = Integer.parseInt(projectIdForInfo);
                        storages.getProjectById(intProjectIdForInfo).getProjectTasksList();
                    } catch (NumberFormatException e) {
                        System.out.println("Вы ввели не число или несуществующий id");
                    }
                    break;
                case 9:
                    storages.getAllProjects();
                    System.out.println("Введите id проекта");
                    String projectIdForUsersList = reader.readLine();
                    try {
                        int intProjectIdForUsersList = Integer.parseInt(projectIdForUsersList);
                        storages.getProjectById(intProjectIdForUsersList).getProjectUsersList();
                    } catch (NumberFormatException e) {
                        System.out.println("Вы ввели не число или несуществующий id");
                    }
                    break;
                case 10:
                    storages.getAllProjects();
                    break;
                case 11:
                    storages.getAllUsers();
                    break;
            }

        }
    }
}
