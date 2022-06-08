import java.util.*;
public class Sorting {

	public static void main(String[] args) {
		int SIZE = 300;
		Scanner scanner = new Scanner(System.in);
		ArrayList<Integer> randNums;
		
		while(true) {
			
			System.out.println("\nPlease enter the number corresponding to your choice.");
			System.out.println("1.  Selection Sort\n2.  Insertion Sort\n7.  Exit");
			
			String choice = scanner.nextLine();
			
			if (choice.equals("1"))
			{
				randNums = RandomArrayListGenerator(SIZE);
				System.out.println("\nUnsorted Array:");
				
				int count = 1;
				for (int num : randNums) {
					System.out.print(num + "   ");
					if (count % 10 == 0) {
						System.out.println();
					}
					count++;
				}
				
				SelectionSort(randNums);
				System.out.println("\nSorted Array: ");
				
				count = 1;
				for (int num : randNums) {
					System.out.print(num + "   ");
					if (count % 10 == 0) {
						System.out.println();
					}
					count++;
				}
				
			}
			else if (choice.equals("2"))
			{
				randNums = RandomArrayListGenerator(SIZE);
				System.out.println("\nUnsorted Array:");
				
				int count = 1;
				for (int num : randNums) {
					System.out.print(num + "   ");
					if (count % 10 == 0) {
						System.out.println();
					}
					count++;
				}
				
				InsertionSort(randNums);
				System.out.println("\nSorted Array: ");
				
				count = 1;
				for (int num : randNums) {
					System.out.print(num + "   ");
					if (count % 10 == 0) {
						System.out.println();
					}
					count++;
				}
				
			}
			else if (choice.equals("7"))
			{
				System.out.print("Exiting program");
				
				
				// I wanted to be creative and have the dots after "Exiting program" slowly appear, so I looked it
				// up Mr. C, I hope you like it
				
				int ms = 750;
				for (int i = 0; i < 3; i++) {
					pause(ms);
					System.out.print(".");
				}
				
				pause(ms);
				System.out.println("\nProgram successfully exited");
				System.exit(0);
			}
			else
			{
				System.out.println("Please enter either 1, 2, or 7.");
			}
			
		}

	}

	public static ArrayList<Integer> RandomArrayListGenerator(int size) {
		
		ArrayList<Integer> nums = new ArrayList<Integer>();
		
		for (int i = 0; i < size; i++) {
			nums.add((int)(1000 * Math.random() + 1));
		}
		
		return nums;
		
	}
	
	public static void SelectionSort(ArrayList<Integer> nums) {
		
		int len = nums.size();
		
		for (int i = 0; i < len - 1; i++) {
			
			int minIndex = i;
			
			for (int j = i + 1; j < len; j++) {
				
				if (nums.get(j) < nums.get(minIndex)) {
					minIndex = j;
				}
				
			}
			
			int temp = nums.get(minIndex);
			nums.set(minIndex, nums.get(i));
			nums.set(i, temp);
				
		}
		
	}
	
	public static void InsertionSort(ArrayList<Integer> nums) {
		
		int len = nums.size();
		
		for (int i = 1; i < len; i++) {
			
			int j = i;
			int num = nums.get(i);
			
			while(j > 0 && num < nums.get(j - 1)) {
				nums.set(j, nums.get(j - 1));
				j--;
			}
			
			nums.set(j,  num);
			
		}
		
	}
	
	// This is what I found on StackExchange for how to make Java delay a program as per my comment above
	public static void pause(int ms) {
		
		try {
			Thread.sleep(ms);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		
	}
	
}
