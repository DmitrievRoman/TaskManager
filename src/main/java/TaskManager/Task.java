package TaskManager;

public class Task {
    private Project project;
    private String topic;
    private String type;
    private String priority;
    private User executor;
    private String description;

    public Task(Project project, String topic, String type, String priority, User executor, String description, Storages storages) {
        this.project = project;
        this.topic = topic;
        this.type = type;
        this.priority = priority;
        this.executor = executor;
        this.description = description;
        storages.addTask(this);
    }
}
