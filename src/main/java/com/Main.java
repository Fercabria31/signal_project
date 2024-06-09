package com;

import java.io.IOException;
import java.util.Scanner;

import com.cardio_generator.*;
import com.data_management.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Specify on the command line the class to execute");
        System.out.println("for DataStorage type data");
        System.out.println("for HealthDataSimulator type health");
        
        String input = scanner.nextLine();
        while (input.equals("data") || input.equals("health")) {
            if (input.equals("data")) {
                DataStorage.main(new String[]{});
            } else if (input.equals("health")) {
                HealthDataSimulator.main(args);
            }
        }
        System.out.println("Incorrect. No class found");

        scanner.close();
    }
}
