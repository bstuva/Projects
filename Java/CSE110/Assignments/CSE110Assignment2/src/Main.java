import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //input variables
        double roadLength = 0;
        int numOfLanes = 0;
        int asphaltDepth = 0;
        int daysNeeded;
        int ASPHALT_WEIGHT_PER_CUBIC_FEET = 150;
        int STOPLIGHT_COST = 25000;

        System.out.println("=== INFO ===");
        System.out.println("Length of road project (miles) : ");
        roadLength = in.nextDouble();
        System.out.println("Number of lanes                : ");
        numOfLanes = in.nextInt();
        System.out.println("Depth of asphalt (inches)      : ");
        asphaltDepth = in.nextInt();
        System.out.println("Days to complete project       : ");
        daysNeeded = in.nextInt();

        //conversion variables
        double asphalt_depth_in_feet = asphaltDepth * 0.00833333;
        int lane_width_in_feet = numOfLanes * 12;
        double road_length_in_feet = roadLength * 5280;

        //calculations
        int truckLoads = (int)Math.ceil((road_length_in_feet * numOfLanes * lane_width_in_feet * asphalt_depth_in_feet * ASPHALT_WEIGHT_PER_CUBIC_FEET) * 0.001);
        double roadLengthInFeet = roadLength * 5280;
        int num_of_intersections = (int)Math.ceil(roadLength);
        int numOfStopLights = (( 2 + numOfLanes) * num_of_intersections);
        int numOfCrew = (int)Math.ceil((50 * roadLength * numOfLanes) / daysNeeded);
        int numOfConduit = (int)Math.ceil((roadLength * 5280.00) / 24);

        System.out.println();
        System.out.println("=== Amount of materials needed ===");
        System.out.printf("Truckloads of Asphalt : %d\n", truckLoads);
        System.out.printf("Stoplights            : %d\n" , numOfStopLights);
        System.out.printf("Conduit pipes         : %d\n" , numOfConduit);
        System.out.printf("Crew members needed   : %d\n" , numOfCrew);

        //computed cost variables
        double asphaltCost = (5 * 200 * truckLoads);
        double conduitCost = numOfConduit * 500;
        double laborCost = (8 * daysNeeded * 25 * numOfCrew);
        double stoplightCost = (STOPLIGHT_COST * numOfStopLights);
        double projectCost = asphaltCost + conduitCost + laborCost + stoplightCost;

        System.out.println();
        System.out.println("=== Cost of Materials ============");
        System.out.printf("Cost of Asphalt       : $%.2f\n" , asphaltCost);
        System.out.printf("Cost of Stoplights    : $%.2f\n" , stoplightCost);
        System.out.printf("Cost of Conduit pipes : $%.2f\n" , conduitCost);
        System.out.printf("Cost of Labor         : $%.2f\n" , laborCost);
        System.out.println();
        System.out.println("=== Total Cost of Project ========");
        System.out.printf("Total cost of project : $%.2f\n" , projectCost);
    }
}