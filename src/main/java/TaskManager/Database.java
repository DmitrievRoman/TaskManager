package TaskManager;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class Database {
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "Root(root)1";
    public static final String URL = "jdbc:mysql://localhost:3306/test";
    public static Statement statement;
    public static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() throws SQLException, ClassNotFoundException {
        statement.executeUpdate("CREATE TABLE users(user_id int PRIMARY KEY," +
                " name VARCHAR(20) NOT NULL)");
    }
    public void createProjectsTable() throws SQLException, ClassNotFoundException {
        statement.executeUpdate("CREATE TABLE projects(project_id int PRIMARY KEY," +
                " title VARCHAR(30) NOT NULL)");
    }
    public void createTasksTable() throws SQLException, ClassNotFoundException {
        statement.executeUpdate("CREATE TABLE tasks (" +
                "task_id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                "title VARCHAR(30) NOT NULL," +
                "project_id INTEGER REFERENCES projects(project_id)," +
                "type VARCHAR(30)," +
                "priority VARCHAR(30)," +
                "executor_id INTEGER REFERENCES users(user_id)," +
                "description VARCHAR(300))");
    }

    public void save(Map<Integer,User> usersMap, Map<Integer,Project> projectsMap, Map<Integer,Task> tasksMap) throws SQLException {
            for(Map.Entry<Integer, User> pair : usersMap.entrySet()){
                statement.executeUpdate("insert into users (user_id,name) values ('" +pair.getKey() +"','" + pair.getValue().getName() + "')");
            }
            for(Map.Entry<Integer, Project> pair : projectsMap.entrySet()) {
                statement.executeUpdate("insert into projects (project_id,title) values ('" +pair.getKey() +"','" + pair.getValue().getTitle() + "')");
            }
            for(Map.Entry<Integer, Task> pair : tasksMap.entrySet()){
                statement.executeUpdate("insert into tasks(task_id, title, project_id, type, priority, executor_id, description)" +
                        " values('"+ pair.getKey() + "'," +
                        "'" + pair.getValue().getTitle() + "'," +
                        "'" + pair.getValue().getProject().getId() + "'," +
                        "'" + pair.getValue().getType() + "'," +
                        "'" + pair.getValue().getPriority() + "'," +
                        "'" + pair.getValue().getExecutor().getId() + "'," +
                        "'" + pair.getValue().getDescription() + "' )");
            }
    }

    public void load(Storages storages) {

    }
}
