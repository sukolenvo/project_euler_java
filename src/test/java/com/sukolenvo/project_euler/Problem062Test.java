package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Problem062Test {

  @Test
  void run() {
    assertThat(new Problem062().run(3))
        .as("check example")
        .isEqualTo(41063625L);

    assertThat(new Problem062().run(5))
        .as("check example")
        .isEqualTo(-1);
  }
}
