package com.sukolenvo.project_euler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Problem089 {

  int run() throws Exception {
    int result = 0;
    try (InputStream stream = getClass().getClassLoader().getResourceAsStream("problem089.txt")) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
      for (String line = reader.readLine(); line != null; line = reader.readLine()) {
        result += line.length() - arabicToRoman(romanToArabic(line)).length();
      }
      return result;
    }
  }

  String arabicToRoman(int number) {
    StringBuilder result = new StringBuilder();
    for (RomanWriteExpressions expression : RomanWriteExpressions.values()) {
      while (number >= expression.getValue()) {
        result.append(expression.name());
        number -= expression.getValue();
      }
    }
    return result.toString();
  }

  int romanToArabic(String roman) {
    int result = 0;
    for (int i = 0; i < roman.length(); i++) {
      RomanChar current = RomanChar.valueOf(roman.substring(i, i + 1));
      if (i == roman.length() - 1) {
        result += current.getValue();
        continue;
      }
      RomanChar next = RomanChar.valueOf(roman.substring(i + 1, i + 2));
      if (current.compareTo(next) < 0) {
        result -= current.getValue();
      } else {
        result += current.getValue();
      }
    }
    return result;
  }

  @Getter
  @RequiredArgsConstructor
  public enum RomanChar {
    I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

    private final int value;
  }

  @Getter
  @RequiredArgsConstructor
  public enum RomanWriteExpressions {
    M(1000), CM(900), D(500), CD(400), C(100), XC(90), L(50), XL(40),
    X(10), IX(9), V(5), IV(4), I(1);

    private final int value;
  }
}
