package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem110 {

  BigInteger run(int goal) {
    int primeCount = (int) Math.ceil(Math.log(goal) / Math.log(2));
    int[] primes = new int[primeCount];
    for (int i = 2, found = 0; found < primeCount; i++) {
      if (BigInteger.valueOf(i).isProbablePrime(100)) {
        primes[found++] = i;
      }
    }
    BigInteger result = Arrays.stream(primes).mapToObj(BigInteger::valueOf).reduce(BigInteger.ONE, BigInteger::multiply);
    int[] maxFactor = new int[primeCount];
    for (int i = 0; i < primeCount; i++) {
      maxFactor[i] = (int) Math.ceil(Math.log(primes[primeCount - 1]) / Math.log(primes[i]));
    }
    int check = Arrays.stream(maxFactor).reduce(1, (a, b) -> a * b);
    System.out.println("Total numbers to check: " + check);
    for (int i = 1; i < check; i++) {
      Map<Integer, Integer> factors = asFactors(i, primes, maxFactor);
      if (countFractions(factors) > goal) {
        BigInteger n = factors.entrySet().stream()
            .map(e -> BigInteger.valueOf(e.getKey()).pow(e.getValue()))
            .reduce(BigInteger.ONE, BigInteger::multiply);
        if (n.compareTo(result) < 0) {
          result = n;
        }
      }
    }
    return result;
  }

  private Map<Integer, Integer> asFactors(int number, int[] primes, int[] maxFactor) {
    Map<Integer, Integer> result = new HashMap<>();
    int index = 0;
    while (number > 0) {
      int factor = number % maxFactor[index];
      result.put(primes[index], factor);
      number /= maxFactor[index];
      index++;
    }
    return result;
  }

  private int countFractions(Map<Integer, Integer> primes) {
    int divisors = primes.values().stream()
        .mapToInt(p -> 2 * p + 1)
        .reduce(1, (a, b) -> a * b);
    return (divisors + 1) / 2;
  }
}
