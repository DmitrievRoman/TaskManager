package TaskManager;
import java.sql.*;
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

        //перед тем как сохранять надо дропнуть базу иначе будут дубликаты и программа упадет
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

    public void load(Storages storages) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        while(resultSet.next()) {
            int id = resultSet.getInt("user_id");
            String name = resultSet.getString("name");
            new User(id, name, storages);
        }
        ResultSet resultSetProjects = statement.executeQuery("SELECT * FROM projects");
        while (resultSetProjects.next()){
            int id = resultSetProjects.getInt("project_id");
            String title = resultSetProjects.getString("title");
            new Project(id, title,storages);
        }
        ResultSet resultSetTasks = statement.executeQuery("SELECT * FROM tasks");
        while (resultSetTasks.next()) {
            int id = resultSetTasks.getInt("task_id");
            String title = resultSetTasks.getString("title");
            int project_id = resultSetTasks.getInt("project_id");
            String type = resultSetTasks.getString("type");
            String priority = resultSetTasks.getString("priority");
            int executor_id = resultSetTasks.getInt("executor_id");
            String description = resultSetTasks.getString("description");
            new Task(id, title, type, priority, description, project_id, executor_id, storages);
        }
    }
}
