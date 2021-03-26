package com.sukolenvo.project_euler;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import lombok.Cleanup;
import lombok.Data;
import org.apache.commons.io.IOUtils;

public class Problem083 {

  int run() throws Exception {
    @Cleanup InputStream content = getClass().getClassLoader().getResourceAsStream("problem083.txt");
    List<String> lines = IOUtils.readLines(content, StandardCharsets.UTF_8);
    assert lines.size() == 80;
    int[][] matrix = new int[lines.size()][lines.size()];
    for (int i = 0; i < lines.size(); i++) {
      matrix[i] = Arrays.stream(lines.get(i).split(","))
          .mapToInt(Integer::parseInt)
          .toArray();
      assert matrix[i].length == 80;
    }
    return findShortestPath(matrix, new ArrayList<>(List.of(new Destination(0, 0, matrix[0][0]))),
        destination -> destination.getX() == matrix.length - 1 && destination.getY() == matrix.length - 1)
        .getDistance();
  }

  static Destination findShortestPath(int[][] weights, List<Destination> destinations, Predicate<Destination> isFinal) {
    destinations.forEach(destination -> weights[destination.x][destination.y] = -1);
    while (true) {
      Destination next = new Destination(-1, -1, Integer.MAX_VALUE);
      for (Destination destination : destinations) {
        if (destination.x > 0 && weights[destination.x - 1][destination.y] > 0
            && weights[destination.x - 1][destination.y] + destination.distance < next.distance) {
          next = new Destination(destination.x - 1, destination.y, weights[destination.x - 1][destination.y] + destination.distance);
        }
        if (destination.x < weights.length - 1 && weights[destination.x + 1][destination.y] > 0
            && weights[destination.x + 1][destination.y] + destination.distance < next.distance) {
          next = new Destination(destination.x + 1, destination.y, weights[destination.x + 1][destination.y] + destination.distance);
        }
        if (destination.y > 0 && weights[destination.x][destination.y - 1] > 0
            && weights[destination.x][destination.y - 1] + destination.distance < next.distance) {
          next = new Destination(destination.x, destination.y - 1, weights[destination.x][destination.y - 1] + destination.distance);
        }
        if (destination.y < weights.length - 1 && weights[destination.x][destination.y + 1] > 0
            && weights[destination.x][destination.y + 1] + destination.distance < next.distance) {
          next = new Destination(destination.x, destination.y + 1, weights[destination.x][destination.y + 1] + destination.distance);
        }
      }

      if (isFinal.test(next)) {
        return next;
      }
      destinations.add(next);
      weights[next.x][next.y] = -1;
    }
  }

  @Data
  static class Destination {
    private final int x;
    private final int y;
    private final int distance;
  }
}
