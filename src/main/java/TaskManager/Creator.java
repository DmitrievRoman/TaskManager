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
            return storages.deleteUser(integerId);
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
            return storages.deleteProject(integerId);
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public boolean createTask(String projectId, String topic, String type, String priority, String executorId, String description, Storages storages) {
        try {
            if ((storages.isExist(storages.getProjectById(Integer.parseInt(projectId)))) && (storages.isExist(storages.getUserById(Integer.parseInt(executorId))))) {
                Project project = storages.getProjectById(Integer.parseInt(projectId));
                User executor = storages.getUserById(Integer.parseInt(executorId));
                new Task(project, topic, type, priority, executor, description, storages);
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
