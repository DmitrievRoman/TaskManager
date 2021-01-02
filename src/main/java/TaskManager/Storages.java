package TaskManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Storages {
    private Map<Integer, User> users = new HashMap<Integer, User>();
    private Map< Integer, Project> projects = new HashMap<Integer, Project>();
    private Map<Integer, Task> tasks = new HashMap<Integer, Task>();

    public void addUser(Integer id, User user) {
        users.put(id, user);
    }

    public void addProject(Integer id, Project project) {
        projects.put(id, project);
    }

    public void addTask(Integer id, Task task) {
        tasks.put(id, task);
    }
    public void getAllUsers() {
        for(Map.Entry<Integer, User> pair : users.entrySet()){
            System.out.println(pair.getKey() + ":" + pair.getValue().getName());
        }
    }

}
