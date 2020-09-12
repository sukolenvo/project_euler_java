package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Problem042Test {

  @Test
  void run() throws Exception {
    assertThat(new Problem042().run())
        .isEqualTo(-1);
  }
}