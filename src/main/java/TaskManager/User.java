package TaskManager;

public class User {
    private static int count;
    private String name;
    private Integer id;

    public User (String name, Storages storages) {
        this.name = name;
        this.id = ++count;
        storages.addUser(id, this);
    }

    public String getName() {
        return name;
    }
}
