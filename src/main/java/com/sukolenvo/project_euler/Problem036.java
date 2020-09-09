package com.sukolenvo.project_euler;

import java.util.List;

public class Problem036 {

  long run() {
    long result = 0;
    for (int i = 1; i < 1_000_000; i += 2) {
      List<Integer> digitsList = Common.getDigitsList(i);
      if (isPalindrom(digitsList)
        && isPalindrom(Integer.toBinaryString(i).toCharArray())) {
        result += i;
      }
    }
    return result;
  }

  private boolean isPalindrom(List<Integer> digits) {
    for (int j = 0; j < digits.size() / 2; j++) {
      if (!digits.get(j).equals(digits.get(digits.size() - j - 1))) {
        return false;
      }
    }
    return true;
  }

  private boolean isPalindrom(char[] digits) {
    for (int j = 0; j < digits.length / 2; j++) {
      if (digits[j] != digits[digits.length - j - 1]) {
        return false;
      }
    }
    return true;
  }
}
