package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class Problem065 {

  int run() {
    BigInteger numerator = BigInteger.ZERO;
    BigInteger denominator = BigInteger.ONE;
    BigInteger tmp = null;
    for (int i = 33; i > 0; i--) {
      tmp = denominator;
      denominator = numerator.add(denominator);
      numerator = tmp;

      tmp = denominator;
      denominator = numerator.add(denominator.multiply(BigInteger.valueOf(i * 2)));
      numerator = tmp;

      tmp = denominator;
      denominator = numerator.add(denominator);
      numerator = tmp;
    }
    numerator = numerator.add(denominator.multiply(BigInteger.TWO));
    numerator = normaliseNumerator(numerator, denominator);

    return numerator.toString().chars()
        .map(c -> c - '0')
        .sum();
  }

  public BigInteger normaliseNumerator(BigInteger numerator, BigInteger denominator) {
    List<Long> numeratorFactors = Common.getPrimeFactors(numerator);
    List<Long> denominatorFactors = Common.getPrimeFactors(denominator);
    List<Long> matched = numeratorFactors.stream()
        .filter(denominatorFactors::contains)
        .distinct()
        .collect(Collectors.toList());
    if (matched.isEmpty()) {
      return numerator;
    }
    BigInteger normalisedNumerator = numerator;
    BigInteger normalisedDenominator = denominator;
    while (!matched.isEmpty()) {
      BigInteger divider = BigInteger.valueOf(matched.remove(0));
      while (normalisedNumerator.mod(divider).equals(BigInteger.ZERO)
          && normalisedDenominator.mod(divider).equals(BigInteger.ZERO)) {
        normalisedNumerator = normalisedNumerator.divide(divider);
        normalisedDenominator = normalisedDenominator.divide(divider);
      }
    }
    return normalisedNumerator;
  }
}
