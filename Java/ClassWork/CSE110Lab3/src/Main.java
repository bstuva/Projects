import java.util.Scanner;

@SuppressWarnings("t")
public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int i = 0;
        int hw = 0;
        int midterm = 0;
        int finalExam = 0;
        int chancesLeft = 3;
        double weightedTotal = 0.0;

        while(i < 3){

            if(i == 0){
                //asks for the homework grade
                System.out.println("Enter your HOMEWORK grade: ");
                hw = in.nextInt();
                if(hw < 0 || hw > 100){
                    System.out.println("[ERR] Invalid input. A homework grade should be in [0, 100].");
                }
                else{
                    i++;
                }

            }
            if(i == 1){
                //asks for the midterm exam grade
                System.out.println("Enter your MIDTERM EXAM grade: ");
                midterm = in.nextInt();

                if(midterm < 0 || midterm > 100){
                    System.out.println("[ERR] Invalid input. A midterm grade should be in [0, 100].");
                }
                else{
                    i++;
                }
            }
            if(i == 2){
                //asks for the final exam grade
                System.out.println("Enter your FINAL EXAM grade: ");

                finalExam = in.nextInt();
                if(finalExam < 0 || finalExam > 200){
                    System.out.println("[ERR] Invalid input. A final grade should be in [0, 100].");
                }
                else{
                    i++;
                }
            }
        }

        weightedTotal = ((double) finalExam / 200*50) + (midterm * 0.25) + (hw * 0.25);

        if(weightedTotal >= 50){
            System.out.println("Student PASSED the class");
        }
        else{
            System.out.println("Student FAILED the class");
        }

    }
}