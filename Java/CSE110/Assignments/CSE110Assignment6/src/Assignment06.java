// CSE 110     : <Class #> / <meeting days and times>
// Assignment  : <assignment #>
// Author      : <name> & <studentID>
// Description : <of the file contents>
import java.util.Scanner;
import java.util.Arrays;

public class Assignment06 {

    public static void main(String[] args) {
		System.out.println("What is the array size you'd like?");
		Scanner scan = new Scanner(System.in);
		int arrSize = scan.nextInt();
		System.out.printf("The array size you selected: %d\n", arrSize);
		System.out.println("Provide your number of integers");
		int[] arr = new int[arrSize];
		for (int i = 0; i < arrSize; i++) {
			arr[i] = scan.nextInt();
		}
		int[] arrCopy = Arrays.copyOf(arr, arrSize);
		System.out.println("How would you separate each item?");
		String sep = scan.next();
		int idx1 = scan.nextInt(), idx2 = scan.nextInt();
		
		//Method 1
		System.out.printf("printArray(%s,\"%s\"): ", Arrays.toString(arr), sep);
		printArray(arr, sep);
		System.out.println();
		
		//Method 2
		System.out.printf("getFirst(%s): %d\n", Arrays.toString(arr), getFirst(arr));

		//Method 3
		System.out.printf("getLast(%s): %d\n", Arrays.toString(arr), getLast(arr));

		//Method 4
		System.out.printf("getAllButFirst(%s): %s\n", Arrays.toString(arr), Arrays.toString(getAllButFirst(arr)));

		//Method 5
		System.out.printf("getIndexOfMin(%s): %d\n", Arrays.toString(arr), getIndexOfMin(arr));

		//Method 6
		System.out.printf("getIndexOfMax(%s): %d\n", Arrays.toString(arr), getIndexOfMax(arr));

		//Method 7
		arr = swapByIndex(arr, idx1, idx2);
		System.out.printf("swapByIndex(%s, %d, %d): %s\n", Arrays.toString(arrCopy), idx1, idx2, Arrays.toString(arr));

		//Method 8
		System.out.printf("removeAtIndex(%s, %d): %s\n", Arrays.toString(arr), idx1, Arrays.toString(removeAtIndex(arr, idx1)));

		//Method 9
		System.out.printf("insertAtIndex(%s, %d, 100): %s\n", Arrays.toString(arr), idx2, Arrays.toString(insertAtIndex(arr, idx2, 100)));

		//Method 10
		System.out.printf("isSorted(%s): %s\n", Arrays.toString(arr), isSorted(arr));

    }
    
    // 1) Write a public static method named printArray, 
    //   that takes two arguments. The first argument is an Array of int 
    //   and the second argument is a String. The method should print out 
    //   a list of the values in the array, each separated by the value of the second argument.
    public static void  printArray(int[] x, String string){
		for(int i = 0; i < x.length; i++){
			System.out.printf("%d%s ", x[i], string);
		}
	}
    
    // 2) Write a public static method named getFirst, 
    //    that takes an Array of int as an argument and returns 
    //    the value of the first element of the array.
    public static int getFirst(int[] x){
		return x[0];
	}
    
    // 3) Write a public static method named getLast, 
    //    that takes an Array of int as an argument and returns 
    //    the value of the last element of the array.
    public static int getLast(int[] x){
		return x[x.length-1];
	}
    
    // 4) Write a public static method named getAllButFirst, 
    //    that takes an Array of int as an argument and creates and returns 
    //    a new array with all of the values in the argument array except the first value.
    public static int[] getAllButFirst(int[] x){

		int[] newArray = new int[x.length-1];
		for(int i = 1; i < x.length; i++){
			newArray[i-1] = x[i];
		}
		return newArray;
	}
    
    // 5) Write a public static method named getIndexOfMin, 
    //    that takes an Array of int as an argument and returns 
    //    the index of the least value in the array.
    public static int getIndexOfMin(int[] x){
		int min = x[0];
		int indexOfMin = 0;
		int temp;

		for(int i = 1; i < x.length; i++){
			temp = x[i];

			if(temp < min){
				min = temp;
				indexOfMin = i;
			}

		}
		return indexOfMin;
	}
    
    // 6) Write a public static method named getIndexOfMax, 
    //    that takes an Array of int as an argument and returns 
    //    the index of the largest value in the array.
	public static int getIndexOfMax(int[] x){
		int max = x[0];
		int indexOfMax = 0;
		int temp;

		for(int i = 1; i < x.length; i++){
			temp = x[i];

			if(temp > max){
				max = temp;
				indexOfMax = i;
			}

		}
		return indexOfMax;
	}
    
    // 7) Write a public static method named swapByIndex, 
    //    that takes three arguments. The first argument is an Array of int, 
    //    and the second and third arguments are int indexes. 
    //    This method will swap the values at the two given index arguments 
    //    in the array, and return a reference to the array.
    public static int[] swapByIndex(int[] x, int index1, int index2){
		int swap1 = x[index1];
		int swap2 = x[index2];

		x[index1] = swap2;
		x[index2] = swap1;

		return x;
	}
    
    //8) Write a public static method named removeAtIndex, 
    //  that takes two arguments. The first argument is an Array of int, 
    //  and the second argument is an int index. This method create and return 
    //  a new array with all of the values in the argument array 
    //  except the value at the argument index.
	public static int[] removeAtIndex(int[] x, int index){
		int[] newArray = new int[x.length-1];

		int newIndex = 0;

		for (int i = 0; i < x.length; i++) {
			// Skip the element at the specified index
			if (i == index) {
				continue;
			}
			// Copy the element into the new array
			newArray[newIndex++] = x[i];
		}
		return newArray;
	}
    
    
    //9) Write a public static method named insertAtIndex, 
    //   that takes three arguments. The first argument is an Array of int, 
    //   the second argument is an int index, and the third argument is an int value. 
    //   This method create and return a new array with all of the values 
    //   in the argument array and including the third argument value 
    //   inserted at the index specified by the second argument value.
	public static int[] insertAtIndex(int[] x, int index2, int value){
		int[] newArray = new int[x.length+1];

		for (int i = 0; i < newArray.length; i++) {
			if (i < index2) {
				// Copy elements before the insertion index
				newArray[i] = x[i];
			} else if (i == index2) {
				// Insert the new value at the specified index
				newArray[i] = value;
			} else {
				// Copy elements after the insertion index
				newArray[i] = x[i - 1];
			}
		}
		return newArray;
	}
	
    //10) Write a public static method named isSorted, 
    //    that takes an Array of int as an argument. 
    //    This method should return the boolean value true 
    //    if all the element values in the array are in ascending order; 
    //    otherwise the method should return the boolean value false.
    public static boolean isSorted(int[] x){
		if (x.length <= 1) {
			return true;
		}
		for (int i = 0; i < x.length - 1; i++) {
			if (x[i] > x[i + 1]) {
				return false;
			}
		}
		return true;
	}
}
