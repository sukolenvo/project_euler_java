package com.sukolenvo.project_euler;

import java.util.HashSet;
import java.util.Set;

public class Problem073 {

  int run() {
    Fraction half = new Fraction(1, 2);
    Fraction third = new Fraction(1, 3);
    Set<Fraction> result = new HashSet<>();
    for (int i = 2; i <= 12000; i++) {
      for (int j = 1; j < i; j++) {
        Fraction fraction = new Fraction(j, i);
        if (fraction.compareTo(third) <= 0) {
          continue;
        }
        if (fraction.compareTo(half) >= 0) {
          break;
        }
        result.add(fraction.normalise());
      }
    }
    return result.size();
  }
}
