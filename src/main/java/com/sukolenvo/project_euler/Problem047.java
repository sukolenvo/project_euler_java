package com.sukolenvo.project_euler;

public class Problem047 {

  int run() {
    int consecutive = 0;
    for (int i = 2; ; i++) {
      if (Common.getPrimeFactors(i).stream().distinct().count() == 4) {
        consecutive++;
        if (consecutive == 4) {
          return i - 3;
        }
      } else {
        consecutive = 0;
      }
    }
  }
}
