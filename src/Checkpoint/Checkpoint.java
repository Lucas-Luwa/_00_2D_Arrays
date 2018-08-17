package Checkpoint;

import java.util.Random;

public class Checkpoint {
	// -Instructions Copied from Level 4 M0 Checkpoint
	// 1. Create a new Java class called Checkpoint//K
	public static void main(String[] args) {
		// 2. Add a main method to the class
		// 3. Create and initialize a 2D integer array of 5 rows and 5 columns.
		int[][] nums = new int[5][5];
		// 4. Use a nested for loop to initialize each int in the 2D array to a random
		// number.
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[i].length; j++) {
				Random rand = new Random();
				int randint = rand.nextInt(100);
				nums[i][j] = randint;
				System.out.print(randint+", ");
			}
		}
		System.out.println("break");
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[i].length; j++) {
					System.out.print(nums[i][j]+",");
			}
		}
		// 5. Use a separate nested for loop to print the contents of the array if row
		// major order.
	}
}