package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.Set;

public class Problem049 {

  long run() {
    Set<Integer>[] primes = new Set[10_000];
    for (int i = 1001; i <= 9999; i += 2) {
      if (BigInteger.valueOf(i).isProbablePrime(i)) {
        primes[i] = Common.getDigits(i);
      }
    }
    for (int i = 1001; i <= 9999; i += 2) {
      if (primes[i] == null) {
        continue;
      }
      for (int j = i + 2; j <= 9999; j += 2) {
        if (i == 1487 && j == 4817) {
          continue;
        }
        int next = j + j - i;
        if (next >= 10_000) {
          break;
        }
        if (primes[j] == null) {
          continue;
        }
        if (!primes[j].equals(primes[i])) {
          continue;
        }
        if (primes[i].equals(primes[next])) {
          return (long) i * 10000 * 10000 + j * 10000 + j + j - i;
        }
      }
    }
    throw new IllegalStateException("not found");
  }
}
