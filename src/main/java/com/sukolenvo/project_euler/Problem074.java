package com.sukolenvo.project_euler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Problem074 {

  int[] factorials = IntStream.range(0, 10)
      .map(i -> (int) Common.getFactorial(i))
      .toArray();

  int run() {
    int result = 0;
    for (int i = 0; i < 1_000_000; i++) {
      if (getChain(i).size() == 60) {
        result++;
      }
    }
    return result;
  }

  private Set<Integer> getChain(int head) {
    Set<Integer> result = new HashSet<>();
    result.add(head);
    int chain = head;
    while (true) {
      List<Integer> digits = Common.getDigitsList(chain);
      int next = digits.stream().mapToInt(digit -> factorials[digit]).sum();
      if (!result.add(next)) {
        break;
      }
      chain = next;
    }
    return result;
  }
}
