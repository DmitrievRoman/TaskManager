package TaskManager;

import java.util.ArrayList;

public class Project {
    private static int count;
    private String title;
    private Integer id;
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public Project(String title, Storages storages) {
        this.title = title;
        this.id = ++count;
        storages.addProject(id,this);
    }

    public String getTitle() {
        return title;
    }

    public void addUser(User user){
        users.add(user);
    }
    public void deleteUser(User user) {
        users.remove(user);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }
    public boolean isExist(User user) {
        return users.contains(user);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
    public void getProjectTasksList() {
        for (Task task : tasks) {
            System.out.println(task.getTitle());
        }
    }
    public void getProjectUsersList() {
        for (User user: users) {
            System.out.println(user.getName());
        }
    }
    public void getAllTasksId(){

    }
}
