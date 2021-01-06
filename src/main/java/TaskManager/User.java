package TaskManager;

import java.util.ArrayList;

public class User {
    private static int count;
    private String name;
    private Integer id;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public User (String name, Storages storages) {
        this.name = name;
        this.id = ++count;
        storages.addUser(id, this);
    }

    public String getName() {
        return name;
    }
    public void getUserTasksList() {
        for(Task task : tasks) {
            System.out.println(task.getTitle());
        }
    }
    public void addTask(Task task) {
        tasks.add(task);
    }

    public void clearTaskList() {
        tasks.clear();
    }
}
