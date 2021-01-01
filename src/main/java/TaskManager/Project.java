package TaskManager;

public class Project {
    private String title;

    public Project(String title, Storages storages) {
        this.title = title;
        storages.addProject(this);
    }

    public String getTitle() {
        return title;
    }
}
