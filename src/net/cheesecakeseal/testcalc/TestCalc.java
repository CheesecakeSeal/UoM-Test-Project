package net.cheesecakeseal.testcalc;

import java.util.Scanner;

public class TestCalc{
    public static void main(String[] args){
        // Initialising Scanner
        Scanner sc = new Scanner(System.in);
        // Initialise Variables
        double num1 = 0;
        double num2 = 0;
        double result = 0;
        char operator = 'a';

        System.out.println("--Calculator---");

        // Ask User for first number
        System.out.print("Enter first number: ");
        num1 = sc.nextDouble();
        // Ask User for the operation
        System.out.print("Enter an operator (+, -, *, /): ");
        operator = sc.next().charAt(0);
        //Ask User for first number
        System.out.print("Enter second number: ");
        num2 = sc.nextDouble();


        // Perform calculation based on operator
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Error: Division by zero is not allowed.");
                    sc.close();
                    return;
                }
                break;
            default:
                System.out.println("Invalid operator.");
                sc.close();
                return;
        }

        // Display result
        System.out.println("The result is: " + result);

        sc.close();
        System.exit(0);
    }
}
