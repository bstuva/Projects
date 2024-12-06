import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // declare and instantiate a Scanner
        Scanner input = new Scanner(System.in);



        //declared and initialized variables

        int currentShares = 0;
        int purchasePrice = 0;
        int marketPrice = 0;
        int availableFunds = 0;
        int transactionFee = 10;
        int i = 0;

        //Collecting inputs
        System.out.println("Given Inputs");
        System.out.print("Current Shares  : ");
        currentShares = input.nextInt();
        System.out.print("Purchase Price  : ");
        purchasePrice = input.nextInt();
        System.out.print("Market Price    : ");
        marketPrice = input.nextInt();
        System.out.print("Available Funds : ");
        availableFunds = input.nextInt();
        System.out.println();
        input.close();

        //misc. calculations
        int m = (availableFunds - transactionFee) / marketPrice;

        // compute required values
        int numberOfSharesToBuy = (int) Math.floor(m);
        int totalBuyCost = transactionFee + (marketPrice * numberOfSharesToBuy);
        int perShareBuyValue = purchasePrice - marketPrice;
        int totalBuyValue = perShareBuyValue * numberOfSharesToBuy;
        int perShareSellValue = marketPrice - purchasePrice;
        int totalSellValue = perShareSellValue * currentShares;

        //buy,sell, or hold if statements
        //buy
        if(marketPrice < purchasePrice && totalBuyValue > transactionFee) {

            System.out.printf("Buy %d shares." , numberOfSharesToBuy);
            i = 1;

        }

        //sell
        if(marketPrice > purchasePrice && totalSellValue > transactionFee) {

            System.out.printf("Sell %d shares." , totalSellValue - transactionFee);
            i = 1;
        }



        //hold if no other options are met

        if(i == 0) {
            System.out.print("Hold Shares.");
        }
    }
}