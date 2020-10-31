package com.sukolenvo.project_euler;

import java.math.BigInteger;

public class Problem055 {

  int run() {
    int result = 0;
    for (int i = 1; i < 10000; i++) {
      if (isLichrel(i)) {
        result++;
      }
    }
    return result;
  }

  boolean isLichrel(int number) {
    return getStepsToPalindrom(BigInteger.valueOf(number), 0) >= 50;
  }

  private int getStepsToPalindrom(BigInteger number, int iteration) {
    if (iteration >= 50) {
      return 50;
    }
    BigInteger next = reverseAndAdd(number);
    if (isPalindrom(next)) {
      return iteration + 1;
    }
    return getStepsToPalindrom(next, iteration + 1);
  }

  boolean isPalindrom(BigInteger number) {
    byte[] digits = number.toString().getBytes();
    for (int i = 0; i < digits.length / 2; i++) {
      if (digits[i] != digits[digits.length - i - 1]) {
        return false;
      }
    }
    return true;
  }

  private BigInteger reverseAndAdd(BigInteger number) {
    return number.add(new BigInteger(new StringBuilder(number.toString()).reverse().toString()));
  }
}
