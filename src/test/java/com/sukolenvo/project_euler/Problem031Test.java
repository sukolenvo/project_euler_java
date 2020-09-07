package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Problem031Test {

  @Test
  void run() {
    assertThat(new Problem031().run())
        .isEqualTo(73682);
  }
}