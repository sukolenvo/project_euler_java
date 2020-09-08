package com.sukolenvo.project_euler;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class Fraction {

  private final int numerator;
  private final int denominator;

  public Fraction normalise() {
    List<Long> numeratorFactors = Common.getPrimeFactors(numerator);
    List<Long> denominatorFactors = Common.getPrimeFactors(denominator);
    List<Long> matched = numeratorFactors.stream()
        .filter(denominatorFactors::contains)
        .distinct()
        .collect(Collectors.toList());
    if (matched.isEmpty()) {
      return this;
    }
    int normalisedNumerator = numerator;
    int normalisedDenominator = denominator;
    while (!matched.isEmpty()) {
      long divider = matched.remove(0);
      while (normalisedNumerator % divider == 0 && normalisedDenominator % divider == 0) {
        normalisedNumerator /= divider;
        normalisedDenominator /= divider;
      }
    }
    return new Fraction(normalisedNumerator, normalisedDenominator);
  }

  public Fraction mul(Fraction other) {
    return new Fraction(numerator * other.numerator, denominator * other.denominator);
  }
}
