package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem037 {

  int run() {
    boolean[] digitPrimes = {
        false,
        false,
        true,
        true,
        false,
        true,
        false,
        true,
        false,
        false
    };
    Set<Integer> result = new HashSet<>();
    for (int i = 11; i < 1_000_000; i += 2) {
      if (i % 10 == 1) {
        continue;
      }
      if (i % 10 == 9) {
        continue;
      }
      List<Integer> digits = Common.getDigitsList(i);
      if (!digitPrimes[digits.get(digits.size() - 1)]) {
        int skipLevel = (int) Math.pow(10, digits.size() - 1);
        i += skipLevel;
        i -= i % skipLevel + 1;
        continue;
      }
      boolean candidate = true;
      for (int j = digits.size() - 2; j > 0; j--) {
        if (digits.get(j) % 2 == 0) {
          int skipLevel = (int) Math.pow(10, j);
          i += skipLevel;
          i -= i % skipLevel + 1;
          candidate = false;
          break;
        }
      }
      if (!candidate) {
        continue;
      }
      if (!BigInteger.valueOf(i).isProbablePrime(100)) {
        continue;
      }
      if (isTruncatablePrime(digits)) {
        result.add(i);
      }
    }
    if (result.size() != 11) {
      throw new IllegalStateException("Expecting 11 items " + result);
    }
    return result.stream().mapToInt(Integer::intValue).sum();
  }

  boolean isTruncatablePrime(List<Integer> digits) {
    for (int i = 1; i < digits.size() - 1; i++) {
      if (!isPrime(digits.subList(i, digits.size()))) {
        return false;
      }
    }
    for (int i = digits.size() - 1; i > 1; i--) {
      if (!isPrime(digits.subList(0, i))) {
        return false;
      }
    }
    return true;
  }

  private boolean isPrime(List<Integer> digits) {
    int number = 0;
    for (int i = 0; i < digits.size(); i++) {
      number += digits.get(i) * Math.pow(10, i);
    }
    return BigInteger.valueOf(number).isProbablePrime(100);
  }

}
