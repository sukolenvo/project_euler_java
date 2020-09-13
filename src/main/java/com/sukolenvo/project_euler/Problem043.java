package com.sukolenvo.project_euler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public class Problem043 {

  private final Map<Integer, List<Integer>> digits = new HashMap<>();
  
  Problem043() {
    for (int i = 10; i < 1000; i++) {
      List<Integer> digits = Common.getDigitsList(i);
      if (digits.size() == 2) {
        digits.add(0);
      }
      if (new HashSet<>(digits).size() == 3) {
        this.digits.put(i, digits);
      }
    }
  }

  long run() {
    long result = 0;
    for (int i = 17; i < 1000; i += 17) {
      List<Integer> digits7 = digits.get(i);
      if (digits7 == null) {
        continue;
      }
      for (int j = 13; j < 1000; j += 13) {
        List<Integer> digits6 = digits.get(j);
        if (digits6 == null) {
          continue;
        }
        if (!digits6.get(0).equals(digits7.get(1))) {
          continue;
        }
        if (!digits6.get(1).equals(digits7.get(2))) {
          continue;
        }
        for (int k = 132; k < 1000; k += 11) {
          List<Integer> digits5 = digits.get(k);
          if (digits5 == null) {
            continue;
          }
          if (!digits5.get(0).equals(digits6.get(1))) {
            continue;
          }
          if (!digits5.get(1).equals(digits6.get(2))) {
            continue;
          }
          for (int l = 14; l < 1000; l += 7) {
            List<Integer> digits4 = digits.get(l);
            if (digits4 == null) {
              continue;
            }
            if (!digits4.get(0).equals(digits5.get(1))) {
              continue;
            }
            if (!digits4.get(1).equals(digits5.get(2))) {
              continue;
            }
            for (int m = 15; m < 1000; m += 5) {
              List<Integer> digits3 = digits.get(m);
              if (digits3 == null) {
                continue;
              }
              if (!digits3.get(0).equals(digits4.get(1))) {
                continue;
              }
              if (!digits3.get(1).equals(digits4.get(2))) {
                continue;
              }
              for (int n = 12; n < 1000; n += 3) {
                List<Integer> digits2 = digits.get(n);
                if (digits2 == null) {
                  continue;
                }
                if (!digits2.get(0).equals(digits3.get(1))) {
                  continue;
                }
                if (!digits2.get(1).equals(digits3.get(2))) {
                  continue;
                }
                for (int o = 12; o < 1000; o += 2) {
                  List<Integer> digits1 = digits.get(o);
                  if (digits1 == null) {
                    continue;
                  }
                  if (!digits1.get(0).equals(digits2.get(1))) {
                    continue;
                  }
                  if (!digits1.get(1).equals(digits2.get(2))) {
                    continue;
                  }
                  Set<Integer> digits2To10 = new HashSet<>();
                  digits2To10.addAll(digits1);
                  digits2To10.addAll(digits2);
                  digits2To10.addAll(digits3);
                  digits2To10.addAll(digits4);
                  digits2To10.addAll(digits5);
                  digits2To10.addAll(digits6);
                  digits2To10.addAll(digits7);
                  if (digits2To10.size() != 9) {
                    continue;
                  }
                  int digit0 = IntStream.range(0, 10).filter(digit -> !digits2To10.contains(digit)).findAny().orElseThrow();
                  result += digit0 * Math.pow(10, 9);
                  result += digits1.get(2) * Math.pow(10, 8);
                  result += digits1.get(1) * Math.pow(10, 7);
                  result += digits1.get(0) * Math.pow(10, 6);
                  result += digits2.get(0) * Math.pow(10, 5);
                  result += digits3.get(0) * Math.pow(10, 4);
                  result += digits4.get(0) * Math.pow(10, 3);
                  result += digits5.get(0) * Math.pow(10, 2);
                  result += digits6.get(0) * Math.pow(10, 1);
                  result += digits7.get(0) * Math.pow(10, 0);
                }
              }
            }
          }
        }
      }
    }
    return result;
  }
}
