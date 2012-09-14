package org.codeexample.algorithms.collected.number.foursum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such
 * that a + b + c + d = target? Find all unique quadruplets in the array which
 * gives the sum of target.
 * <p>
 * http://stackoverflow.com/questions/11216582/4sum-implementation-in-java-from-
 * leetcode
 * 
 */
public class FourSumSolution
{

    private static class SortedTuple implements Comparable<SortedTuple>
    {
        int[] values;

        public SortedTuple(int... values)
        {
            this.values = Arrays.copyOf(values, values.length);
            Arrays.sort(this.values);
        }

        public int size()
        {
            return values.length;
        }

        public int get(
                int index)
        {
            return values[index];
        }

        public ArrayList<Integer> asList()
        {
            // Result type is ArrayList and not the better List,
            // because of the external API of the outer class.
            ArrayList<Integer> list = new ArrayList<Integer>(values.length);
            for (int value : values)
                list.add(value);
            return list;
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(values);
            return result;
        }

        @Override
        public boolean equals(
                Object obj)
        {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            SortedTuple other = (SortedTuple) obj;
            if (!Arrays.equals(values, other.values))
                return false;
            return true;
        }

        @Override
        public int compareTo(
                SortedTuple other)
        {
            int c = cmp(values.length, other.values.length);
            if (c == 0)
            {
                for (int i = 0; i < values.length; ++i)
                {
                    c = cmp(values[i], other.values[i]);
                    if (c != 0)
                        break;
                }
            }
            return c;
        }

        @Override
        public String toString()
        {
            return Arrays.toString(values);
        }

        private static int cmp(
                int lhs, int rhs)
        {
            return lhs < rhs ? -1 : lhs > rhs ? 1 : 0; // Cannot overflow like
                                                       // lhs - rhs.
        }
    }

    public ArrayList<ArrayList<Integer>> fourSum(
            int[] num, int target)
    {
        final int nTerms = 4;
        SortedTuple values = new SortedTuple(num);
        SortedSet<SortedTuple> results = new TreeSet<SortedTuple>();
        int[] candidateTerms = new int[nTerms];
        int valuesCount = values.size();
        solveNSum(target, nTerms, values, results, candidateTerms, valuesCount);

        ArrayList<ArrayList<Integer>> aList = new ArrayList<ArrayList<Integer>>();
        for (SortedTuple solution : results)
        {
            aList.add(solution.asList());
        }
        return aList;
    }

    public static void main(
            String[] args)
    {
        final int[] num = { 1, 3, 1, 3, 4 };
        final int requiredSum = 4;
        final int nTerms = 2;

        SortedTuple values = new SortedTuple(num);
        SortedSet<SortedTuple> results = new TreeSet<SortedTuple>();
        int[] candidateTerms = new int[nTerms];
        int valuesCount = values.size();
        solveNSum(requiredSum, nTerms, values, results, candidateTerms, valuesCount);

        System.out.println("Solutions:");
        for (SortedTuple solution : results)
        {
            System.out.println("Solution: " + solution);
        }
        System.out.println("End of solutions.");
    }

    private static void solveNSum(
            int requiredSum, int nTerms, SortedTuple values, SortedSet<SortedTuple> results,
            int[] candidateTerms, int valuesCount)
    {
        if (nTerms <= 0)
        {
            if (requiredSum == 0)
                results.add(new SortedTuple(candidateTerms));
            return;
        }
        if (valuesCount <= 0)
        {
            return;
        }

        --valuesCount;
        int candidateTerm = values.get(valuesCount);

        // Try with candidate term:
        candidateTerms[nTerms - 1] = candidateTerm;
        solveNSum(requiredSum - candidateTerm, nTerms - 1, values, results, candidateTerms,
                valuesCount);

        // Try without candidate term:
        solveNSum(requiredSum, nTerms, values, results, candidateTerms, valuesCount);
    }
}
