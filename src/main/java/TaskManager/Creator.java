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
    public boolean createTask(int projectId, String topic, String type, String priority, int executorId, String description, Storages storages) {
            if ((storages.isExist(storages.getProjectById(projectId))) && (storages.isExist(storages.getUserById(executorId)))) {
                Project project = storages.getProjectById(projectId);
                User executor = storages.getUserById(executorId);
                new Task(project, topic, type, priority, executor, description, storages);
            } else {
                return false;
            }
        return true;
    }
    public boolean deleteTask(String id, Storages storages) {
        try {
            int integerId = Integer.parseInt(id);
            return storages.deleteTask(integerId);
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public boolean isDigit(String digit) {
        try{
            Integer.parseInt(digit);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
