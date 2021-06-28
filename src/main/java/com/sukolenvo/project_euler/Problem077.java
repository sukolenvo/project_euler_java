package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Data;

public class Problem077 {

  int run() {
    List<Set<Combination>> combinations = new ArrayList<>();
    combinations.add(Set.of());
    combinations.add(Set.of());
    for (int i = 2; ; i++) {
      if (i == Integer.MAX_VALUE) {
        throw new IllegalStateException("Use long");
      }
      Set<Combination> combinationI = new HashSet<>();
      for (int j = 2; j <= i - 2; j++) {
        for (Combination f : combinations.get(j)) {
          for (Combination s : combinations.get(i - j)) {
            combinationI.add(f.combine(s));
          }
        }
      }
      if (combinationI.size() > 5000) {
        return i;
      }
      if (BigInteger.valueOf(i).isProbablePrime(100)) {
        combinationI.add(new Combination(List.of(i)));
      }
      combinations.add(combinationI);
    }
  }

  @Data
  private static class Combination {

    private final List<Integer> members;

    Combination combine(Combination other) {
      return new Combination(Stream.concat(members.stream(), other.members.stream())
          .sorted()
          .collect(Collectors.toList()));
    }
  }
}
