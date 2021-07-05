package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem084Test {

  @Test
  void run() {
    assertThat(new Problem084().run(6))
        .isEqualTo("102400");
    assertThat(new Problem084().run(4))
        .isEmpty();
  }
}