// ACSL Contest #2 - Bits - Senior Division
// Eric Ouyang

import java.util.Arrays;
import java.util.Scanner;

public class Bits
{
	private static final int numLines = 5;
	private static String[][] input = new String[numLines][];
	private static String[] output = new String[numLines];
	
	public static void main(String[] args)
	{
		Arrays.fill(output, ""); // fills output with empty strings
		
		Scanner kb = new Scanner(System.in); 
		
		// get data
		for (int i = 0; i < numLines; i++)
		{
			System.out.print("Elements in line " + (i + 1) + ": ");
			int num = kb.nextInt();
			input[i] = new String[num];
			for (int j = 0; j < num; j++)
			{
				System.out.print("  String " + (j + 1) + ": ");
				input[i][j] = kb.next();
			}
		}
		
		calcOutput();
		
		// display result
		System.out.println("\nOUTPUT:");
		for (int i = 0; i < numLines; i++)
			System.out.println((i+1) + ": " + output[i]);
	}
	
	// checks if all values of index is constant in an array of Strings
	private static boolean isConstant(String[] arr, int index)
	{
		int value = arr[0].charAt(index);
		for (String s : arr)
		{
			if (s.charAt(index) != value)
				return false;
		}
		return true;
	}
	
	// determines number of unique elements in array
	private static int numUnique(String[] arr)
	{
		Arrays.sort(arr); // sorts array to find duplicates
		
		int numUnique = arr.length;
		
		for (int j = 0; j < arr.length-1; j++)
			if (arr[j].equals(arr[j+1]))
				numUnique--;
		
		return numUnique;
	}
	
	// determines the output
	private static void calcOutput()
	{	
		for (int i = 0; i < numLines; i++)
		{
			int wildcards = 0; // counter for number of wildcards
			
			for (int j = 0; j < input[i][0].length(); j++) // iterator through bit string 
			{
				if (isConstant(input[i], j))
					output[i] += input[i][0].charAt(j); // adds the constant bit to output string
				else
				{
					output[i] += "*"; // adds a '*' to indicate potential wildcard
					wildcards++;
				}
			}
			
			// checks if there are enough unique strings to account for number of wildcards
			if (numUnique(input[i]) < Math.pow(2, wildcards)) 
				output[i] = "NONE";	
		}
	}
}