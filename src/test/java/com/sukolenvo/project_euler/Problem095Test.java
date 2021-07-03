package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.Test;

class Problem095Test {

  @Test
  void run() {
    Problem095 problem095 = new Problem095();
    assertThat(problem095.getChain(220))
        .isEqualTo(Set.of(220, 284));
    assertThat(problem095.getChain(28))
        .isEqualTo(Set.of(28));
    assertThat(problem095.getChain(12496))
        .isEqualTo(Set.of(12496, 14288, 15472, 14536, 14264));
    assertThat(problem095.run())
        .isEqualTo(-1);
  }
}