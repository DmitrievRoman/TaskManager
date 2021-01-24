package TaskManager;

import junit.framework.TestCase;
import org.junit.Before;

import java.sql.SQLException;

public class StoragesTest extends TestCase {
    private User user;
    private User user1;
    private Task task;
    private Task task1;
    private Project project;
    private Project project1;
    @Before
    public void setUp() throws SQLException {
        Storages storages = new Storages();
        user = new User("UserName", storages);
        user1 = new User("UserName1", storages);
        project = new Project("ProjectTitle", storages);
        project1 = new Project("ProjectTitle1", storages);
        task = new Task(project, "topic", "type", "priority", user, "description", storages);
        task1 = new Task(project1, "topic1", "type1", "priority1", user1, "description1", storages);
    }

    public void testDeleteUser() {
    }

    public void testDeleteProject() {
    }

    public void testDeleteTask() {
    }

    public void testGetAllUsers() {
    }

    public void testGetAllProjects() {
    }

    public void testGetAllTasks() {
    }
}