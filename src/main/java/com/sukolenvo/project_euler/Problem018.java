package com.sukolenvo.project_euler;

import java.util.Arrays;
import java.util.function.Function;

public class Problem018 {

  long run(String pyramid) {
    long[][] numbers = readPyramid(pyramid);
    for (int i = 1; i < numbers.length; i++) {
      for (int j = 0; j < numbers[i].length; j++) {
        if (j == 0) {
          numbers[i][j] += numbers[i - 1][j];
        } else {
          numbers[i][j] += Math.max(numbers[i - 1][j], numbers[i - 1][j - 1]);
        }
      }
    }
    return Arrays.stream(numbers[numbers.length - 1]).max().orElseThrow();
  }

  private long[][] readPyramid(String pyramid) {
    String[] lines = pyramid.trim().split("\n");
    int baseSize = lines[lines.length - 1].split(" ").length;
    long[][] numbers = new long[baseSize][baseSize];
    for (int i = 0; i < lines.length; i++) {
      String[] lineNumbers = lines[i].trim().split(" ");
      for (int j = 0; j < lineNumbers.length; j++) {
        numbers[i][j] = Integer.parseInt(lineNumbers[j]);
      }
    }
    return numbers;
  }
}
