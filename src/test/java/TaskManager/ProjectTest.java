package TaskManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProjectTest {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private ArrayList<User> users = new ArrayList<User>();
    private User user;
    private User user1;
    private Task task;
    private Task task1;
    private Project project;
    private Project project1;
    @Before
    public void setUp() {
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
        users.clear();
    }
    @Test
    public void testGetAllTasksId() {
        tasks.add(task);
        tasks.add(task1);
        ArrayList<Integer> actual = new ArrayList<Integer>();
        for(Task task : tasks) {
            actual.add(task.getId());
        }
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(task.getId());
        expected.add(task1.getId());

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testGetProjectTasksList() {
        tasks.add(task);
        tasks.add(task1);
        ArrayList<String> actual = new ArrayList<String>();
        for(Task task : tasks) {
            actual.add(task.getTitle());
        }
        ArrayList<String> expected = new ArrayList<String>();
        expected.add(task.getTitle());
        expected.add(task1.getTitle());
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testGetProjectUsersList() {
        users.add(user);
        users.add(user1);
        ArrayList<String> actual = new ArrayList<String>();
        for(User user : users) {
            actual.add(user.getName());
        }
        ArrayList<String> expected = new ArrayList<String>();
        expected.add(user.getName());
        expected.add(user1.getName());
    }

}