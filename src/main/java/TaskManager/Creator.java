package TaskManager;

public class Creator {
    public void createUser(String name, Storages storages) {
        if(!name.equals("")) {
            new User(name, storages);
        }
    }
    public void createProject(String title, Storages storages) {
        if(!title.equals("")) {
            new Project(title, storages);
        }
    }
    public void createTask(Project project, String topic, String type, String priority, User executor, String description, Storages storages) {
        new Task(project, topic, type, priority, executor, description, storages);
    }
}
