package TaskManager;


import TaskManager.Storage.Storages;
import TaskManager.Units.Project;
import TaskManager.Units.Task;
import TaskManager.Units.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserTest {
    private ArrayList<Task> tasks;
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
        task = new Task(project,"topic", "type", "priority", user, "description",storages);
        task1 = new Task(project1, "topic1", "type1", "priority1", user1, "description1", storages);
    }
    @After
    public void tearDown() {
        tasks.clear();
    }
    @Test
    public void testGetUserTasksList() {
        tasks = new ArrayList<Task>();
        tasks.add(task);
        tasks.add(task1);
        ArrayList<String> actual = new ArrayList<String>();
        for(Task task : tasks) {
            actual.add(task.getTitle());
        }
        ArrayList<String> expected = new ArrayList<String>();
        expected.add(task.getTitle());
        expected.add(task1.getTitle());
        Assert.assertEquals(actual,expected);
    }

}