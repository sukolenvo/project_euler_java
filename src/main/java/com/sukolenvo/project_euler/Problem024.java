package com.sukolenvo.project_euler;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Problem024 {

  long run() {
    int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    for (int i = 1; i < 1_000_000; i++) {
      mutate(digits);
    }
    String result = Arrays.stream(digits)
        .mapToObj(String::valueOf)
        .collect(Collectors.joining(""));
    return Long.parseLong(result);
  }

  private void mutate(int[] digits) {
    for (int i = digits.length - 2; i >= 0; i--) {
      if (digits[i] < digits[i + 1]) {
        for (int j = digits.length - 1; j > i; j--) {
          if (digits[j] > digits[i]) {
            digits[i] ^= digits[j];
            digits[j] ^= digits[i];
            digits[i] ^= digits[j];
            Arrays.sort(digits, i + 1, digits.length);
            return;
          }
        }
      }
    }
  }
}
