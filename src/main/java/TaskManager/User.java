package TaskManager;

public class User {
    private String name;

    public User (String name, Storages storages) {
        this.name = name;
        storages.addUser(this);
    }

    public String getName() {
        return name;
    }
}
