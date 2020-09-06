package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem020Test {

  @Test
  void run() {
    assertThat(new Problem020().run())
        .isEqualTo(648);
  }
}