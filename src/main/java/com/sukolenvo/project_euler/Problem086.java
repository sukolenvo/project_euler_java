package com.sukolenvo.project_euler;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Problem086 {

  int run() {
    return generateCuboids(1_000_000);
  }

  int generateCuboids(int goal) {
    TreeSet<Cuboid> cuboids = new TreeSet<>(Comparator.comparing(Cuboid::getMaxSide)
        .thenComparing(Cuboid::getA)
        .thenComparing(Cuboid::getB)
        .thenComparing(Cuboid::getC));
    AtomicInteger max = new AtomicInteger(Integer.MAX_VALUE);
    generateTriangles(triangle -> {
      if (Math.min(triangle.a, triangle.b) / 2 > max.get()) {
        return false;
      }
      for (int i = 1; Math.max(triangle.a, triangle.b) * i / 2 < max.get(); i++) {
        long a = triangle.a * i;
        long b = triangle.b * i;
        for (int j = 1; j <= a / 2; j++) {
          List<Long> cuboid = Stream.of(b, (long) j, a - j)
              .sorted()
              .collect(Collectors.toList());
          if (check(cuboid)) {
            cuboids.add(new Cuboid(cuboid.get(0), cuboid.get(1), cuboid.get(2)));
          }
        }
        for (int j = 1; j <= b / 2; j++) {
          List<Long> cuboid = Stream.of(a, (long) j, b - j)
              .sorted()
              .collect(Collectors.toList());
          if (check(cuboid)) {
            cuboids.add(new Cuboid(cuboid.get(0), cuboid.get(1), cuboid.get(2)));
          }
        }
        if (cuboids.size() > goal) {
          Iterator<Cuboid> iterator = cuboids.descendingIterator();
          while (cuboids.size() > goal) {
            iterator.next();
            iterator.remove();
          }
          max.set((int) cuboids.last().getMaxSide());
        }
      }
      return true;
    });
    return max.get();
  }

  private boolean check(List<Long> cuboid) {
    double path1 = Math.pow(cuboid.get(0) + cuboid.get(1), 2) + Math.pow(cuboid.get(2), 2);
    double path2 = Math.pow(cuboid.get(0) + cuboid.get(2), 2) + Math.pow(cuboid.get(1), 2);
    double path3 = Math.pow(cuboid.get(2) + cuboid.get(1), 2) + Math.pow(cuboid.get(0), 2);
    long pathSquare = (long) Math.min(Math.min(path1, path2), path3);
    long path = (long) Math.sqrt(pathSquare);
    return path * path == pathSquare;
  }

  void generateTriangles(Function<Triangle, Boolean> consumer) {
    Set<Triangle> triangles = new HashSet<>();
    for (int m = 2; ; m++) {
      Set<Long> mPrimes = Set.copyOf(Common.getPrimeFactors(m));
      for (int n = m % 2 + 1; n < m; n += 2) {
        if (Set.copyOf(Common.getPrimeFactors(n)).stream().noneMatch(mPrimes::contains)) {
          long a = (long) m * m - (long) n * n;
          long b = 2L * m * n;
          long c = (long) m * m + (long) n * n;
          Triangle triangle = new Triangle(a, b, c);
          if (triangles.add(triangle)) {
            if (!consumer.apply(triangle)) {
              return;
            }
          }
        }
      }
    }
  }

  @Data
  @AllArgsConstructor
  private static class Triangle {

    private long a;
    private long b;
    private long c;
  }

  @Data
  private static class Cuboid {
    private long a;
    private long b;
    private long c;
    private long maxSide;

    Cuboid(long a, long b, long c) {
      this.a = a;
      this.b = b;
      this.c = c;
      maxSide = Math.max(Math.max(a, b), c);
    }
  }
}
