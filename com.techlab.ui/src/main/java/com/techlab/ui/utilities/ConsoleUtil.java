package com.techlab.ui.utilities;

import java.util.Scanner;

public class ConsoleUtil {
    // https://stackoverflow.com/a/32295974/10942774
    public static void clearConsole() {
        System.out.print("\\u001B[H\\u001B[2J");
        System.out.flush();
    }

    public static void pressEnterToContinue(Scanner scanner) {
        System.out.println("Presiona \"enter\" para continuar...");
        scanner.nextLine();
    }
}
