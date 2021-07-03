package com.sukolenvo.project_euler;

import java.util.HashSet;
import java.util.Set;

public class Problem095 {

  int[] nextMembers = new int[1_000_001];

  {
    for (int i = 1; i < nextMembers.length; i++) {
      nextMembers[i] = Common.divisorsSum(i);
    }
  }

  int run() {
    int maxChainLength = 0;
    int result = 0;
    for (int i = 10; i < nextMembers.length; i++) {
      Set<Integer> chain = getChain(i);
      if (chain.size() > maxChainLength) {
        maxChainLength = chain.size();
        result = chain.stream().mapToInt(Integer::intValue).min().orElseThrow();
      }
    }
    return result;
  }

  Set<Integer> getChain(int start) {
    Set<Integer> chain = new HashSet<>();
    int next = start;
    while (true) {
      if (next > 1_000_000) {
        return Set.of();
      }
      if (!chain.add(next)) {
        return next == start ? chain : Set.of();
      }
      next = nextMembers[next];
    }
  }
}
