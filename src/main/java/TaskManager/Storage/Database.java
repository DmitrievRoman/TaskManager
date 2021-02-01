package TaskManager.Storage;
import TaskManager.Units.Project;
import TaskManager.Units.Task;
import TaskManager.Units.User;

import java.sql.*;

public class Database {
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "Root(root)1";
    public static final String URL = "jdbc:mysql://localhost:3306/test";
    public static Statement statement;
    public static Connection connection;
    public static int random = (int)(Math.random() * 100);

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
    //Данный метод создает новые таблицы (users, projects, tasks), для того, чтобы избежать одинаковых названий таблиц,
    //после названия таблицы добавляется рандомное число от 0 до 99
    //в цикле идет проверка на уникальность имени, цикл будет выполняться до тех пор пока не будет сгенерировано уникальное имя таблицы
    public static void createTables() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SHOW TABLES");
        boolean a = true;
        boolean b; //Если b = false значит таблица с таким именем уже есть и надо пройти цикл еще раз
        while (a) {
            b = true;
            while (resultSet.next()) {
                String s = "users" + random;
                if(resultSet.getString(1).equals(s)){
                    random = (int)(Math.random() * 100);
                    b = false;
                }
            }
            if(b){
                a = false;
            }
        }
        createUsersTable();
        createProjectsTable();
        createTasksTable();
        if (!isExist()){
            createTableWithLastId();
        }
        addLastId(random);
    }

    public static void createUsersTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE users" + random + "(user_id int PRIMARY KEY," +
                " name VARCHAR(20) NOT NULL)");
    }
    public static void createProjectsTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE projects" + random + "(project_id int PRIMARY KEY," +
                " title VARCHAR(30) NOT NULL)");
    }
    public static void createTasksTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE tasks" + random + "(" +
                "task_id int PRIMARY KEY," +
                "title VARCHAR(30) NOT NULL," +
                "project_id int REFERENCES projects(project_id)," +
                "type VARCHAR(30)," +
                "priority VARCHAR(30)," +
                "executor_id int REFERENCES users(user_id)," +
                "description VARCHAR(300))");
    }
    public static void createTableWithLastId() throws SQLException {
        statement.executeUpdate("CREATE TABLE last_id(id INTEGER)");
    }
    public static void addLastId(int id) throws SQLException {
        statement.executeUpdate("DELETE FROM last_id");
        statement.executeUpdate("insert into last_id(id) values ('" + id + "')");
    }
    public static boolean isExist() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SHOW TABLES");
        while (resultSet.next()) {
            if(resultSet.getString(1).equals("last_id")) {
                return true;
            }
        }
        return false;
    }
    public static int getLastId() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT id FROM last_id");
        int lastId = 0;
        if(resultSet.next()) {
             lastId = resultSet.getInt("id");
        }
        return lastId;
    }

    public static void add(User user) throws SQLException {
        int id = getLastId();
        statement.executeUpdate("insert into users" + id + " (user_id,name) values ('" + user.getId() +"','" + user.getName() + "')");
    }
    public static void add(Project project) throws SQLException {
        int id = getLastId();
        statement.executeUpdate("insert into projects" + id + "(project_id,title) values ('" + project.getId() +"','" + project.getTitle() + "')");
    }
    public static void add(Task task) throws SQLException {
        int id = getLastId();
        statement.executeUpdate("insert into tasks" + id + " (task_id,title,project_id,type,priority,executor_id,description) values ('" + task.getId() + "','" + task.getTitle() + "','" + task.getProject().getId() + "','" + task.getType() + "','" + task.getPriority() + "','" + task.getExecutor().getId() + "','" + task.getDescription() + "')");
    }

    public static void load(Storages storages, int count) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users" + count);
        while(resultSet.next()) {
            int id = resultSet.getInt("user_id");
            String name = resultSet.getString("name");
            new User(id, name, storages);
        }
        ResultSet resultSetProjects = statement.executeQuery("SELECT * FROM projects" + count);
        while (resultSetProjects.next()){
            int id = resultSetProjects.getInt("project_id");
            String title = resultSetProjects.getString("title");
            new Project(id, title,storages);
        }
        ResultSet resultSetTasks = statement.executeQuery("SELECT * FROM tasks" + count);
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
