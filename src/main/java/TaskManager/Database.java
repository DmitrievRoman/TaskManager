package TaskManager;
import java.sql.*;

public class Database {
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "Root(root)1";
    public static final String URL = "jdbc:mysql://localhost:3306/test";
    public static Statement statement;
    public static Connection connection;
    public static int count;

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
    public static void createTables() throws SQLException {
        createUsersTable();
        createProjectsTable();
        createTasksTable();
        count++;
    }

    public static void createUsersTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE users" + count + "(user_id int PRIMARY KEY," +
                " name VARCHAR(20) NOT NULL)");
    }
    public static void createProjectsTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE projects" + count + "(project_id int PRIMARY KEY," +
                " title VARCHAR(30) NOT NULL)");
    }
    public static void createTasksTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE tasks" + count + " (" +
                "task_id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                "title VARCHAR(30) NOT NULL," +
                "project_id INTEGER REFERENCES projects(project_id)," +
                "type VARCHAR(30)," +
                "priority VARCHAR(30)," +
                "executor_id INTEGER REFERENCES users(user_id)," +
                "description VARCHAR(300))");
    }
    public static void createTableWithLastId() throws SQLException {
        statement.executeUpdate("CREATE TABLE last_id(id INTEGER)");
    }
    public static void addLastId(int id) throws SQLException {
        statement.executeUpdate("insert into last_id(id) values ('" + id + "')");
    }

    public static void add(User user) throws SQLException {
        statement.executeUpdate("insert into users (user_id,name) values ('" + user.getId() +"','" + user.getName() + "')");
    }
    public static void add(Project project) throws SQLException {
        statement.executeUpdate("insert into projects (project_id,title) values ('" + project.getId() +"','" + project.getTitle() + "')");
    }
    public static void add(Task task) throws SQLException {
        statement.executeUpdate("insert into tasks(task_id, title, project_id, type, priority, executor_id, description)" +
                " values('"+ task.getId() + "'," +
                "'" + task.getTitle() + "'," +
                "'" + task.getProject().getId() + "'," +
                "'" + task.getType() + "'," +
                "'" + task.getPriority() + "'," +
                "'" + task.getExecutor().getId() + "'," +
                "'" + task.getDescription() + "' )");
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
