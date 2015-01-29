package org.codeexample.algorithms.collected.misc;

public class SumFromDices1 
{
 
    public static void main(String[] args) 
    {
        int N = 5;
        int diceValues[] = new int[N];
        System.out.println ("Num solutions: "  + sFromN(N,6,20,diceValues) + ", callCount="+callCount);
    }
 
    static long callCount=0;
    static int sFromN(int N, int A, int S, int []diceValues)
    {
        callCount++;
 
        if (N<=0)
            return 0;
 
        if (N==1)
        {
            if (S>=1 && S<=A)
            {
                diceValues[0] = S;
                for (int i : diceValues)
                    System.out.print(i+", ");
                System.out.println();
                return 1;
            }
            else
                return 0;
        }
 
        int numSolutions = 0;
        for(int i=1; i<=A; i++)
        {
            diceValues[N-1] = i;
            numSolutions += sFromN(N-1, A, S-i, diceValues);
        }
 
        return numSolutions;
    }
}