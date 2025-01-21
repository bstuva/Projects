// Assignment  : <assignment #4>
// Author      : Brayden Stuva>

import java.math.*;
import java.util.Random;
import java.util.Scanner;


public class Assignment04 {

    public static String secretNumberGen(){

        String secretNum = "";
        int x = 0;

        x = (int)(Math.random() * 9000) + 1000;

        secretNum = String.valueOf(x);


        return secretNum;

    }

    public static int guessCount(String x, String y){

        int count = 0;

        for(int i = 0; i < x.length(); i++){
            if(y.charAt(i) == x.charAt(i)){
                count++;
            }
        }
        return count;
    }



    public static void main(String[] args) {

        String secretNumber = secretNumberGen();
        String guess;
        String playAgain;
        int numberOfMatches = 0;
        int numberOfGuess = 1;
        boolean gameOn = true;

        Scanner scan = new Scanner(System.in);

        System.out.println("----- MASTERMIND -----");
        System.out.println("Guess the 4 digit number!");
        System.out.println();
        System.out.println(secretNumber);

        while(gameOn){

            System.out.printf("Guess %d: ", numberOfGuess);
            guess = scan.nextLine();

            if(guess.length() > 4){
                System.out.println("Oops too many digits");
            }
            else{
                numberOfMatches = guessCount(secretNumber, guess);
            }

            if(numberOfMatches == 4) {
                System.out.printf("Congratulations! You guessed the right number in %d guesses.", numberOfGuess);
                System.out.println();
                System.out.println("Would you like to play again (yes/no)?");
                playAgain = scan.nextLine();

                if(playAgain.matches("no")){
                    break;
                }
                else{
                    System.out.println("----- MASTERMIND -----");
                    System.out.println("Guess the 4 digit number!");
                    System.out.println();
                    numberOfGuess = 1;
                }
            }
            else {

                System.out.printf("You matched %d \n", numberOfMatches);
                numberOfGuess++;

            }

        }
    }
}
