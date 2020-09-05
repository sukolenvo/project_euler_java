package com.sukolenvo.project_euler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Problem013 {

  String run() throws Exception {
    try (InputStream stream = getClass().getClassLoader().getResourceAsStream("problem013.txt")) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
      BigInteger sum = BigInteger.ZERO;
      for (String line = reader.readLine(); line != null; line = reader.readLine()) {
        line = line.trim();
        sum = sum.add(new BigInteger(line));
      }
      return sum.toString().substring(0, 10);
    }
  }
}
