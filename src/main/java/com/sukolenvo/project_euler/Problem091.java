package com.sukolenvo.project_euler;

import java.util.stream.IntStream;

public class Problem091 {

  int run(int size) {
    int result = 0;
    for (int x1 = 0; x1 <= size; x1++) {
      for (int y1 = 0; y1 <= size; y1++) {
        for (int x2 = 0; x2 <= size; x2++) {
          for (int y2 = 0; y2 <= size; y2++) {
            int aSquare = getLengthSquare(0, 0, x1, y1);
            int bSquare = getLengthSquare(0, 0, x2, y2);
            int cSquare = getLengthSquare(x1, y1, x2, y2);
            if (aSquare > 0 && bSquare > 0 && cSquare > 0) {
              int[] sides = IntStream.of(aSquare, bSquare, cSquare)
                  .sorted()
                  .toArray();
              if (sides[0] + sides[1] == sides[2]) {
                result++;
              }
            }
          }
        }
      }
    }
    return result / 2;
  }

  private int getLengthSquare(int x1, int y1, int x2, int y2) {
    int deltaX = Math.abs(x1 - x2);
    int deltaY = Math.abs(y1 - y2);
    return deltaX * deltaX + deltaY * deltaY;
  }

//  int run(int size) {
//    int result = size * size * 3;
//    for (int deltaY = 1; deltaY < size; deltaY++) {
//      for (int deltaX = deltaY; deltaX < size; deltaX++) {
//        if (Common.getPrimeFactors(deltaX).stream().anyMatch(Set.copyOf(Common.getPrimeFactors(deltaY))::contains)) {
//          continue;
//        }
//        for (int x = deltaX, y = deltaY; x <= size; x += deltaX, y += deltaY) {
//          result += getCount(x, y, deltaX, deltaY, size);
//        }
//      }
//    }
//    return result;
//  }
//
//  private int getCount(int x, int y, int deltaX, int deltaY, int size) {
//    int result = 0;
//    while (true) {
//      x -= deltaY;
//      y += deltaX;
//      if (x >= 0 && x <= size && y >= 0 && y <= size) {
//        result++;
//      } else {
//        break;
//      }
//    }
//    return result * 2;
//  }
}
