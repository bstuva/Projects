import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        int number_of_tests = 0;
        double test_average = 0;
        int all_test_sum = 0;
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome");
        System.out.println("Please enter the number of tests the class has: ");

        number_of_tests = in.nextInt();

        if(number_of_tests > 4){
            System.out.println("Must have meant a more reasonable number");
            System.out.printf("%d seems a bit high", number_of_tests);

            System.exit(1);
        }

        int[] tests = new int[number_of_tests];

        for(int i = 0; i < number_of_tests; i++){
            System.out.println("Please enter a test score");

            int temp = in.nextInt();

            tests[i] = temp;
        }

        in.close();

        for(int k = 0; k < tests.length; k++){

            all_test_sum += tests[k];
            test_average = (double) all_test_sum / tests.length;

        }
            System.out.println();
            System.out.printf("You averaged: %.2f percent", test_average);

    }
}