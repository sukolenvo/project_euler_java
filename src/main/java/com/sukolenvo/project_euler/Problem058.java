package com.sukolenvo.project_euler;

import java.math.BigInteger;

public class Problem058 {

  int run(int primeRatio) {
    int primes = 0;
    int nonPrimes = 1;
    int counter = 1;
    for (int i = 2; ; i += 2) {
      if (counter < 0) {
        throw new IllegalStateException("Overflow");
      }
      counter += i;
      if (BigInteger.valueOf(counter).isProbablePrime(100)) {
        primes++;
      } else {
        nonPrimes++;
      }
      counter += i;
      if (BigInteger.valueOf(counter).isProbablePrime(100)) {
        primes++;
      } else {
        nonPrimes++;
      }
      counter += i;
      if (BigInteger.valueOf(counter).isProbablePrime(100)) {
        primes++;
      } else {
        nonPrimes++;
      }
      counter += i;
      if (BigInteger.valueOf(counter).isProbablePrime(100)) {
        primes++;
      } else {
        nonPrimes++;
      }
      if (100. * primes / (primes + nonPrimes) < primeRatio) {
        return 1 + i;
      }
    }
  }
}
