package TaskManager.Main;

import TaskManager.Storage.Storages;
import TaskManager.Units.Project;
import TaskManager.Units.Task;
import TaskManager.Units.User;

import java.sql.SQLException;

public class Creator {
    public void createUser(String name, Storages storages) throws SQLException {
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
    public void createProject(String title, Storages storages) throws SQLException {
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
            int intProjectId = Integer.parseInt(projectId);
            int intExecutorId = Integer.parseInt(executorId);
            if ((storages.isExist(storages.getProjectById(intProjectId))) && (storages.isExist(storages.getUserById(intExecutorId)))) {
                Project project = storages.getProjectById(intProjectId);
                User executor = storages.getUserById(intExecutorId);
                new Task(project, topic, type, priority, executor, description, storages);
            } else {
                return false;
            }
        } catch (NumberFormatException | SQLException e) {
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
