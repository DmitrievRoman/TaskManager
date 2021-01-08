package TaskManager;

public class Task {
    private static int count;
    private Project project;
    private String topic;
    private String type;
    private String priority;
    private User executor;
    private String description;
    private Integer id;

    public Task(Project project, String topic, String type, String priority, User executor, String description, Storages storages) {
        this.project = project;
        this.topic = topic;
        this.type = type;
        this.priority = priority;
        this.executor = executor;
        this.description = description;
        this.id = ++count;
        storages.addTask(id, this);
        executor.addTask(this);
        project.addTask(this);
        project.addUser(executor);
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
    public Integer getId() {
        return getId();
    }

}
