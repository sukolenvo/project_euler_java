package com.sukolenvo.project_euler;

import java.math.BigInteger;

public class Problem069 {

  int run(int n) {
    int result = 1;
    for (int i = 2; result * i < n; i++) {
      if (BigInteger.valueOf(i).isProbablePrime(100)) {
        result *= i;
      }
    }
    return result;
  }
}
