package com.sukolenvo.project_euler;

import java.math.BigInteger;

public class Problem048 {

  long run() {
    BigInteger sum = BigInteger.ZERO;
    for (int i = 1; i <= 1000; i++) {
      sum = sum.add(BigInteger.valueOf(i).pow(i));
    }
    String result = sum.toString();
    return Long.parseLong(result.substring(result.length() - 10));
  }
}
