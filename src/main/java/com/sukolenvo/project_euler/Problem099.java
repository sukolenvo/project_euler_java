package com.sukolenvo.project_euler;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Cleanup;
import lombok.Data;
import org.apache.commons.io.IOUtils;

public class Problem099 {

  int run() throws Exception {
    @Cleanup InputStream content = getClass().getClassLoader().getResourceAsStream("problem099.txt");
    List<IndexNumber> lines = IOUtils.readLines(content, StandardCharsets.UTF_8)
        .stream()
        .map(line -> line.split(",", 2))
        .map(values -> new IndexNumber(Integer.parseInt(values[0]), Integer.parseInt(values[1])))
        .collect(Collectors.toList());

    int maxIndex = 0;
    for (int i = 1; i < lines.size(); i++) {
      IndexNumber currentMax = lines.get(maxIndex);
      IndexNumber current = lines.get(i);
      if (current.compareTo(currentMax) > 0) {
        maxIndex = i;
      }
    }
    return maxIndex + 1;
  }

  @Data
  static class IndexNumber implements Comparable<IndexNumber> {
    private final int base;
    private final int pow;

    @Override
    public int compareTo(IndexNumber o) {
      double power = Math.log(o.base) / Math.log(this.base);
      return Double.compare(pow, o.pow * power);
    }
  }
}
