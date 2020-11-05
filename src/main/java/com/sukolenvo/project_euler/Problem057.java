package com.sukolenvo.project_euler;

import java.math.BigInteger;

public class Problem057 {

  int run(int iteration) {
    int result = 0;
    BigInteger numerator = BigInteger.ONE;
    BigInteger denominator = BigInteger.TWO;
    for (int i = 1; i < iteration; i++) {
      numerator = numerator.add(denominator.multiply(BigInteger.TWO));
      var temp = numerator;
      numerator = denominator;
      denominator = temp;
      if (numerator.add(denominator).toString().length() > denominator.toString().length()) {
        result++;
      }
    }
    return result;
  }
}
