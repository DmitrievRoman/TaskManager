package TaskManager;

public class Creator {
    public void createUser(String name, Storages storages) {
        if(!name.equals("")) {
            new User(name, storages);
        }
    }
    public boolean deleteUser(String id, Storages storages) {
        try{
            int integerId = Integer.parseInt(id);
            if(storages.deleteUser(integerId)){
                return true;
            }else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public void createProject(String title, Storages storages) {
        if(!title.equals("")) {
            new Project(title, storages);
        }
    }
    public boolean deleteProject(String id, Storages storages) {
        try{
            int integerId = Integer.parseInt(id);
            if(storages.deleteProject(integerId)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public void createTask(Project project, String topic, String type, String priority, User executor, String description, Storages storages) {
        new Task(project, topic, type, priority, executor, description, storages);
    }
}
