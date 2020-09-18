package com.sukolenvo.project_euler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Problem050Test {

  @Test
  void run() {
    assertThat(new Problem050().run())
        .isEqualTo(-1);
  }
}