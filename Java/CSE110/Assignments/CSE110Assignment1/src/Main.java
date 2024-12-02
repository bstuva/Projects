import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // declare and instantiate a Scanner
        Scanner in = new Scanner(System.in);
        // declare and initialize variables
        int numPizzasPurchased = 0;
        int slicesPerPizza = 0;
        int numOfAdults = 0;
        int numOfChildren = 0;

        // prompt for and collect inputs
        System.out.println("Number of pizzas purchased: ");
        numPizzasPurchased = in.nextInt();

        System.out.println("Number of slices per pizza: ");
        slicesPerPizza = in.nextInt();

        System.out.println("Number of adults: ");
        numOfAdults = in.nextInt();

        System.out.println("Number of children: ");
        numOfChildren = in.nextInt();

        // compute required values
        int totalSlices = numPizzasPurchased * slicesPerPizza;
        int totalAdultSlices = 2 * numOfAdults;
        int slicesForChildren = totalSlices - totalAdultSlices;
        int slicesChildrenGet = slicesForChildren / numOfChildren;
        int leftOverSlices =  slicesForChildren % numOfChildren;

        // display required outputs
        System.out.println("Expected Results:");
        System.out.printf("Total number of slices of pizza                : %d\n", totalSlices);
        System.out.printf("Total number of slices given to adults         : %d\n", totalAdultSlices);
        System.out.printf("Total number of slices available for children  : %d\n", slicesForChildren);
        System.out.printf("Number of slices each child will get           : %d\n", slicesChildrenGet);
        System.out.printf("Number of slices left over                     : %d\n", leftOverSlices);
    }
}