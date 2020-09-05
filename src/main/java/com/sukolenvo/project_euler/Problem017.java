package com.sukolenvo.project_euler;

import java.util.stream.IntStream;

public class Problem017 {

  int run(int limitInclusive) {
    return IntStream.rangeClosed(1, limitInclusive)
        .mapToObj(EnglishNumberToWords::convert)
        .mapToInt(number -> number.replace(" ", "").replace("-", "").length())
        .sum();
  }
}
