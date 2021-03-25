package com.sukolenvo.project_euler;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Cleanup;
import org.apache.commons.io.IOUtils;

public class Problem079 {

  int run() throws Exception {
    @Cleanup InputStream content = getClass().getClassLoader().getResourceAsStream("problem079.txt");
    List<int[]> codes = IOUtils.readLines(content, StandardCharsets.UTF_8).stream()
        .map(line -> line.trim().chars().map(c -> c - '0').toArray())
        .collect(Collectors.toList());
    List<Integer> digits = List.of(7, 3, 1, 6, 2, 8, 9, 0);
    for (int[] code : codes) {
      assert digits.indexOf(code[0]) < digits.indexOf(code[1]) && digits.indexOf(code[1]) < digits.indexOf(code[2]);
    }
    int sum = 0;
    for (int i = 0; i < digits.size(); i++) {
      sum += digits.get(i) * Math.pow(10, digits.size() - i - 1);
    }
    return sum;
  }
}
