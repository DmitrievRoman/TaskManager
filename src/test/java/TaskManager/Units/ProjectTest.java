package TaskManager.Units;

import TaskManager.Storage.Storages;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProjectTest {
    private Project project;
    private User user;
    private ArrayList<User> users;
    private ArrayList<Task> tasks;
    Storages storages;

    @Before
    public void setUp(){
        storages = new Storages();
        project = new Project(0,"ProjectTitle", storages);
        user = new User(0,"UserName",storages);
        users = new ArrayList<>();
        tasks = new ArrayList<>();
    }
    @After
    public void tearDown() {
        users.clear();
        tasks.clear();
    }
    @Test
    public void getTitle() {
        assertEquals("ProjectTitle", project.getTitle());
    }

    @Test
    public void addUserIntoUsersList() {
        assertEquals(0, users.size());
        users.add(new User(0, "UserName", storages));
        assertEquals(1,users.size());
    }

    @Test
    public void addTaskIntoTasksList() {
        assertEquals(0,tasks.size());
        tasks.add(new Task(0,"TaskTitle","Type","Priority","Description",0,0,storages));
        assertEquals(1,tasks.size());
    }

    @Test
    public void deleteUserFromUsersList() {
        User user = new User(0,"UserName",storages);
        users.add(user);
        assertEquals(1,users.size());
        project.delete(user);
        assertEquals(0,users.size());
    }

    @Test
    public void deleteTaskFromTasksList() {
        tasks.add(new Task(0,"TaskTitle","Type","Priority","Description",0,0,storages));
        assertEquals(1,tasks.size());
        project.delete(tasks.get(0));
        assertEquals(0,tasks.size());
    }

    @Test
    public void isExist() {
    }

    @Test
    public void getProjectTasksList() {
    }

    @Test
    public void getProjectUsersList() {
    }

    @Test
    public void getAllTasksId() {
    }

    @Test
    public void getId() {

    }
}