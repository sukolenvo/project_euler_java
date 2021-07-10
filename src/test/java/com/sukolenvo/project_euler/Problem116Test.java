package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Problem116Test {

  @Test
  void run() {
    assertThat(new Problem116().paint(5, 2))
        .isEqualTo(8);
    assertThat(new Problem116().paint(5, 3))
        .isEqualTo(4);
    assertThat(new Problem116().paint(5, 4))
        .isEqualTo(3);
    assertThat(new Problem116().run(5))
        .isEqualTo(12);
    assertThat(new Problem116().run(50))
        .isEqualTo(-1);
  }
}