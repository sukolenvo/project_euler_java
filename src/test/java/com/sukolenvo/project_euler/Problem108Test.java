package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem108Test {

  @Test
  void run() {
    assertThat(new Problem108().run(1000))
        .isEqualTo(-1);
  }
}