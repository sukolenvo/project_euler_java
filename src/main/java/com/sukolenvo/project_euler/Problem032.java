package com.sukolenvo.project_euler;

import java.util.HashSet;
import java.util.Set;

public class Problem032 {

  long run() {
    Set<Integer> pandigitalNumbers = new HashSet<>();
    for (int i = 0; i < 100; i++) {
      if (i % 5 == 0) {
        continue;
      }
      if (i % 10 == 1) {
        continue;
      }
      Set<Integer> multiplicandDigits = getDigits(i);
      if (multiplicandDigits.contains(0)) {
        continue;
      }
      int multiplicandDigitsCount = getDigitCount(i);
      if (multiplicandDigits.size() != multiplicandDigitsCount) {
        continue;
      }
      for (int j = i + 1; ; j++) {
        if (j % 5 == 0) {
          continue;
        }
        if (j % 10 == 1) {
          continue;
        }
        int multiplierDigitsCount = getDigitCount(j);
        int product = i * j;
        int productDigitsCount = getDigitCount(product);
        if (multiplicandDigitsCount + multiplierDigitsCount + productDigitsCount < 9) {
          continue;
        }
        if (multiplicandDigitsCount + multiplierDigitsCount + productDigitsCount > 9) {
          break;
        }
        Set<Integer> multiplierDigits = getDigits(j);
        if (multiplierDigits.size() != multiplierDigitsCount) {
          continue;
        }
        if (multiplierDigits.contains(0)) {
          continue;
        }
        if (multiplierDigits.stream().anyMatch(multiplicandDigits::contains)) {
          continue;
        }
        Set<Integer> productDigits = getDigits(product);
        if (productDigits.size() != productDigitsCount) {
          continue;
        }
        if (productDigits.contains(0)) {
          continue;
        }
        if (productDigits.stream().anyMatch(multiplierDigits::contains)
            || productDigits.stream().anyMatch(multiplicandDigits::contains)) {
          continue;
        }
        pandigitalNumbers.add(product);
      }
    }
    return pandigitalNumbers.stream()
        .mapToLong(Integer::longValue)
        .sum();
  }

  int getDigitCount(int i) {
    if (i < 10) {
      return 1;
    }
    if (i < 100) {
      return 2;
    }
    if (i < 1000) {
      return 3;
    }
    if (i < 10000) {
      return 4;
    }
    if (i < 100000) {
      return 5;
    }
    if (i < 1000000) {
      return 6;
    }
    return (int) Math.ceil(Math.log10(i));
  }

  Set<Integer> getDigits(int i) {
    Set<Integer> digits = new HashSet<>();
    while (i > 0) {
      digits.add(i % 10);
      i /= 10;
    }
    return digits;
  }
}
