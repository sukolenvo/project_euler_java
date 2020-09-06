package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem030Test {

  @Test
  void run() {
    assertThat(new Problem030().run())
        .isEqualTo(443839);
  }
}