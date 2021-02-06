package TaskManager.Units;

import TaskManager.Storage.Database;
import TaskManager.Storage.Storages;

import java.sql.SQLException;
import java.util.ArrayList;

public class Project extends Unit {
    private static int count;
    private final String title;
    private final Integer id;
    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Task> tasks = new ArrayList<>();

    public Project(String title, Storages storages) throws SQLException {
        this.title = title;
        this.id = ++count;
        storages.add(id,this);
        Database.add(this); //Добавляем проект в базу данных
    }

    public Project(int id, String title, Storages storages) {
        this.title = title;
        this.id = id;
        count = id;// <-- необходимо, чтобы после загрузки данных из базы, значение count было актуальным
        storages.add(id,this);
    }

    public String getTitle() {
        return title;
    }
    public void add(User user){
        users.add(user);
    }
    public void add(Task task) {
        tasks.add(task);
    }
    public void delete(User user) {
        users.remove(user);
    }
    public void delete(Task task) {
        tasks.remove(task);
    }
    public boolean isExist(User user) {
        return users.contains(user);
    }
    public void displayTasksList() {
        if (!tasks.isEmpty()) {
            for (Task task : tasks) {
                System.out.println(task.getTitle());
            }
        } else {
            System.out.println("В проекте нет ни одной задачи");
        }
    }
    public void displayUsersList() {
        if (!users.isEmpty()) {
            for (User user : users) {
                System.out.println(user.getName());
            }
        } else {
            System.out.println("В проекте нет пользователей");
        }
    }
    public ArrayList<Integer> getAllTasksId(){
        ArrayList<Integer> tasksId = new ArrayList<>();
        for (Task task : tasks) {
            tasksId.add(task.getId());
        }
        return tasksId;
    }
    public Integer getId() {
        return id;
    }
    public ArrayList<User> getUsersList() {
        return users;
    }
    public ArrayList<Task> getTasksList() {
        return tasks;
    }
}
