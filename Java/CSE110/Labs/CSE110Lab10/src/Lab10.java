/*-------------------------------------------------------------
//AUTHOR: your name.
//FILENAME: title of the source file.
//SPECIFICATION: your own description of the program.
//FOR: CSE 110 - Lab #10
//TIME SPENT: how long it took you to complete the assignment.
//-----------------------------------------------------------*/

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Lab10
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        /* Create an array of Student */
        final int NUM_STUDENTS = 10;
        Student[] gradebook = new Student[NUM_STUDENTS];

        int count = 0;

        String inputFile = "lab10_student_data.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");
                Student newStudent = new Student(data[0], data[1], Double.parseDouble(data[2]));

                gradebook[count] = newStudent;
                count++;
            }

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        /* Find out duplicate student records by .equals() */
        for (int i = 0; i < NUM_STUDENTS; i++)
        {
            for (int j = i + 1; j < NUM_STUDENTS; j++)
            {
                if (gradebook[i].equals(gradebook[j]))
                {
                    System.out.printf("\"%s\" and \"%s\": %s\n",
                            gradebook[i].getFullName(), gradebook[j].getFullName(), gradebook[i].getId());
                }
            }
        }
    }
}
