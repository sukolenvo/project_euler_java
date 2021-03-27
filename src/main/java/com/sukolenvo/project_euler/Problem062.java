package com.sukolenvo.project_euler;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem062 {

  long run(int groupSize) {
    Map<String, List<Integer>>[] permutations = new Map[15];
    for (int i = 0; i < permutations.length; i++) {
      permutations[i] = new HashMap<>();
    }
    for (int i = 1; ; i++) {
      long number = (long) i * i * i;
      String key = Common.getDigitsList(number).stream()
          .sorted()
          .map(String::valueOf)
          .collect(Collectors.joining());
      int finalI = i;
      Map<String, List<Integer>> permutation = permutations[key.length()];
      if (permutation.isEmpty()) {
        Optional<Integer> result = permutations[key.length() - 1].values().stream()
            .filter(list -> list.size() == groupSize)
            .flatMap(Collection::stream)
            .min(Integer::compareTo);
        if (result.isPresent()) {
          return (long) result.get() * result.get() * result.get();
        }
      }
      permutation.compute(key, (k, v) -> {
        if (v == null) {
          return Stream.of(finalI).collect(Collectors.toList());
        }
        v.add(finalI);
        return v;
      });
    }
  }
}
