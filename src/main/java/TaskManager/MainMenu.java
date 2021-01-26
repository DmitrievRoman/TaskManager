package TaskManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class MainMenu {
    public static void main(String[] args) throws IOException, SQLException {

        Menu.getStartMenu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String userInput = reader.readLine();
            if(!userInput.isEmpty()){
                switch (userInput){
                    case"1":
                        Database.createTables();
                        break;
                    case"2":

                }
            } else {
                System.out.println("Введите число");
            }
        }
    }
}
