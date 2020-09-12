package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

public class Problem041 {

  int run() {
    for (int i = 987_654_321; ; i--) {
      List<Integer> digitsList = Common.getDigitsList(i);
      boolean skip = false;
      for (int j = digitsList.size() - 1; j > 0; j--) {
        if (digitsList.get(j) == 0) {
          i /= (int) Math.pow(10, j);
          i *= (int) Math.pow(10, j);
          skip = true;
          break;
        }
      }
      if (skip) {
        continue;
      }
      for (int j = 0; j < digitsList.size(); j++) {
        if (digitsList.get(j) == 0 || digitsList.lastIndexOf(digitsList.get(j)) != j) {
          i /= (int) Math.pow(10, j);
          i *= (int) Math.pow(10, j);
          skip = true;
          break;
        }
      }
      if (skip) {
        continue;
      }
      if (isPandigital(i) && BigInteger.valueOf(i).isProbablePrime(100)) {
        return i;
      }
    }
  }

  private boolean isPandigital(int number) {
    Set<Integer> digits = Common.getDigits(number);
    if (digits.size() != Common.getDigitCount(number)) {
      return false;
    }
    if (digits.contains(0)) {
      return false;
    }
    for (int i = 1; i <= digits.size(); i++) {
      if (!digits.contains(i)) {
        return false;
      }
    }
    return true;
  }
}
