// ACSL Contest #4 - Pip - Senior Division
// Eric Ouyang

import java.util.Scanner;
import java.util.Stack;

public class Contest_4_Pip {
	
	private static final int numLines = 1;
	private static String[] input = new String[numLines];
	private static String[] output = new String[numLines];
	
	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in); 
		
		System.out.println("INPUT:");
		
		for (int i = 0; i < numLines; i++)
		{
			System.out.print("Infix expression " + (i + 1) + ": ");
			input[i] = kb.next();	
		}
		
		process();
		
		System.out.println("\nOUTPUT:");
		
		for (int i = 0; i < numLines; i++)
			System.out.println("Prefix expression " + (i + 1) + ": " + output[i]);
	}
	
	public static void process()
	{
		for (int i = 0; i < numLines; i++)
		{
			try
			{
				output[i] = convertToPrefix(input[i]);
			}
			catch (Exception e)
			{
				System.out.println("\nError converting line " + (i + 1) + " to prefix");
			}		
		}
	}
	
	public static String convertToPrefix(String s)
	{
		StringBuffer result = new StringBuffer();
		Stack<Character> output = new Stack<Character>();
		Stack<Character> operators = new Stack<Character>();
		
		for (int i = s.length() - 1; i >= 0; i--)
		{
			char c = s.charAt(i);
			
			if (isOperator(c))
			{
				if (!operators.empty() && operators.peek() == '*')
				{
					output.push(operators.pop());
				}
				operators.push(c);
			}
			
			else if (c == ')')
				operators.push(c);
			
			else if (c == '(')
			{
				while (!operators.empty() && operators.peek() != ')')
					output.push(operators.pop());
				operators.pop();
			}
				
			else
				output.push(c);
		}
		
		while(!operators.empty())
			output.push(operators.pop());
		
		while(!output.empty())
			result.append(output.pop());
		
		return result.toString();
	}
	
	public static boolean isOperator(char c)
	{
		return (c == '+' || c == '-' || c == '*' );
	}
	
}
