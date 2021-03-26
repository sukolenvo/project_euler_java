package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import com.sukolenvo.project_euler.Problem081.Destination;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class Problem081Test {

  @Test
  void findShortestPath() throws Exception {
    int[][] example = {
        {131, 673, 234, 103, 18},
        {201, 96, 342, 965, 159},
        {630, 803, 746, 422, 111},
        {537, 699, 497, 121, 956},
        {805, 732, 524, 37, 331}
    };
    assertThat(Problem081.findShortestPath(example, new ArrayList<>(List.of(new Destination(0, 0, 131))),
        destination -> destination.getX() == 4 && destination.getY() == 4).getDistance())
        .as("check example")
        .isEqualTo(2427);

    assertThat(new Problem081().run())
        .as("check result")
        .isEqualTo(-1);
  }
}
