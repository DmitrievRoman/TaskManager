package TaskManager.Units;

import TaskManager.Storage.Storages;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest1 {

    private User user;
    private Project project;
    private Storages storages;
    private ArrayList<Task> tasks;

    @Before
    public void setUp() throws SQLException {
        storages = new Storages();
        user = new User("Name", storages);
        project = new Project("ProjectTitle", storages);
        tasks = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            tasks.add(new Task(project,"TaskTitle" + i,"type","priority",user,"description",storages));
        }
    }

    @Test
    void getName() {
        assertEquals("Name", user.getName() );
    }

    @Test
    void displayUserTasksList() {
        for (int i = 0; i < 10; i++){
            assertEquals("TaskTitle" + i, tasks.get(i).getTitle());
        }
    }

    @Test
    void addTask() throws SQLException {
        tasks.add(new Task(project,"TaskTitle","type","priority",user,"description",storages));
        assertEquals(11,tasks.size());
    }

    @Test
    void deleteTask() {
        tasks.remove(tasks.get(0));
        assertEquals(9,tasks.size());
    }

    @Test
    void clearTaskList() {
        tasks.clear();
        assertEquals(0,tasks.size());
    }

    @Test
    void getId() {
        assertEquals(1,user.getId());
    }
}