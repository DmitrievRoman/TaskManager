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

    public void addTask(Task task) {
        tasks.add(task);
    }
}
