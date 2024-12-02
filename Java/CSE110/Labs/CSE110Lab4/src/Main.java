import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int menuChoice;
        int sum = 0;
        int fact;
        int factTotal = 1;
        int l = 0;

        while(true){
            System.out.println("Please choose one option from the following menu:");
            System.out.println("1) Calculate the sum of integers from 1 to m");
            System.out.println("2) Calculate the factorial of a given number");
            System.out.println("3) Display the leftmost digit of a given number");
            System.out.println("4) Quit");

            menuChoice = in.nextInt();

            switch (menuChoice){
                case 1:
                    System.out.println("Enter a number: ");
                    int m = in.nextInt();

                    for(int z  = 1; z <= m; z++){
                        sum += z;
                    }
                    System.out.printf("The sum of 1 to %d is %d \n", m, sum);
                    break;

                case 2:
                    System.out.println("Enter a number: ");
                    fact = in.nextInt();

                    for(int z = fact; z > 0; z--){
                        factTotal *= z;
                    }
                    System.out.printf("The factorial of %d is %d \n", fact, factTotal);
                    break;

                case 3:
                    System.out.println("Enter a number: ");
                    l = in.nextInt();

                    String leftMost = String.valueOf(l);

                    System.out.printf("The leftmost digit of %d is %s \n", l, leftMost.charAt(0));

                    break;

                case 4:
                    System.out.println("Bye! \n");
                    break;
            }
        }
    }
}