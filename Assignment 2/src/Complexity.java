import java.util.Scanner;

public class Complexity {

    public void method1(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Iteration Number " + (int) (count + 1)); // making it count+1 because I want to
                                                                             // start the count from 1 to make counting
                                                                             // easier with natural numbers
                count++;
            }
        }
    }

    public void method2(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    System.out.println("Iteration Number " + (int) (count + 1));
                    count++;
                }
            }
        }
    }

    public void method3(int n) {
        int count = 0;
        for (int i = 1; i <= n; i *= 2) {
            System.out.println("Iteration Number " + (int) (count + 1));
            count++;
        }
    }

    public void method4(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n; j > 1;) {
                j = j / 2;
                System.out.println("Iteration Number " + (int) (count + 1));
                count++;
            }
        }
    }

    public void method5(int n) {
        int count = 0;
        for (int i = n; i > 1; i = (int) Math.sqrt(i)) {
            System.out.println("Iteration Number " + (int) (count + 1));
            count++;
        }
    }

    static int count = 0;

    public int method6(int n) {

        if (n < 1) {
            return 1;
        }
        System.out.println("Recursion Cycle " + (int) (count + 1));
        count++;
        // the final value will be 1 more than the last recursion cycle number, as the return statement is after the System.out.println statement.
        return method6(n - 1) + method6(n - 1);
    }

    public static void main(String[] args) {
        Complexity new_c = new Complexity();
        
        System.out.println(
            "\nChoose what you want to test: \n\n1. O(n^2)\n2. O(n^3)\n3. O(log(n))\n4. O(nlog(n))\n5. O(loglog(n))\n6. O(2^n)\n"
        );

        System.out.print("Enter your choice number: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
            System.out.print("Enter the value of n: ");
            int n1 = sc.nextInt();
            new_c.method1(n1);
            break;

            case 2:
            System.out.print("Enter the value of n: ");
            int n2 = sc.nextInt();
            new_c.method2(n2);
            break;

            case 3:
            System.out.print("Enter the value of n: ");
            int n3 = sc.nextInt();
            new_c.method3(n3);
            break;

            case 4:
            System.out.print("Enter the value of n: ");
            int n4 = sc.nextInt();
            new_c.method4(n4);
            break;

            case 5:
            System.out.print("Enter the value of n: ");
            int n5 = sc.nextInt();
            new_c.method5(n5);
            break;

            case 6:
            System.out.print("Enter the value of n: ");
            int n6 = sc.nextInt();
            System.out.println("Final Value: " + new_c.method6(n6));
            break;
        
            default:
            System.out.println("Enter from the choices given: ");
            break;
        }
        sc.close();
    }
}
