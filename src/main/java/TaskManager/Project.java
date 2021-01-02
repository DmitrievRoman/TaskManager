package TaskManager;

public class Project {
    private static int count;
    private String title;
    private Integer id;

    public Project(String title, Storages storages) {
        this.title = title;
        this.id = ++count;
        storages.addProject(id,this);
    }

    public String getTitle() {
        return title;
    }
}
