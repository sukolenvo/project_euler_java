package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

class Problem067Test {

  @Test
  void run() throws Exception {
    try (InputStream stream = getClass().getClassLoader().getResourceAsStream("problem067.txt")) {
      assertThat(new Problem067().run(IOUtils.toString(stream, StandardCharsets.UTF_8)))
          .as("run problem")
          .isEqualTo(1074);
    }
  }
}