package com.sukolenvo.project_euler;

public class Problem039 {

  int run() {
    int result = 0;
    int maxSolutions = 0;
    for (int p = 3; p <= 1000; p++) {
      int solutionsCount = countSolutions(p);
      if (solutionsCount > maxSolutions) {
        maxSolutions = solutionsCount;
        result = p;
      }
    }
    return result;
  }

  int countSolutions(int p) {
    int solutions = 0;
    for (int a = 1; a < p / 2; a++) {
      int aSquire = a * a;
      for (int b = a; ; b++) {
        int sum = aSquire + b * b;
        double c = Math.sqrt(sum);
        if (a + b + c == p) {
          solutions++;
          break;
        }
        if (a + b + c > p) {
          break;
        }
      }
    }
    return solutions;
  }
}
