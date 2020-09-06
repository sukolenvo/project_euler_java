package com.sukolenvo.project_euler;

import java.math.BigInteger;

public class Problem020 {

  int run() {
    BigInteger factorial = BigInteger.ONE;
    for (int i = 2; i <= 100; i++) {
      factorial = factorial.multiply(BigInteger.valueOf(i));
    }
    int result = 0;
    for (char digit : factorial.toString().toCharArray()) {
      result += digit - '0';
    }
    return result;
  }
}
