package TaskManager;
import java.sql.*;
public class DatabaseManagement  {
//    public DatabaseManagement() throws ClassNotFoundException, SQLException {
//        /*ResultSet resultSet = statement.executeQuery("SELECT * FROM users1");
//        while(resultSet.next()){
//            System.out.println(resultSet.getInt(1));
//            System.out.println(resultSet.getString(2));
//            System.out.println("-------");
//        }*/
//    }
    public void createUser(String userName) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "Root(root)1");
        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into users (name) values ('" + userName + "')");
    }
    public void deleteUser(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "Root(root)1");
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM users WHERE user_id = '" + id + "'");
    }
    public void createProject(String projectTitle) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "Root(root)1");
        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into projects (title) values ('" + projectTitle + "')");
    }
    public void deleteProject(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "Root(root)1");
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM projects WHERE project_id = '" + id + "'");
    }
    public void getAllUsers() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "Root(root)1");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            System.out.println("-------");
        }
    }
    public void getAllProjects() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "Root(root)1");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM projects");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            System.out.println("-------");
        }
    }
    public void dropTables() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "Root(root)1");
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE projects");
        statement.executeUpdate("DROP TABLE users");
    }

//    public void dropTable(String titleDatabase) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "Root(root)1");
//        Statement statement = connection.createStatement();
//        statement.executeUpdate("DROP TABLE '" +  titleDatabase + "'");
//    }
    public void createUsersTable() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "Root(root)1");
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE users(user_id int AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL)");
    }
    public void createProjectsTable() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "Root(root)1");
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE projects(project_id int AUTO_INCREMENT PRIMARY KEY, title VARCHAR(30) NOT NULL, user_id int, FOREIGN KEY(user_id) REFERENCES users (user_id)) ");
    }
}
