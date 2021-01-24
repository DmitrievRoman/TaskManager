package TaskManager;

import java.sql.SQLException;
import java.util.ArrayList;

public class User extends Unit {
    private static int count;
    private String name;
    private Integer id;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public User (String name, Storages storages) throws SQLException {
        this.name = name;
        this.id = ++count;
        storages.add(id, this);
        Database.add(this); //добавляем пользователя в базу данных
    }
    public User (int id, String name, Storages storages) {
        this.id = id;
        this.name = name;
        storages.add(id,this);
        count = id;// <-- необходимо, чтобы после загрузки данных из базы, значение count было актуальным
    }

    public String getName() {
        return name;
    }
    public void getUserTasksList() {
        if (!tasks.isEmpty()) {
            for (Task task : tasks) {
                System.out.println(task.getTitle());
            }
        } else {
            System.out.println("Список задач пользователя пуст");
        }
    }
    public void addTask(Task task) {
        tasks.add(task);
    }
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public void clearTaskList() {
        tasks.clear();
    }
    public Integer getId() {
        return id;
    }
}
