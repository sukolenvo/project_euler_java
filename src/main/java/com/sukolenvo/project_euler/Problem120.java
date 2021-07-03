package com.sukolenvo.project_euler;

import java.math.BigInteger;

public class Problem120 {

  long run() {
    long result = 0;
    for (int i = 3; i <= 1000; i++) {
      result += findMaxR(i);
    }
    return result;
  }

  private long findMaxR(int i) {
    long result = 0;
    for (int j = 1; j < 2 * i; j++) {
      long reminder = BigInteger.valueOf(i - 1).pow(j)
          .add(BigInteger.valueOf(i + 1).pow(j))
          .mod(BigInteger.valueOf(i * i)).longValue();
      result = Math.max(result, reminder);
    }
    return result;
  }
}
