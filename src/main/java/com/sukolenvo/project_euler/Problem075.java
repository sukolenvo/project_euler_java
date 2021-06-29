package com.sukolenvo.project_euler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

public class Problem075 {

  int run() {
    int[] result = generateTriangles();
    return (int) IntStream.range(0, result.length)
        .filter(i -> result[i] == 1)
        .count();
  }

  int[] generateTriangles() {
    int maxM = (int) Math.sqrt(1_500_000 / 2) + 1;
    List<Set<Long>> triangles = new ArrayList<>(maxM);
    for (int i = 1; i <= maxM; i++) {
      triangles.add(new HashSet<>(Common.getPrimeFactors(i)));
    }
    int[] result = new int[1_500_001];
//    List<Triangle> triples = new ArrayList<>();
    for (int m = 2; m <= maxM; m++) {
      Set<Long> mPrimes = triangles.get(m - 1);
      for (int n = m % 2 + 1; n < m; n += 2) {
        if (triangles.get(n - 1).stream().noneMatch(mPrimes::contains)) {
          long a = (long) m * m - (long) n * n;
          long b = 2L * m * n;
          long c = (long) m * m + (long) n * n;
          if (a + b + c > 1_500_000) {
            break;
          }
          for (int i = 1; i <= 1_500_000 / (a + b + c); i++) {
            result[(int) ((a + b + c) * i)]++;
          }
//          triples.add(new Triangle(a, b, c));
        }
      }
    }
    return result;
  }

  @Data
  @AllArgsConstructor
  @Accessors(chain = true)
  private static class Triangle {
    private long a;
    private long b;
    private long c;
  }
}
