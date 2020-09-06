package com.sukolenvo.project_euler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Problem023 {

  long run() {
    Set<Integer> abundantNumbers = new TreeSet<>();
    for (int i = 1; i < 28123; i++) {
      if (Common.divisorsSum(i) > i) {
        abundantNumbers.add(i);
      }
    }
    long result = 0;
    for (int i = 1; i < 28123; i++) {
      if (!isSumOfTwo(i, abundantNumbers)) {
        result += i;
      }
    }
    return result;
  }

  boolean isSumOfTwo(int number, Set<Integer> abundantNumbers) {
    for (int abundantNumber : abundantNumbers) {
      if (abundantNumber > number / 2) {
        return false;
      }
      if (abundantNumbers.contains(number - abundantNumber)) {
        return true;
      }
    }
    return false;
  }
}
