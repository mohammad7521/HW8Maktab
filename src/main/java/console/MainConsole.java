package console;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainConsole {

    public static void mainMenu() {

        while (true) {
            System.out.println("1-Admin: ");
            System.out.println("2-Customer: ");

            Scanner scanner = new Scanner(System.in);

            try {
                int userSelect = scanner.nextInt();
                switch (userSelect) {
                    case 1:
                        AdminConsole.adminMenu();
                        break;
//                    case 2:
//                        CustomerConsole.clientMenu();
//                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a valid number!");
            }
        }
    }
}
