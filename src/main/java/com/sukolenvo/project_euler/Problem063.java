package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Problem063 {

  int run() {
    Set<BigInteger> results = new HashSet<>();
    for (int i = 1; i < 10; i++) {
      for (int j = 1; ; j++) {
        BigInteger candidate = BigInteger.valueOf(i).pow(j);
        int digitCount = candidate.toString().length();
        if (digitCount < j) {
          break;
        }
        if (digitCount == j) {
          results.add(candidate);
        }
      }
    }
    return results.size();
  }
}
