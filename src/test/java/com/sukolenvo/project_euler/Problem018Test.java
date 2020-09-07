package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

class Problem018Test {

  @Test
  void run() throws Exception {
    assertThat(new Problem018().run("3\n"
        + "7 4\n"
        + "2 4 6\n"
        + "8 5 9 3"))
        .as("run small sample")
        .isEqualTo(23);

    try (InputStream stream = getClass().getClassLoader().getResourceAsStream("problem018.txt")) {
      assertThat(new Problem018().run(IOUtils.toString(stream, StandardCharsets.UTF_8)))
          .as("run problem")
          .isEqualTo(0);
    }
  }
}