//ACSL Contest #1 - Draft Picks - Senior Division
//Eric Ouyang

import java.util.Arrays;
import java.util.Scanner;

public class DraftPicks
{
  //input
  static int[] length = new int[10];
  static double[] value = new double[10];
  static double[] guaranteedMoney = new double[10];
  
  //calculated
  static double[] salaryPerGame = new double[10];
  static double[] expectedValue = new double[10];
  static double[] salaryPerYear = new double[10];
  
  public static void main(String[] args)
  {
    Scanner kb = new Scanner(System.in);
    
    //get data
    for(int i = 0; i < 10; i++)
    {
      System.out.println("Player " + (i+1) +":");
      System.out.print("Contract Length:");
      length[i] = kb.nextInt();
      System.out.print("Contract Value (in millions of dollars):");
      value[i] = kb.nextDouble();
      System.out.print("Guaranteed Money (in millions of dollars):");
      guaranteedMoney[i] = kb.nextDouble();
    }
   
    //calc and display requested stats
    
    //part 1
    calcSalaryPerGame(16);
    System.out.println("1: " + (int)(calcRangeSalaryPerGame() + 0.5));
    
    //part 2
    calcSalaryPerGame(18);
    System.out.println("2: " + (int)(calcMidRangeSalaryPerGame() + 0.5));
    
    //part 3
    calcExpectedValue(16);
    dispHighestExpected();
    
    //part 4
    calcExpectedValue(18);
    System.out.println("4: " + (int)(calcAvgExpected() + 0.5));
    
    //part 5
    calcSalaryPerYear();
    System.out.println("5: " + (int)(calcMedianAnnual() + 0.5));
  }
  
  public static void calcSalaryPerGame(int games)
  {
    for(int i = 0; i < 10; i++)
    {
      salaryPerGame[i] = value[i] / (length[i] * games) * 1000000;
    }
  }
  
  public static void calcSalaryPerYear()
  {
    for(int i = 0; i < 10; i++)
    {
      salaryPerYear[i] = value[i] / length[i] * 1000000;
    }
  }
  
  public static void calcExpectedValue(int games)
  { 
    double injuryProb = 0;
    for(int i = 0; i < 10; i++)
    {
      if(games == 16) injuryProb = length[i] * 0.03;
      if(games == 18) injuryProb = length[i] * 0.03375;
      expectedValue[i] = (value[i] * (1 - injuryProb) + guaranteedMoney[i]*injuryProb) * 1000000;
    }
  }
  
  //Part 1
  public static double calcRangeSalaryPerGame()
  {
    double[] sorted = Arrays.copyOf(salaryPerGame, 10);
    Arrays.sort(sorted);
    return sorted[9]-sorted[0];
  }
  
  //Part 2
  public static double calcMidRangeSalaryPerGame()
  {
    double[] sorted = Arrays.copyOf(salaryPerGame, 10);
    Arrays.sort(sorted);
    return (sorted[9]+sorted[0])/2;
  }
  
  //Part 3
  public static void dispHighestExpected()
  {
    double[] sorted = Arrays.copyOf(expectedValue, 10);
    Arrays.sort(sorted);
    int playerNum = 0;
    for (int i=0; i<10; i++)
    {
      if (sorted[9] == expectedValue[i]) playerNum = i + 1;
    }
    System.out.println("3: " + (int)( sorted[9] + 0.5) + " by player " + playerNum);
  }
  
  //Part 4
  public static double calcAvgExpected()
  {
    double total = 0;
    for (double i : expectedValue)
    {
      total += i;
    }  
    return total/10.0;
  }
  
  //Part 5
  public static double calcMedianAnnual()
  {
    double[] sorted = Arrays.copyOf(salaryPerYear, 10);
    Arrays.sort(sorted);
    return (sorted[4]+sorted[5])/2;
  }
}
