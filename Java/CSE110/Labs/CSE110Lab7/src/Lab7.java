
/*-------------------------------------------------------------
// AUTHOR: Brayden Stuva.
//-----------------------------------------------------------*/
import java.util.Scanner;

public class Lab7
{
    public static void main(String[] args)
    {
        /* Declare initial variables you need */
        Scanner scanner = new Scanner(System.in);

        /* Ask the user for the number of students */
        System.out.println("How many students do you have?");
        int numStudents = scanner.nextInt();

        /* Create two arrays: one for groups the other for grades */
        int[] studentGroups = null;
        double[] studentGrades = null;
        
        // >>>>> YOUR CODE HERE <<<<<
        for(int i = 1; i < numStudents; i++){


        }

        /* A loop to fill up the array of */
        
        // >>>>> YOUR CODE HERE <<<<<

        /* Print out the content of the two arrays as a list (from the back). */
        System.out.println("\n===== List of Student Records =====");
        
        // >>>>> YOUR CODE HERE <<<<<

        /* Print some statistics for each group. You must use the method "printStats" */
        System.out.println("\n===== Group Statistics =====");
        for (int i = 0; i < findTotalNumOfGroups(studentGroups); i++)
        {
            printStats(studentGroups, studentGrades, i + 1);
        }

        /* Clean up */
        scanner.close();
    }

    /**
     * Print out the count and average for targetGroup.
     * 
     * @param groups      an array of all students' groups
     * @param grades      an array of all students' grades
     * @param targetGroup the target group
     */
    private static void printStats(int[] groups, double[] grades, int targetGroup)
    {
        int count = 0;
        double sum = 0;
        double average = 0;

        /* For each record in groups */
        for (int i = 0; i < groups.length; i++)
        {
            /* If we find a record matching targetGroup, update count and sum */
            
            // >>>>> YOUR CODE HERE <<<<<
        }

        /* Update the average */
        
        // >>>>> YOUR CODE HERE <<<<<

        /* Output */
        System.out.printf("Group #%d has %d student(s), the average is %.2f\n", targetGroup, count, average);
    }

    /**
     * Find out the total number of groups on record. Group numbers are assumed to
     * be a cumulative sequence starting from 1, e.g., [1, 2, 3, 4, 5]. Therefore,
     * the total number of groups is simply the maximum number on a record (array)
     * of group numbers.
     * 
     * @param groups an array of group numbers
     * @return the total number of groups on the record
     */
    private static int findTotalNumOfGroups(int[] groups)
    {
        if (groups == null)
        {
            return 0;
        }

        int numGroups = 0;
        for (int i = 0; i < groups.length; i++)
        {
            if (groups[i] > numGroups)
            {
                numGroups = groups[i];
            }
        }
        return numGroups;
    }

}
