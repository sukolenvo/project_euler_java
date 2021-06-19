package com.sukolenvo.project_euler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem061 {

  int run() {
    Set<Integer> triangle = IntStream.range(1, 1000)
        .map(i -> i * (i + 1) / 2)
        .filter(i -> i >= 1000)
        .filter(i -> i < 10_000)
        .boxed()
        .collect(Collectors.toSet());
    Set<Integer> squire = IntStream.range(1, 1000)
        .map(i -> i * i)
        .filter(i -> i >= 1000)
        .filter(i -> i < 10_000)
        .boxed()
        .collect(Collectors.toSet());
    Set<Integer> pentagonal = IntStream.range(1, 1000)
        .map(i -> i * (3 * i - 1) / 2)
        .filter(i -> i >= 1000)
        .filter(i -> i < 10_000)
        .boxed()
        .collect(Collectors.toSet());
    Set<Integer> hexagonal = IntStream.range(1, 1000)
        .map(i -> i * (2 * i - 1))
        .filter(i -> i >= 1000)
        .filter(i -> i < 10_000)
        .boxed()
        .collect(Collectors.toSet());
    Set<Integer> heptagonal = IntStream.range(1, 1000)
        .map(i -> i * (5 * i - 3) / 2)
        .filter(i -> i >= 1000)
        .filter(i -> i < 10_000)
        .boxed()
        .collect(Collectors.toSet());
    Set<Integer> octagonal = IntStream.range(1, 1000)
        .map(i -> i * (3 * i - 2))
        .filter(i -> i >= 1000)
        .filter(i -> i < 10_000)
        .boxed()
        .collect(Collectors.toSet());
    for (Integer test : triangle) {
      int digits = test / 100;
      List<List<Integer>> chains = getChains(digits, List.of(squire, pentagonal, hexagonal, heptagonal, octagonal));
      for (List<Integer> chain : chains) {
        if (chain.contains(test)) {
          continue;
        }
        if (Set.copyOf(chain).size() != 5) {
          continue;
        }
        if (chain.get(0) / 100 == test % 100) {
          return chain.stream().mapToInt(Integer::intValue).sum() + test;
        }
      }
    }
    throw new RuntimeException("Failed to find chain");
  }

  private List<List<Integer>> getChains(int digits, List<Set<Integer>> groups) {
    List<List<Integer>> results = new ArrayList<>();
    for (Set<Integer> polygonalGroup : groups) {
      List<Set<Integer>> nextGroups = groups.stream().filter(group -> group != polygonalGroup).collect(Collectors.toList());
      for (Integer polygonal : polygonalGroup) {
        if (polygonal % 100 == digits) {
          if (nextGroups.isEmpty()) {
            List<Integer> chain = new ArrayList<>();
            chain.add(polygonal);
            results.add(chain);
          } else {
            int nextDigits = polygonal / 100;
            for (List<Integer> chain : getChains(nextDigits, nextGroups)) {
              chain.add(polygonal);
              results.add(chain);
            }
          }
        }
      }
    }
    return results;
  }


}
