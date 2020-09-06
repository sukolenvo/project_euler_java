package com.sukolenvo.project_euler;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;

public class Problem022 {

  long run() throws Exception {
    String names = IOUtils.toString(getClass().getClassLoader().getResource("problem022.txt"), StandardCharsets.UTF_8);
    List<String> sortedNames = Arrays.stream(names.split(","))
        .map(quotedName -> quotedName.substring(1, quotedName.length() - 1))
        .sorted()
        .collect(Collectors.toList());
    long result = 0;
    for (int i = 0; i < sortedNames.size(); i++) {
      int charSum = 0;
      for (char c : sortedNames.get(i).toCharArray()) {
        charSum += c - 'A' + 1;
      }
      result += charSum * (i + 1);
    }
    return result;
  }
}
