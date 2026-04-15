package MainPackage;

import java.util.Scanner;

public class CalculatorApp {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        Calculator cal = new SimpleCalculator();

        while (true) {
            try {

                System.out.println("======== Calculator =======");
                System.out.println("1. Add");
                System.out.println("2. Subtract");
                System.out.println("3. Multiply");
                System.out.println("4. Divide");
                System.out.println("5. Exit");

                System.out.print("Enter Choice: ");
                int choice = sc.nextInt();

                // Exit condition
                if (choice == 5) {
                    System.out.println("Exiting...");
                    break;
                }

                // Validate choice BEFORE taking numbers
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice. Please select between 1-5.");
                    continue;
                }

                // Now take numbers only if choice valid
                System.out.print("Enter number 1: ");
                double num1 = sc.nextDouble();

                System.out.print("Enter number 2: ");
                double num2 = sc.nextDouble();

                double result = 0;

                switch (choice) {

                    case 1:
                        result = cal.add(num1, num2);
                        break;

                    case 2:
                        result = cal.subtract(num1, num2);
                        break;

                    case 3:
                        result = cal.multiply(num1, num2);
                        break;

                    case 4:
                        result = cal.divide(num1, num2);
                        break;
                }

                System.out.println("Result: " + result);

            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter numbers only.");
                sc.nextLine(); // clear buffer
            }
        }

        sc.close();
    }
}