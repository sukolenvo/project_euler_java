package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem060Test {

  @Test
  void run() {
    assertThat(new Problem060().run(4))
        .isEqualTo(792);
    assertThat(new Problem060().run(5))
        .isEqualTo(-1);
  }

  @Test
  void isConcatenatePrime() {
    assertThat(new Problem060().isConcatenatePrime(3, 673))
        .isTrue();
  }
}