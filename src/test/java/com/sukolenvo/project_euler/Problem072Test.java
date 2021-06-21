package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem072Test {

  @Test
  void result() {
    assertThat(new Problem072().run())
        .isEqualTo(-1);
  }
}