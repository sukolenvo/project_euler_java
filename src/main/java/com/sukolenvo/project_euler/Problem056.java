package com.sukolenvo.project_euler;

import java.math.BigInteger;

public class Problem056 {

  int run() {
    int maxSum = 0;
    for (int a = 1; a < 100; a++) {
      for (int b = 1; b < 100; b++) {
        maxSum = Math.max(maxSum, getDigitSum(a, b));
      }
    }
    return maxSum;
  }

  private int getDigitSum(int a, int b) {
    int sum = 0;
    for (char digit : BigInteger.valueOf(a).pow(b).toString().toCharArray()) {
      sum += digit - '0';
    }
    return sum;
  }
}
