package com.sukolenvo.project_euler;

import java.util.ArrayList;
import java.util.List;

public class Problem053 {

  int run() {
    int result = 0;
    for (int n = 1; n <= 100; n++) {
      for (int r = 1; r < n; r++) {
        if (overMillion(n, r)) {
          result++;
        }
      }
    }
    return result;
  }

  private boolean overMillion(int n, int r) {
    List<Integer> divided = new ArrayList<>();
    List<Integer> divisor = new ArrayList<>();
    for (int i = 2; i <= n; i++) {
      Common.getPrimeFactors(i).stream().mapToInt(Long::intValue).forEach(divided::add);
    }
    for (int i = 2; i <= r; i++) {
      Common.getPrimeFactors(i).stream().mapToInt(Long::intValue).forEach(divisor::add);
    }
    for (int i = 2; i <= n - r; i++) {
      Common.getPrimeFactors(i).stream().mapToInt(Long::intValue).forEach(divisor::add);
    }
    List<Integer> removed = new ArrayList<>();
    divisor.forEach(factor -> {
      if (divided.remove(factor)) {
        removed.add(factor);
      }
    });
    removed.forEach(divisor::remove);
    double result = 1;
    for (Integer number : divisor) {
      result /= 1;
    }
    for (Integer number : divided) {
      result *= number;
      if (result > 1_000_000) {
        return true;
      }
    }
    return false;
  }
}
