package com.sukolenvo.project_euler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.experimental.Accessors;

public class Problem064 {

  int run() {
    int result = 0;
    for (int i = 2; i <= 10_000; i++) {
      if (getPeriod(i).size() % 2 == 1) {
        result++;
      }
    }
    return result;
  }

  List<Integer> getPeriod(int i) {
    int mod = (int) Math.sqrt(i);
    if (mod * mod == i) {
      return List.of();
    }
    List<ContinuedFraction> result = new ArrayList<>();
    int d = mod;
    int n = 1; // n / (sqrt(i) - d)
    int a;
    while (true) {
      assert (i - d * d) % n == 0;
      int nextN = (i - d * d) / n;
      a = (mod + d) / nextN;
      d = a * nextN - d;
      n = nextN;
      ContinuedFraction next = new ContinuedFraction()
          .setA(a)
          .setD(d)
          .setN(n);
      Optional<ContinuedFraction> cycleStart = result.stream()
          .filter(fraction -> fraction.n == next.n && fraction.d == next.d)
          .findAny();
      if (cycleStart.isPresent()) {
        return result.stream()
            .skip(result.indexOf(cycleStart.get()))
            .map(it -> it.a)
            .collect(Collectors.toList());
      }
      result.add(next);
    }
  }

  /**
   * a + n / sqrt(i) - d
   */
  @Data
  @Accessors(chain = true)
  private static class ContinuedFraction {
    private int a;
    private int n;
    private int d;
  }
}
