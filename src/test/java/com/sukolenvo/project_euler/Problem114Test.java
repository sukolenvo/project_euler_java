package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Problem114Test {

  @Test
  void run() {
    assertThat(new Problem114().run(7))
        .isEqualTo(17);
    assertThat(new Problem114().run(50))
        .isEqualTo(-1);
  }
}