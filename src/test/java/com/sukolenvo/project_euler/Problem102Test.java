package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

class Problem102Test {

  @Test
  void run() throws Exception {
    assertThat(new Problem102().run())
        .isEqualTo(-1);
  }

  @Test
  void triangleShift() {
    assertThat(new Problem102().triangleShift(-340,495,-153,-910,835,-947))
        .isEqualTo(0, Offset.strictOffset(0.001));

    assertThat(new Problem102().triangleShift(-175,41,-421,-714,574,-645))
        .isNotCloseTo(0, Offset.strictOffset(0.001));
  }
}