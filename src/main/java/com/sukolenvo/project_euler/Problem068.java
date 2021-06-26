package com.sukolenvo.project_euler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem068 {

  private List<Long> solutions = new ArrayList<>();

  private List<List<Integer>> chains = List.of(
      List.of(0, 1, 2),
      List.of(3, 2, 4),
      List.of(5, 4, 6),
      List.of(7, 6, 8),
      List.of(9, 8, 1)
  );

  long run() {
    int[] ring = new int[10];
    List<Integer> numbers = IntStream.rangeClosed(1, 10)
        .boxed()
        .collect(Collectors.toList());
    generateSolutions(0, ring, numbers);
    return solutions.stream()
        .mapToLong(Long::longValue)
        .max()
        .orElseThrow();
  }

  private void generateSolutions(int index, int[] ring, List<Integer> numbers) {
    if (numbers.isEmpty()) {
      Integer firstChainIndex = IntStream.range(0, chains.size())
          .boxed()
          .min(Comparator.comparing(i -> ring[chains.get(i).get(0)]))
          .orElseThrow();
      StringBuilder solution = new StringBuilder();
      for (int i = 0; i < chains.size(); i++) {
        String solutionChain = chains.get((i + firstChainIndex) % chains.size()).stream()
            .map(j -> String.valueOf(ring[j]))
            .collect(Collectors.joining(""));
        solution.append(solutionChain);
      }
      if (solution.length() == 16) {
        solutions.add(Long.parseLong(solution.toString()));
      }
      return;
    }
    for (Integer candidate : numbers) {
      ring[index] = candidate;
      if (checkValid(ring)) {
        generateSolutions(index + 1, ring, numbers.stream().filter(i -> !i.equals(candidate)).collect(Collectors.toList()));
      }
    }
    ring[index] = 0;
  }

  private boolean checkValid(int[] ring) {
    int sum = 0;
    for (List<Integer> chain : chains) {
      if (chain.stream().allMatch(i -> ring[i] > 0)) {
        if (sum == 0) {
          sum = chain.stream().mapToInt(i -> ring[i]).sum();
        } else {
          if (chain.stream().mapToInt(i -> ring[i]).sum() != sum) {
            return false;
          }
        }
      }
    }
    return true;
  }
}
