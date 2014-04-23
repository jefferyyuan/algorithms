package org.codeexample.algorithms.collected.mit6006.newton;

/**
 * From: http://www.ugrad.math.ubc.ca/Flat/newton-code.html <br/>
 * 
 */
class Newton {

  static double f(double x) {
    return Math.sin(x);
  }

  static double fprime(double x) {
    return Math.cos(x);
  }

  public static void main(String argv[]) {
    argv = new String[] { "20" };
    double tolerance = .000000001; // Our approximation of zero
    int max_count = 2000; // Maximum number of Newton's method iterations

    /*
     * x is our current guess. If no command line guess is given, we take 0 as
     * our starting point.
     */

    double x = 0;

    if (argv.length == 1) {
      x = Double.valueOf(argv[0]).doubleValue();
    }

    for (int count = 1; (Math.abs(f(x)) > tolerance) && (count < max_count); count++) {
      x = x - f(x) / fprime(x);
      System.out.println("Step: " + count + " x:" + x + " Value:" + f(x));
    }

    if (Math.abs(f(x)) <= tolerance) {
      System.out.println("Zero found at x=" + x);
    } else {
      System.out.println("Failed to find a zero");
    }
  }

}