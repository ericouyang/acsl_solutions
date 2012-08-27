// Eric Ouyang, Phillips Academy, Senior-3

import java.util.Scanner;
import java.util.ArrayList;

public class GridFit {
	private final static int max = 35;
	private final static int numsPerRow = 5;
	private final static int numRequested = 5;
	private static ArrayList<Integer> filledSquares;
	private static int[] requestedArrangements = new int[numRequested];
	
	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in); 
		
		// get data
		System.out.print("Number of filled squares: ");
		int num = kb.nextInt();
		filledSquares = new ArrayList<Integer>(num);
		for (int i = 0; i < num; i++)
		{
			System.out.print("Position of filled square: ");
			filledSquares.add(kb.nextInt());
		}
		
		for (int j = 0; j < numRequested; j++)
		{
			System.out.print("Arrangement to fit: ");
			requestedArrangements[j] = kb.nextInt();
		}

		printOutput();
	}
	
	private static void printOutput()
	{
		for (int i = 0; i < numRequested; i++)
		{
			int n = requestedArrangements[i];
			int result = -1;
			
			switch (n)
			{
				case 1:
					result = getLowestEmptyColumn(1);
					if (result != -1)
						filledSquares.add(result);
					break;
				case 2:
					result = getLowestEmptyColumn(1);
					if ((result + numsPerRow) > max)
						result = -1;
					if (result != -1)
					{
						filledSquares.add(result);
						filledSquares.add(result + numsPerRow);
					}
					break;
				case 3:
					result = getLowestAdjacentColumns(1);
					if (result != -1)
					{
						filledSquares.add(result);
						filledSquares.add(result + 1);
					}
					break;
				case 4:
					result = getLowestEmptyColumn(1);
					while (isLastColumn(result) || !isColumnClear(result + numsPerRow + 1))
					{
						result = getLowestEmptyColumn(result+1);
						if (result == -1)
							break;
					}
					if ((result + numsPerRow + 1) > max)
						result = -1;
					if (result != -1)
					{
						filledSquares.add(result);
						filledSquares.add(result + numsPerRow);
						filledSquares.add(result + numsPerRow + 1);
					}
					break;
				case 5:
					result = getLowestAdjacentColumns(1);
					if ((result + numsPerRow + 1) > max)
						result = -1;
					if (result != -1)
					{
						filledSquares.add(result);
						filledSquares.add(result + 1);
						filledSquares.add(result + numsPerRow);
					}
					break;
			}
			
			if (result == -1)
				System.out.println((i+1) + ". NONE");
			else
				System.out.println((i+1) + ". " + result);
		}
	}
	
	private static boolean isColumnClear(int n)
	{
		for (int i = n; i <= max; i += numsPerRow)
		{
			if (filledSquares.contains(i))
				return false;
		}
		return true;
	}
	
	private static boolean isLastColumn(int n)
	{
		return n % numsPerRow == 0;
	}
	
	private static int getLowestEmpty(int start)
	{
		for (int i = start; i <= max; i++)
			if (!filledSquares.contains(i))
				return i;
		return -1;
	}
	
	private static int getLowestEmptyColumn(int start)
	{
		if (start > max)
			return -1;
		int n = getLowestEmpty(start);
		while (n != -1 && !isColumnClear(n))
			n = getLowestEmpty(n+1);
		return n;
	}
	
	// returns the smaller of the two columns
	private static int getLowestAdjacentColumns(int start)
	{
		if (start >= max)
			return -1;
		int n = getLowestEmptyColumn(start);
		while (n != -1 && (isLastColumn(n) || !isColumnClear(n+1)))
			n = getLowestEmptyColumn(n+1);
		return n;		
	}
}
