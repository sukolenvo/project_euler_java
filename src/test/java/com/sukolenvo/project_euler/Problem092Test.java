package com.sukolenvo.project_euler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Problem092Test {

  @Test
  void run() {
    assertThat(new Problem092().runThroughChain(44))
        .isEqualTo(1);
    assertThat(new Problem092().runThroughChain(85))
        .isEqualTo(89);
    assertThat(new Problem092().run())
        .isEqualTo(-1);
  }
}