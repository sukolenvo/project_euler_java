package com.sukolenvo.project_euler;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class Fraction implements Comparable<Fraction> {

  private final long numerator;
  private final long denominator;

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
    long normalisedNumerator = numerator;
    long normalisedDenominator = denominator;
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

  public Fraction add(int number) {
    return new Fraction(numerator + number * denominator, denominator);
  }

  public Fraction add(Fraction other) {
    if (denominator == other.denominator) {
      return new Fraction(numerator + other.numerator, denominator);
    }
    long numerator = this.numerator * other.denominator + other.numerator * denominator;
    long denominator = this.denominator * other.denominator;
    return new Fraction(numerator, denominator);
  }

  @Override
  public int compareTo(Fraction o) {
    return Long.compare(numerator * o.denominator, o.numerator * denominator);
  }
}
