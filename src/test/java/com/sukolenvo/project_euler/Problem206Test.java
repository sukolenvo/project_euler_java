package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem206Test {

  @Test
  void run() {
    assertThat(new Problem206().run())
        .isEqualTo(-1);
  }

  @Test
  void match() {
    assertThat(new Problem206().matched(Common.getDigitsList(10203040506070809L)))
        .isTrue();
  }
}