package TaskManager.Units;

import TaskManager.Storage.Storages;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User user;
    private Project project;
    private Storages storages;
    private ArrayList<Task> tasks;
    @Before
    public void setUp() throws Exception {
        storages = new Storages();
        user = new User(0,"Name", storages);
        project = new Project(0,"ProjectTitle", storages);
        tasks = new ArrayList<>();
        tasks.add(new Task(0,"TaskTitle","type","priority","description",project.getId(),user.getId(),storages));
    }

    @After
    public void tearDown() throws Exception {
        tasks.clear();
    }

    @Test
    public void getName() {
        assertEquals("Name", user.getName() );
    }

    @Test
    public void displayUserTasksList() {
        assertEquals("TaskTitle", tasks.get(0).getTitle());
    }

    @Test
    public void addTask() throws SQLException {
        tasks.add(new Task(0,"TaskTitle","type","priority","description",project.getId(),user.getId(),storages));
        assertEquals(2,tasks.size());
    }

    @Test
    public void deleteTask() {
        tasks.remove(tasks.get(0));
        assertEquals(0,tasks.size());
    }

    @Test
    public void clearTaskList() {
        tasks.clear();
        assertEquals(0,tasks.size());
    }

    @Test
    public void getId() {
        assertEquals(0,user.getId());
    }
}