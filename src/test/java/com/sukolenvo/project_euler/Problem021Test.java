package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem021Test {

  @Test
  void run() {
    assertThat(new Problem021().run())
        .isEqualTo(31626);
  }
}