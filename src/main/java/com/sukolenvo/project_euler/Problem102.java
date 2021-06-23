package com.sukolenvo.project_euler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Problem102 {

  int run() throws Exception {
    int result = 0;
    try (InputStream stream = getClass().getClassLoader().getResourceAsStream("problem102.txt")) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
      for (String line = reader.readLine(); line != null; line = reader.readLine()) {
        String[] values = line.split(",");
        assert values.length == 6;
        int x1 = Integer.parseInt(values[0]);
        int y1 = Integer.parseInt(values[1]);
        int x2 = Integer.parseInt(values[2]);
        int y2 = Integer.parseInt(values[3]);
        int x3 = Integer.parseInt(values[4]);
        int y3 = Integer.parseInt(values[5]);
        double diff = triangleShift(x1, y1, x2, y2, x3, y3);
        if (diff == 0) {
          result++;
        } else if (Math.abs(diff) < 0.001){
          System.out.println(diff);
        }
      }
      return result;
    }
  }

   double triangleShift(int x1, int y1, int x2, int y2, int x3, int y3) {
     double total = area(x1, y1, x2, y2, x3, y3);
     return total
         - area(0, 0, x2, y2, x3, y3)
         - area(x1, y1, 0, 0, x3, y3)
         - area(x1, y1, x2, y2, 0, 0);
   }

  private double area(int x1, int y1, int x2, int y2, int x3, int y3) {
    return Math.abs((double) x1 * (y2 - y3) + (double) x2 * (y3 - y1) + (double) x3 * (y1 - y2)) / 2;
  }
}
