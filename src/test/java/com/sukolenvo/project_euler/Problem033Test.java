package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Problem033Test {

  @Test
  void run() {
    assertThat(new Problem033().run())
        .isEqualTo(-1);
  }
}