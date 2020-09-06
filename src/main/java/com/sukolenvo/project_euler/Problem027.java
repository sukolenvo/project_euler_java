package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem027 {

  int run() {
    int result = -1;
    int maxPrimes = 0;
    List<Integer> possibleB = IntStream.range(2, 1000)
        .filter(b -> BigInteger.valueOf(b).isProbablePrime(100))
        .boxed()
        .collect(Collectors.toList());
    for (int a = -999; a < 1000; a++) {
      for (Integer b : possibleB) {
        int primes = getNumberOfConsequtivePrimes(a, b);
        if (maxPrimes < primes) {
          maxPrimes = primes;
          result = a * b;
        }
      }
    }
    return result;
  }

  private int getNumberOfConsequtivePrimes(int a, int b) {
    for (int i = 0; ; i++) {
      int value = i * i + i * a + b;
      if (!BigInteger.valueOf(value).isProbablePrime(100)) {
        return i + 1;
      }
    }
  }
}
