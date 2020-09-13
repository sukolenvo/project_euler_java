package com.sukolenvo.project_euler;

import java.util.PrimitiveIterator.OfLong;
import java.util.stream.Stream;

public class Problem045 {

  long run() {
    OfLong triangular = Stream.iterate(1L, i -> i + 1)
        .mapToLong(n -> n * (n + 1) / 2)
        .iterator();
    OfLong pentagonal = Stream.iterate(1L, i -> i + 1)
        .mapToLong(n -> n * (3 * n - 1) / 2)
        .iterator();
    OfLong hexagonal = Stream.iterate(1L, i -> i + 1)
        .mapToLong(n -> n * (2 * n - 1))
        .iterator();
    long nextTriangular = triangular.nextLong();
    long nextPentagonal = pentagonal.nextLong();
    long nextHexagonal = hexagonal.nextLong();
    while (nextTriangular <= 40755) {
      nextTriangular = triangular.nextLong();
    }
    while (nextPentagonal <= 40755) {
      nextPentagonal = pentagonal.nextLong();
    }
    while (nextHexagonal <= 40755) {
      nextHexagonal = hexagonal.nextLong();
    }
    while (true) {
      while (nextPentagonal < nextHexagonal) {
        nextPentagonal = pentagonal.nextLong();
      }
      while (nextTriangular < nextHexagonal) {
        nextTriangular = triangular.nextLong();
      }
      if (nextHexagonal == nextPentagonal && nextHexagonal == nextTriangular) {
        return nextHexagonal;
      }
      nextHexagonal = hexagonal.nextLong();
    }
  }
}
