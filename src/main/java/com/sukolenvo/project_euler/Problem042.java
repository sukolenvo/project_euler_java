package com.sukolenvo.project_euler;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.io.IOUtils;

public class Problem042 {

  int run() throws Exception {
    List<String> words = IOUtils
        .readLines(getClass().getClassLoader().getResourceAsStream("problem042.txt"), StandardCharsets.UTF_8);
    int longestWord = words.stream()
        .mapToInt(String::length)
        .max()
        .orElseThrow();
    int maxValue = ('Z' - 'A' + 1) * longestWord;
    Set<Integer> sequence = new TreeSet<>();
    for (int i = 1; ; i++) {
      int newTerm = i * (i + 1) / 2;
      if (newTerm > maxValue) {
        break;
      }
      sequence.add(newTerm);
    }
    int result = 0;
    for (String word : words) {
      int charSum = 0;
      for (char c : word.toCharArray()) {
        charSum += c - 'A' + 1;
      }
      if (sequence.contains(charSum)) {
        result++;
      }
    }
    return result;
  }
}
