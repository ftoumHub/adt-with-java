package org.demo.functor;

import java.util.Scanner;

public class ScanIntegers {

    private static void displayResultIfInt(final Scanner reader) {
        if (reader.hasNextInt()) {
            int i = reader.nextInt();
            System.out.println(i * i);
        } else {
            System.out.println("You don't give me an integer :-(");
        }
    }

    public static void main(String[] args) {
        System.out.println("Give me an integer");
        Scanner scanner = new Scanner(System.in);
        displayResultIfInt(scanner);
    }
}
