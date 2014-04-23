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

import java.util.*;//arrays copy
import javax.swing.JOptionPane;

public class AtkinsSieve {

    public static void main(String[] args) {
        int[] primes;
        String arraySizeStr;
        int arraySize;
        arraySizeStr
                = JOptionPane.showInputDialog("Enter a number to find all the prime"
                        + " numbers up to that number.");
        arraySize = Integer.parseInt(arraySizeStr);
        primes = AtkinSieve.findPrimes(arraySize);
        for (int prime : primes) {
            System.out.print(prime + " ");
        }
        System.out.println();
        System.out.print("DONE!");
    }

    static public class AtkinSieve {

        public static int[] findPrimes(int arraySize) {
            boolean[] isPrime = new boolean[arraySize + 1];
            double sqrt = Math.sqrt(arraySize);

            for (int x = 1; x <= sqrt; x++) {
                for (int y = 1; y <= sqrt; y++) {
                    int n = 4 * x * x + y * y;
                    if (n <= arraySize && (n % 12 == 1 || n % 12 == 5)) {
                        isPrime[n] = !isPrime[n];
                    }

                    n = 3 * x * x + y * y;
                    if (n <= arraySize && (n % 12 == 7)) {
                        isPrime[n] = !isPrime[n];
                    }

                    n = 3 * x * x - y * y;
                    if (x > y && (n <= arraySize) && (n % 12 == 11)) {
                        isPrime[n] = !isPrime[n];
                    }
                }
            }
            for (int n = 5; n <= sqrt; n++) {
                if (isPrime[n]) {
                    int s = n * n;
                    for (int k = s; k <= arraySize; k += s) {
                        isPrime[k] = false;
                    }
                }
            }
            int[] primes = new int[arraySize];
            int found = 0;
            if (arraySize > 2) {
                primes[found++] = 2;
            }
            if (arraySize > 3) {
                primes[found++] = 3;
            }
            for (int n = 5; n <= arraySize; n += 2) {
                if (isPrime[n]) {
                    primes[found++] = n;
                }
            }
            return Arrays.copyOf(primes, found);
        }
    }
}
