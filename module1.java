// Java Basics Project Demonstrating Fundamental Concepts
// Package Structure
package javabasicsproject;

public class Main {
    public static void main(String[] args) {
        // ---- Java Hello World ----
        System.out.println("Hello World from Java Basics Project!");

        // ---- Variables and Data Types ----
        int age = 20;
        double salary = 50000.50;
        char grade = 'A';
        boolean isStudent = true;

        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
        System.out.println("Grade: " + grade);
        System.out.println("Is Student: " + isStudent);

        // ---- Scanner Input (REMOVED — causes runtime timeout online)
        String name = "User"; // predefined name
        System.out.println("Welcome, " + name + "!");

        // ---- Operators ----
        int a = 10, b = 5;
        int sum = a + b;
        int product = a * b;
        System.out.println("Sum: " + sum);
        System.out.println("Product: " + product);

        // ---- Expressions & Blocks ----
        {
            int x = 50;
            System.out.println("Inside block, x = " + x);
        }

        // ---- Comments ----
        // This is a single-line comment
        /* This is a multi-line comment */

        // ---- if...else ----
        if (age > 18) {
            System.out.println("You are an adult.");
        } else {
            System.out.println("You are not an adult.");
        }

        // ---- switch ----
        switch (grade) {
            case 'A':
                System.out.println("Excellent Grade!");
                break;
            case 'B':
                System.out.println("Good Grade!");
                break;
            default:
                System.out.println("Grade not recognized.");
        }

        // ---- for Loop ----
        for (int i = 1; i <= 5; i++) {
            System.out.println("For Loop Count: " + i);
        }

        // ---- for-each Loop ----
        int[] numbers = {1, 2, 3, 4, 5};
        for (int num : numbers) {
            System.out.println("For-each value: " + num);
        }

        // ---- while Loop ----
        int count = 3;
        while (count > 0) {
            System.out.println("While Loop: " + count);
            count--;
        }

        // ---- break Statement ----
        for (int i = 1; i <= 10; i++) {
            if (i == 5) break;
            System.out.println("Break Example i = " + i);
        }

        // ---- continue Statement ----
        for (int i = 1; i <= 5; i++) {
            if (i == 3) continue;
            System.out.println("Continue Example i = " + i);
        }
    }
}
