package TaskManager.Units;

import TaskManager.Storage.Database;
import TaskManager.Storage.Storages;

import java.sql.SQLException;

public class Task extends Unit {
    private static int count;
    private Project project;
    private String topic;
    private String type;
    private String priority;
    private User executor;
    private String description;
    private Integer id;

    public Task(Project project, String topic, String type, String priority, User executor, String description, Storages storages) throws SQLException {
        this.project = project;
        this.topic = topic;
        this.type = type;
        this.priority = priority;
        this.executor = executor;
        this.description = description;
        this.id = ++count;
        storages.add(id, this);
        executor.addTask(this);
        project.add(this);
        project.add(executor);
        Database.add(this);//Добавляем задачу в базу данных
    }
    public Task(int id, String topic, String type, String priority, String description, int project_id, int executor_id, Storages storages) {
        this.id = id;
        this.topic = topic;
        this.type = type;
        this.priority = priority;
        this.description = description;
        this.project = storages.getProjectById(project_id);
        this.executor = storages.getUserById(executor_id);
        count = id; // <-- необходимо, чтобы после загрузки данных из базы, значение count было актуальным
        storages.add(id, this);
        storages.getUserById(executor_id).addTask(this);
        storages.getProjectById(project_id).add(this);
        storages.getProjectById(project_id).add(storages.getUserById(executor_id));
    }
    public String getTitle() {
        return topic;
    }
    public User getExecutor() {
        return executor;
    }
    public Project getProject() {
        return project;
    }
    public String getType() {
        return type;
    }
    public String getPriority() {
        return priority;
    }
    public String getDescription() {
        return description;
    }
    public Integer getId(){
        return id;
    }

}
