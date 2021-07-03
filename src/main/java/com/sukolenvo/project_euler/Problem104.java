package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Problem104 {

  int run() {
    BigInteger prev = BigInteger.ONE;
    BigInteger curr = BigInteger.ONE;
    long lastNinePrev = 1;
    long lastNineCurr = 1;
    for (int i = 3; ; i++) {
      long lastNineNext = (lastNineCurr + lastNinePrev) % 1_000_000_000;
      BigInteger next = curr.add(prev);
      if (checkLastNine(lastNineNext) && check(next)) {
        return i;
      }
      prev = curr;
      curr = next;
      lastNinePrev = lastNineCurr;
      lastNineCurr = lastNineNext;
    }
  }

  boolean checkLastNine(long lastNine) {
    Set<Object> digits = new HashSet<>(9, 1);
    for (int i = 0; i < 9; i++) {
      long digit = lastNine % 10;
      if (digit == 0) {
        return false;
      }
      if (!digits.add(digit)) {
        return false;
      }
      lastNine /= 10;
    }
    return true;
  }

  boolean check(BigInteger next) {
    String number = next.toString();
    if (number.length() < 9) {
      return false;
    }
    return number.chars()
        .limit(9)
        .filter(c -> c > '0')
        .distinct()
        .count() == 9;
  }
}
