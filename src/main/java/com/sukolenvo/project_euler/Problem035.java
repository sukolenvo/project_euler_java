package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.List;

public class Problem035 {

  int run() {
    int result = 13;
    for (int i = 101; i < 1_000_000; i += 2) {
      List<Integer> digits = Common.getDigitsList(i);
      if (digits.stream().anyMatch(digit -> digit % 2 == 0)) {
        continue;
      }
      if (BigInteger.valueOf(i).isProbablePrime(100) && isCircularPrime(digits)) {
        result++;
      }
    }
    return result;
  }

  private boolean isCircularPrime(List<Integer> digits) {
    for (int j = 1; j <= digits.size(); j++) {
      int temp = 0;
      for (int k = 0; k < digits.size(); k++) {
        temp += Math.pow(10, (k + j) % digits.size()) * digits.get(k);
      }
      if (!BigInteger.valueOf(temp).isProbablePrime(100)) {
        return false;
      }
    }
    return true;
  }
}
