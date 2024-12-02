import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String fName;
        String lName;
        String fullName;
        int nameLength = 0;

        String title1 = new String("cse110");
        String title2 = "cse110";

        System.out.println("Please enter first name: ");
        Scanner in  = new Scanner(System.in);

        fName = in.next();
        System.out.println();
        System.out.println("Please enter last name: ");
        lName = in.next();

        fullName = fName + " " + lName;

        fullName = fullName.toUpperCase();

        nameLength = fullName.length();
        System.out.println(fullName);
        System.out.println(nameLength);

        if(title1 == title2){
            System.out.println("String comparison using \"==\" sign works");
        }
        else{
            System.out.println("String comparison using \"==\" sign DOES NOT works");
        }
        if(title1.equals(title2)){
           System.out.println("print \"String comparison using \"equals\" method works\"");
        }
        else{
            System.out.println("print \"String comparison using \"equals\" method DOES NOT works\"");
        }
    }
}