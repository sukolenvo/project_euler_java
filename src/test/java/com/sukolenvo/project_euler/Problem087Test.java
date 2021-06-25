package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem087Test {

  @Test
  void run() {
    assertThat(new Problem087().run(50))
        .isEqualTo(4);

    assertThat(new Problem087().run(50_000_000))
        .isEqualTo(-1);
  }
}