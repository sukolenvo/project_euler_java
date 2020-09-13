package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Problem046 {

  private Set<Integer> primeNumbers = new HashSet<>();

  int run() {
    for (int i = 3; ; i += 2) {
      if (BigInteger.valueOf(i).isProbablePrime(100)) {
        primeNumbers.add(i);
      } else {
        boolean supportRule = false;
        for (int prime : primeNumbers) {
          int squire = (i - prime) / 2;
          int c = (int) Math.sqrt(squire);
          if (squire == c * c) {
            supportRule = true;
            break;
          }
        }
        if (!supportRule) {
          return i;
        }
      }
    }
  }
}
