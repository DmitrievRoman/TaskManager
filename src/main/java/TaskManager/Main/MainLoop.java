package TaskManager.Main;

import TaskManager.Menu.Menu;
import TaskManager.Storage.Storages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class MainLoop {
    public static void mainLoop(Storages storages) throws SQLException, IOException{
        Creator creator = new Creator();
        Menu.getMenu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String userInput = reader.readLine();
            if (!userInput.isEmpty()) {
                switch (userInput) {
                    case "1": //Создать пользователя
                        System.out.println("Введите имя пользователя:");
                        String userName = reader.readLine();
                        if(creator.createUser(userName, storages)){
                            System.out.println("Пользователь создан успешно");
                        } else {
                            System.out.println("Пользователь не создан");
                        }
                        break;
                    case "2"://Удалить пользователя
                        storages.getAllUsers();
                        System.out.println("Введите id пользователя для удаления");
                        String idForDelete = reader.readLine();
                        if (creator.deleteUser(idForDelete, storages)) {
                            System.out.println("Удаление прошло успешно");
                        } else {
                            System.out.println("Вы ввели не число или несуществующий id");
                        }
                        break;
                    case "3"://Создать проект
                        System.out.println("Введите название проекта");
                        String projectTitle = reader.readLine();
                        creator.createProject(projectTitle, storages);
                        break;
                    case "4"://Удалить проект
                        storages.getAllProjects();
                        System.out.println("Введите id проекта для удаления");
                        String projectIdForDelete = reader.readLine();
                        if (creator.deleteProject(projectIdForDelete, storages)) {
                            System.out.println("Удаление прошло успешно");
                        } else {
                            System.out.println("Вы ввели не число или несуществующий id");
                        }
                        break;
                    case "5"://Создать задачу
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
                    case "6"://Удалить задачу
                        storages.getAllTasks();
                        String taskIdForDelete = reader.readLine();
                        if (creator.deleteTask(taskIdForDelete, storages)) {
                            System.out.println("Удаление прошло успешно");
                        } else {
                            System.out.println("Вы ввели не число или несуществующий id");
                        }
                        break;
                    case "7":// Вывести список задач назначенных на пользователя
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
                    case "8"://Вывести список всех задач в проекте
                        storages.getAllProjects();
                        System.out.println("Введите id проекта");
                        String projectIdForInfo = reader.readLine();
                        try {
                            int intProjectIdForInfo = Integer.parseInt(projectIdForInfo);
                            storages.getProjectById(intProjectIdForInfo).displayTasksList();
                        } catch (NumberFormatException e) {
                            System.out.println("Вы ввели не число или несуществующий id");
                        }
                        break;
                    case "9"://Вывести список всех пользователей в проекте
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
                    case "10"://Вывести список всех проектов
                        storages.getAllProjects();
                        break;
                    case "11"://Вывести список всех пользователей
                        storages.getAllUsers();
                        break;
//                    case "load":
//                        database.load(storages);
//                        break;
                    case "menu"://Вывести список команд
                        Menu.getMenu();
                        break;
                }
            } else {
                System.out.println("Введите число");
            }

        }
    }
}
