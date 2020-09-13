package com.sukolenvo.project_euler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Problem044 {

  final List<Long> pentagonalNumbers = new ArrayList<>();

  long run() {
    Optional<Long> result = Optional.empty();
    for (int j = 1; ; j++) {
      long first = getPentagonNumber(j);
      if (result.isPresent() && getPentagonNumber(j + 1) - first > result.get()) {
        break;
      }
      for (int k = j + 1; ; k++) {
        long second = getPentagonNumber(k);
        if (isPentagonNumber(first + second) && isPentagonNumber(second - first)) {
          if (result.isEmpty() || result.get() < second - first) {
            result = Optional.of(second - first);
            break;
          }
        }
        if (getPentagonNumber(k + 1) - second > first) {
          break;
        }
        if (result.isPresent() && second - first > result.get()) {
          break;
        }
      }
    }
    return result.get();
  }

  private long getPentagonNumber(int term) {
    for (long i = pentagonalNumbers.size(); i < term; i++) {
      pentagonalNumbers.add(calcPentagonNumber(i + 1));
    }
    return pentagonalNumbers.get(term - 1);
  }

  private long calcPentagonNumber(long term) {
    return term * (3 * term - 1) / 2;
  }

  private boolean isPentagonNumber(long number) {
    if (number < 1) {
      return false;
    }
    while (pentagonalNumbers.get(pentagonalNumbers.size() - 1) < number) {
      pentagonalNumbers.add(calcPentagonNumber(pentagonalNumbers.size() + 1));
    }
    return Collections.binarySearch(pentagonalNumbers, number) >= 0;
  }
}
