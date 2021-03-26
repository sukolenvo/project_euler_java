package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem082Test {

  @Test
  void findShortestPath() throws Exception {
    assertThat(new Problem082().run())
        .as("check result")
        .isEqualTo(-1);
  }
}
