package TaskManager.Menu;

public class Menu {
    public static void getMenu() {
        System.out.println("1.Создать пользователя");
        System.out.println("2.Удалить пользователя");
        System.out.println("3.Создать проект");
        System.out.println("4.Удалить проект");
        System.out.println("5.Создать задачу");
        System.out.println("6.Удалить задачу");
        System.out.println("7.Вывести список задач назначенных на пользователя");
        System.out.println("8.Вывести список всех задач в проекте");
        System.out.println("9.Вывести список всех пользователей в проекте");
        System.out.println("10.Вывести список всех проектов");
        System.out.println("11.Вывести список всех пользователей");
        System.out.println("Введите:\n'load' - чтобы загрузить данные из базы");
        System.out.println("'menu' чтобы еще раз увидеть данное меню");
    }

    public static void getStartMenu() {
        System.out.println("1.Новая сессия");
        System.out.println("2.Загрузить прошлую сессию");
        System.out.println("3.Выбрать сессию для загрузки");
    }

}
