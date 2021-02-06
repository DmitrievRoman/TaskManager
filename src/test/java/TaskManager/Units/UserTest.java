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
    private Storages storages;
    private Project project;
    private Task task;
    @Before
    public void setUp() throws Exception {
        storages = new Storages();
        user = new User(0,"Name", storages);
        project = new Project(0,"ProjectTitle",storages);
        task = new Task(0,"TaskTitle","type","priority","description",project.getId(),user.getId(),storages);
    }

    @After
    public void tearDown() throws Exception {
        user.clearTaskList();
    }

    @Test
    public void getName() {
        assertEquals("Name", user.getName() );
    }

    @Test
    public void addTask() throws SQLException {
        user.addTask(task);
        //expected = 2 потому, что в setUp() задача была создана вручную, и конструктор класса Task
        //сам добавил задачу пользователю на основе id, а в данном методе была добавлена еще одна задача
        assertEquals(2,user.getTasks().size());
    }

    @Test
    public void deleteTask() {
        user.deleteTask(task);
        assertEquals(0,user.getTasks().size());
    }

    @Test
    public void clearTaskList() {
        user.clearTaskList();
        assertEquals(0,user.getTasks().size());
    }

    @Test
    public void getId() {
        assertEquals(0,user.getId());
    }
}