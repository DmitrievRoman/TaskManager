package TaskManager.Main;

import TaskManager.Menu.Menu;
import TaskManager.Storage.Database;
import TaskManager.Storage.Storages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class MainMenu {
    public static void main(String[] args) throws IOException, SQLException {
        Storages storages = new Storages();
        Menu.getStartMenu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String userInput = reader.readLine();
            if(!userInput.isEmpty()){
                switch (userInput){
                    case"1":
                        Database.createTables();
                        MainLoop.mainLoop(storages);
                        break;
                    case"2":
                        //не работает
                        if(Database.isExist()) {
                            int count = Database.getLastId();
                            Database.load(storages, count);
                        }
                        break;
                    case"3":

                        break;
                }
            } else {
                System.out.println("Введите число");
            }
        }
    }
}
