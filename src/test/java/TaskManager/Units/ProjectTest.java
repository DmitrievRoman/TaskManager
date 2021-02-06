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
    private Storages storages;
    private Task task;

    @Before
    public void setUp(){
        storages = new Storages();
        project = new Project(0,"ProjectTitle", storages);
        user = new User(0,"UserName",storages);
        task = new Task(0,"TaskTitle","type","priority","description",0,0,storages);
    }
    @After
    public void tearDown() {
        project.getUsersList().clear();
        project.getTasksList().clear();
    }
    @Test
    public void getTitle() {
        assertEquals("ProjectTitle", project.getTitle());
    }

    @Test
    public void addUserIntoUsersList() {
        assertEquals(1, project.getUsersList().size());
        project.add(user);
        assertEquals(2,project.getUsersList().size());
    }

    @Test
    public void addTaskIntoTasksList() {
        assertEquals(1,project.getTasksList().size());
        project.add(task);
        assertEquals(2,project.getTasksList().size());
    }

    @Test
    public void isExist() {
        project.add(user);
        assertTrue(project.isExist(user));
    }
    @Test
    public void getAllTasksId() {

    }

    @Test
    public void getId() {
        int actual = project.getId();
        assertEquals(0,actual);
    }
}