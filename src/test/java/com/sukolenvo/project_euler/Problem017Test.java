package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Problem017Test {

  @Test
  void run() {
    assertThat(new Problem017().run(5))
        .as("check 5")
        .isEqualTo(19);

    assertThat(new Problem017().run(1000))
        .as("check 1000")
        .isEqualTo(0);
  }
}