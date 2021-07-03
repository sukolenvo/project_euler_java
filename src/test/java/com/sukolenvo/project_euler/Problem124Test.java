package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Problem124Test {

  @Test
  void run() {
    assertThat(new Problem124().run())
        .isEqualTo(-1);
  }
}