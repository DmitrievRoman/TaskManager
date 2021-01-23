package TaskManager;

import java.util.ArrayList;

public class Project extends Unit{
    private static int count;
    private String title;
    private Integer id;
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public Project(String title, Storages storages) {
        this.title = title;
        this.id = ++count;
        storages.add(id,this);
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
    public void getProjectTasksList() {
        if (!tasks.isEmpty()) {
            for (Task task : tasks) {
                System.out.println(task.getTitle());
            }
        } else {
            System.out.println("В проекте нет ни одной задачи");
        }
    }
    public void getProjectUsersList() {
        if (!users.isEmpty()) {
            for (User user : users) {
                System.out.println(user.getName());
            }
        } else {
            System.out.println("В проекте нет пользователей");
        }
    }
    public ArrayList<Integer> getAllTasksId(){
        ArrayList<Integer> tasksId = new ArrayList<Integer>();
        for (Task task : tasks) {
            tasksId.add(task.getId());
        }
        return tasksId;
    }
    public Integer getId() {
        return id;
    }
}
