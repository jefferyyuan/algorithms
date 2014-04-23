/*
 * Copyright 2014 administrator.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codeexample.algorithms.collected.popular;

import java.util.BitSet;
import java.util.Scanner;

/**
 *
 * @author administrator
 */
public class Prime {

    public static void main(String[] args) {
        // get input integer
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter an integer from 2 to 1023");
        int input = scanner.nextInt();

        // perform Sieve of Eratosthenes  
        BitSet sieve = new BitSet(1024);
        int size = sieve.size();

        // set all bits from 2 to 1023
        for (int i = 2; i < size; i++) {
            sieve.set(i);
        }

        // perform Sieve of Eratosthenes
        int finalBit = (int) Math.sqrt(size);

        for (int i = 2; i < finalBit; i++) {
            if (sieve.get(i)) {
                for (int j = 2 * i; j < size; j += i) {
                    sieve.clear(j);
                }
            } // end if
        } // end for

        int counter = 0;

        // display prime numbers from 2 to 1023
        for (int i = 2; i < size; i++) {
            if (sieve.get(i)) {
                System.out.print(String.valueOf(i));
                System.out.print(++counter % 7 == 0 ? "\n" : "\t");
            } // end if
        } // end for

        // display result
        if (sieve.get(input)) {
            System.out.printf("\n%d is a prime number", input);
        } else {
            System.out.printf("\n%d is not a prime number", input);
        }
    }
}
