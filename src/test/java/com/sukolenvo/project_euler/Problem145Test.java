package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.sukolenvo.project_euler.Problem145.Result;
import org.junit.jupiter.api.Test;

class Problem145Test {

  @Test
  void run() {
    assertThat(new Problem145().run(1000))
        .isEqualTo(120);
    assertThat(new Problem145().run(1_000_000_000))
        .isEqualTo(-1);
  }

  @Test
  void isOddSum() {
    assertThat(new Problem145().isOddSum(36))
        .isEqualTo(Result.SUM_ODD);
    assertThat(new Problem145().isOddSum(29))
        .isEqualTo(Result.SUM_EVEN);
    assertThat(new Problem145().isOddSum(409))
        .isEqualTo(Result.SUM_ODD);
  }
}