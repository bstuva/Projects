public class Main {

    public static void displayGreeting(){
        System.out.println("Hello, and welcome!");
    }

    public static void displayText(String x){

        System.out.println(x);

    }

    public static void printTotal(int x, int y, int z){
        System.out.println(x+y+z);
    }

    public static double getAverage(int x, int y, int z){
        return ((double) (x + y + z) /3);
    }

    public static double averageLength(String one, String two, String three){
        int x = one.length();
        int y = two.length();
        int z = three.length();

        return ((double) (x + y + z) /3);
    }

    public static int lengthOfShortest(String one, String two){

        if(one.length() > two.length()){
            return two.length();
        }
        else{
            return one.length();
        }
    }

    public static String stringOfStars(String word){

        String asterisks = "*";
        String result = "";

        for(int i = 0; i < word.length(); i++){

            result += asterisks;
        }

        return result;

    }
    public static String maxStringOfStars(String one, String two){

        String asterisks = "*";
        String result = "";

        if(one.length() > two.length()){

            for(int i = 0; i < one.length(); i++){

                result += asterisks;
            }
        }
        else{

            for(int i = 0; i < two.length(); i++){

                result += asterisks;
            }
        }

        return result;
    }

    public static String midStringOfStars(String one, String two, String three){
        String asterisks = "*";
        String result = "";

        if ((one.length() > two.length() && one.length() < three.length()) || (one.length() > three.length() && one.length() < two.length())) {
            for(int i = 0; i < one.length(); i++){
                result += asterisks;
            }

        } else if ((two.length() > one.length() && two.length() < three.length()) || (two.length() > three.length() && two.length() < one.length())) {
            for(int i = 0; i < two.length(); i++){
                result += asterisks;
            }
        } else {
            for(int i = 0; i < three.length(); i++){
                result += asterisks;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        displayGreeting();
        System.out.println();
        displayText("Hello World!!!!");
        System.out.println();
        printTotal(7, 6, 9);
        System.out.println();
        System.out.println(getAverage(9, 6, 4));
        System.out.println();
        System.out.println(averageLength("bobby", "cookie", "soup"));
        System.out.println();
        System.out.println(lengthOfShortest("Brayden", "Australopithecus"));
        System.out.println();
        System.out.println(stringOfStars("Brayden"));
        System.out.println();
        System.out.println(maxStringOfStars("Brayden", "Keaton"));
        System.out.println();
        System.out.println(midStringOfStars("apple", "Bookkeeper", "Australopithecus"));
    }
}