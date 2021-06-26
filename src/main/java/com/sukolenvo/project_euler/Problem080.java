package com.sukolenvo.project_euler;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.stream.IntStream;

public class Problem080 {

  int run() {
    return IntStream.range(2, 100)
        .map(this::getDigitsSum)
        .sum();
  }

  int getDigitsSum(int number) {
    if ((int) Math.sqrt(number) * (int) Math.sqrt(number) == number) {
      return 0;
    }
    String digits = BigDecimal.valueOf(number).sqrt(new MathContext(100, RoundingMode.DOWN)).toString();
    return digits.chars()
        .filter(c -> c != '.')
        .map(c -> c - '0')
        .sum();
  }
}
