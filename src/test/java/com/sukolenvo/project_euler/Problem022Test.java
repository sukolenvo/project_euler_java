package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem022Test {

  @Test
  void run() throws Exception {
    assertThat(new Problem022().run())
        .isEqualTo(871198282L);
  }
}