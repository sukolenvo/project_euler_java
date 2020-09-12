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
      Set<Integer> multiplicandDigits = Common.getDigits(i);
      if (multiplicandDigits.contains(0)) {
        continue;
      }
      int multiplicandDigitsCount = Common.getDigitCount(i);
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
        int multiplierDigitsCount = Common.getDigitCount(j);
        int product = i * j;
        int productDigitsCount = Common.getDigitCount(product);
        if (multiplicandDigitsCount + multiplierDigitsCount + productDigitsCount < 9) {
          continue;
        }
        if (multiplicandDigitsCount + multiplierDigitsCount + productDigitsCount > 9) {
          break;
        }
        Set<Integer> multiplierDigits = Common.getDigits(j);
        if (multiplierDigits.size() != multiplierDigitsCount) {
          continue;
        }
        if (multiplierDigits.contains(0)) {
          continue;
        }
        if (multiplierDigits.stream().anyMatch(multiplicandDigits::contains)) {
          continue;
        }
        Set<Integer> productDigits = Common.getDigits(product);
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
}
