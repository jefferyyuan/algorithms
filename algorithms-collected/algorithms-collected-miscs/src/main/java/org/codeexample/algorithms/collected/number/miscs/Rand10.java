package org.codeexample.algorithms.collected.number.miscs;

public class Rand10
{
    public static int rand10Imp()
    {
        int a, b, idx;
        while (true)
        {
            a = rand7();
            b = rand7();
            idx = b + (a - 1) * 7;
            if (idx <= 40)
                return 1 + (idx - 1) % 10;
            a = idx - 40;
            b = rand7();
            // get uniform dist from 1 - 63
            idx = b + (a - 1) * 7;
            if (idx <= 60)
                return 1 + (idx - 1) % 10;
            a = idx - 60;
            b = rand7();
            // get uniform dist from 1-21
            idx = b + (a - 1) * 7;
            if (idx <= 20)
                return 1 + (idx - 1) % 10;
        }
    }

    private static int rand7()
    {
        // TODO Auto-generated method stub
        return 0;
    }
}
