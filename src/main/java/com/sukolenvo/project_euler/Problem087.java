package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Problem087 {

  int run(int size) {
    boolean[] result = new boolean[size];
    List<Integer> square = new ArrayList<>();
    List<Integer> cube = new ArrayList<>();
    List<Integer> fourth = new ArrayList<>();
    for (int i = 2; ; i++) {
      long next = i * i;
      if (next >= result.length) {
        break;
      }
      if (!BigInteger.valueOf(i).isProbablePrime(100)) {
        continue;
      }
      square.add((int) next);
      next *= i;
      if (next < result.length) {
        cube.add((int) next);
        next *= i;
        if (next < result.length) {
          fourth.add((int) next);
        }
      }
    }
    for (Integer i : square) {
      for (Integer j : cube) {
        if (i + j >= result.length) {
          continue;
        }
        for (Integer k : fourth) {
          int matched = i + j + k;
          if (matched < result.length) {
            result[matched] = true;
          }
        }
      }
    }
    int count = 0;
    for (boolean matched : result) {
      if (matched) {
        count++;
      }
    }
    return count;
  }
}
