package com.sukolenvo.project_euler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Problem069Test {

  @Test
  void runSample() {
    assertThat(new Problem069().run(10))
        .isEqualTo(6);
  }

  @Test
  void runTask() {
    assertThat(new Problem069().run(1_000_000))
        .isEqualTo(-1);
  }
}