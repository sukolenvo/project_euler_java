package com.sukolenvo.project_euler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Problem059Test {

  @Test
  void run() {
    assertThat(new Problem059().run())
        .isEqualTo(-1);
  }
}