package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Problem035Test {

  @Test
  void run() {
    assertThat(new Problem035().run())
        .isEqualTo(-1);
  }
}