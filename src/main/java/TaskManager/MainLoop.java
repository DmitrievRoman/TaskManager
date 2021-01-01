package TaskManager;

import java.io.IOException;
import java.sql.SQLException;

public class MainLoop {
    public void getMenu() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("1.Создать пользователя");
        System.out.println("2.Удалить пользователя");
        System.out.println("3.Создать проект");
        System.out.println("4.Удалить проект");
        System.out.println("5.Создать задачу");
        System.out.println("6.Удалить задачу");

    }

}
