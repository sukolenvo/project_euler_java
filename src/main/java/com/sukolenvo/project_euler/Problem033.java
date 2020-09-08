package com.sukolenvo.project_euler;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Problem033 {

  int run() {
    Set<Fraction> results = new HashSet<>();
    for (int numerator = 10; numerator < 100; numerator++) {
      if (numerator % 10 == 0) {
        continue;
      }
      Set<Integer> numeratorDigits = Common.getDigits(numerator);
      if (numeratorDigits.size() != 2) {
        continue;
      }
      for (int denominator = numerator + 1; denominator < 100; denominator++) {
        if (denominator % 10 == 0) {
          continue;
        }
        Set<Integer> denominatorDigits = Common.getDigits(denominator);
        if (denominatorDigits.size() != 2) {
          continue;
        }
        if (numeratorDigits.equals(denominatorDigits)) {
          continue;
        }
        if (numeratorDigits.stream().anyMatch(denominatorDigits::contains)) {
          int naiveNumerator = numeratorDigits.stream()
              .filter(Predicate.not(denominatorDigits::contains))
              .findAny()
              .orElseThrow();
          int naiveDenominator = denominatorDigits.stream()
              .filter(Predicate.not(numeratorDigits::contains))
              .findAny()
              .orElseThrow();
          if (new Fraction(numerator, denominator).normalise()
              .equals(new Fraction(naiveNumerator, naiveDenominator).normalise())) {
            results.add(new Fraction(numerator, denominator));
          }
        }
      }
    }
    if (results.size() != 4) {
      throw new IllegalStateException("Expecting 4fractions but got " + results);
    }
    return results.stream()
        .reduce(new Fraction(1, 1), Fraction::mul)
        .normalise()
        .getDenominator();
  }
}
