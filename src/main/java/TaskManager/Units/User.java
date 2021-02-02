package TaskManager.Units;

import TaskManager.Storage.Database;
import TaskManager.Storage.Storages;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends Unit {
    private static int count;
    private final String name;
    private final Integer id;
    private final ArrayList<Task> tasks = new ArrayList<>();

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
    public void displayUserTasksList() {
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
