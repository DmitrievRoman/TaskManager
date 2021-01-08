package TaskManager;

import java.util.HashMap;
import java.util.Map;

public class Storages {
    private Map<Integer, User> users = new HashMap<Integer, User>();
    private Map<Integer, Project> projects = new HashMap<Integer, Project>();
    private Map<Integer, Task> tasks = new HashMap<Integer, Task>();

    public void addUser(Integer id, User user) {
        users.put(id, user);
    }
    public boolean deleteUser(int id) {
        if(users.containsKey(id)) {
            User user = users.remove(id);
            user.clearTaskList();
            for (Map.Entry<Integer, Project> pair : projects.entrySet()) {
                if(pair.getValue().isExist(user)){
                    pair.getValue().deleteUser(user);
                }
            }
            for(Map.Entry<Integer, Task> pair : tasks.entrySet()) {
                if(pair.getValue().getExecutor().equals(user)) {
                    tasks.remove(pair.getKey());
                }
            }

            return true;
        } else {
            return false;
        }
    }


    public void addProject(Integer id, Project project) {
        projects.put(id, project);
    }
    public boolean deleteProject(int id) {
        if(projects.containsKey(id)) {
            Project project = projects.remove(id);
            //project.
            return true;
        } else {
            return false;
        }
    }

    public void addTask(Integer id, Task task) {
        tasks.put(id, task);
    }
    public boolean deleteTask(int id) {
        if(tasks.containsKey(id)){
            Task task = tasks.remove(id);
            task.getExecutor().deleteTask(task);
            task.getProject().deleteTask(task);
            return true;
        } else {
            return false;
        }
    }
    public void getAllUsers() {
        for(Map.Entry<Integer, User> pair : users.entrySet()){
            System.out.println(pair.getKey() + ":" + pair.getValue().getName());
        }
    }
    public void getAllProjects() {
        for(Map.Entry<Integer, Project> pair : projects.entrySet()){
            System.out.println(pair.getKey() + ":" + pair.getValue().getTitle());
        }
    }
    public void getAllTasks() {
        for(Map.Entry<Integer, Task> pair : tasks.entrySet()) {
            System.out.println(pair.getKey() + ":" + pair.getValue().getTitle());
        }
    }
    public User getUserById(int id) {
         return users.get(id);
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

}
