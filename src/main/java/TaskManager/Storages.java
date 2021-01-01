package TaskManager;

import java.util.ArrayList;

public class Storages {
    private ArrayList<User> users;
    private ArrayList<Project> projects;
    private ArrayList<Task> tasks;

    public void addUser(User user) {
        users.add(user);
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
