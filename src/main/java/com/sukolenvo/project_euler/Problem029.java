package com.sukolenvo.project_euler;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Problem029 {

  int run(int limit) {
    Set<Map<Long, Integer>> items = new HashSet<>();
    for (int i = 2; i <= limit; i++) {
      List<Long> primeFactors = Common.getPrimeFactors(i);
      for (int j = 2; j <= limit; j++) {
        int power = j;
        items.add(primeFactors.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(prime -> power))));
      }
    }
    return items.size();
  }
}
