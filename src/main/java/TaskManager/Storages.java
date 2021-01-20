package TaskManager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Storages {
    private Map<Integer, User> users = new HashMap<Integer, User>();
    private Map<Integer, Project> projects = new HashMap<Integer, Project>();
    private Map<Integer, Task> tasks = new HashMap<Integer, Task>();

    public void add(Integer id, User user) {
        users.put(id, user);
    }
    public void add(Integer id, Project project) {
        projects.put(id, project);
    }
    public void add(Integer id, Task task) {
        tasks.put(id, task);
    }
    public boolean deleteUser(int id) {
        if(users.containsKey(id)) {
            User user = users.remove(id);
            user.clearTaskList();
            for (Map.Entry<Integer, Project> pair : projects.entrySet()) {
                if(pair.getValue().isExist(user)){
                    pair.getValue().delete(user);
                }
            }
            for(Map.Entry<Integer, Task> pair : tasks.entrySet()) {
                if(pair.getValue().getExecutor().equals(user)) {
                    deleteTask(pair.getKey());
                    tasks.remove(pair.getKey());
                }
            }

            return true;
        } else {
            return false;
        }
    }
    public boolean deleteProject(int id) {
        if(projects.containsKey(id)) {
            Project project = projects.remove(id);

            for (int i = 0; i < project.getAllTasksId().size(); i++) {
                deleteTask(project.getAllTasksId().get(i));
            }
            return true;
        } else {
            return false;
        }
    }
    public boolean deleteTask(int id) {
        if(tasks.containsKey(id)){
            Task task = tasks.remove(id);
            task.getExecutor().deleteTask(task);
            task.getProject().delete(task);
            return true;
        } else {
            return false;
        }
    }
    public void getAllUsers() {
        if(!users.isEmpty()) {
            for (Map.Entry<Integer, User> pair : users.entrySet()) {
                System.out.println(pair.getKey() + ":" + pair.getValue().getName());
            }
        } else {
            System.out.println("Список польхователей пуст");
        }
    }
    public void getAllProjects() {
        if(!projects.isEmpty()) {
            for (Map.Entry<Integer, Project> pair : projects.entrySet()) {
                System.out.println(pair.getKey() + ":" + pair.getValue().getTitle());
            }
        } else {
            System.out.println("Список проектов пуст");
        }
    }
    public void getAllTasks() {
        if(!tasks.isEmpty()) {
            for (Map.Entry<Integer, Task> pair : tasks.entrySet()) {
                System.out.println(pair.getKey() + ":" + pair.getValue().getTitle());
            }
        } else {
            System.out.println("Список задач пуст");
        }
    }
    public User getUserById(int id) {
         return users.get(id);
    }
    public void displayAllTasksForUserById(int id) {
        if(users.containsKey(id)){
            User user = users.get(id);
            user.getUserTasksList();
        } else {
            System.out.println("Пользователя с таким id не существует");
        }
    }
    public void displayAllProjectUsersById(int id) {
        if (projects.containsKey(id)) {
            Project project = projects.get(id);
            project.getProjectUsersList();
        } else {
            System.out.println("Проекта с таким id не существует");
        }
    }
    public void displayAllUsersInProjectById(int id) {
        if(projects.containsKey(id)) {
            Project project = projects.get(id);
            project.getProjectUsersList();
        } else {
            System.out.println("Прроекта с таким id не существует");
        }
    }
    public Project getProjectById(int id) {
        return projects.get(id);
    }
    public boolean isExist(User user) {
        return users.containsValue(user);
    }
    public boolean isExist(Project project) {
        return projects.containsValue(project);
    }
    public void saveToDatabase(Database database) throws SQLException {
        database.save(users,projects,tasks);
    }
}
