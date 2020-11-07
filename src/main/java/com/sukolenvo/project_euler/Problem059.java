package com.sukolenvo.project_euler;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

public class Problem059 {

  int run() {
    int[] encryptedMessage = readEncrypted();
    for (int i = 'a'; i <= 'z'; i++) {
      for (int j = 'a'; j <= 'z'; j++) {
        for (int k = 'a'; k <= 'z'; k++) {
          Optional<String> content = tryDecrypt(encryptedMessage, new int[] {i, j, k}, 90);
          if (content.isPresent()) {
            System.out.println(content.get());
            return asciiSum(content.get());
          }
        }
      }
    }
    throw new IllegalStateException("Key not found");
  }

  private int asciiSum(String message) {
    int result = 0;
    for (char c :message.toCharArray()) {
      result += c;
    }
    return result;
  }

  private Optional<String> tryDecrypt(int[] encryptedMessage, int[] key, int englishCharsRatio) {
    int alphabeticChars = 0;
    char[] decrypted = new char[encryptedMessage.length];
    for (int i = 0; i < encryptedMessage.length; i++) {
      decrypted[i] = (char) (encryptedMessage[i] ^ key[i % key.length]);
      if (Character.isAlphabetic(decrypted[i]) || decrypted[i] == ' ' || decrypted[i] == ',' || decrypted[i] == '.') {
        alphabeticChars++;
      }
    }
    if (alphabeticChars * 100 / encryptedMessage.length > englishCharsRatio) {
      return Optional.of(String.valueOf(decrypted));
    }
    return Optional.empty();
  }

  private int[] readEncrypted() {
    try (InputStream stream = getClass().getClassLoader().getResourceAsStream("problem059.txt")) {
      return Arrays.stream(IOUtils.toString(stream, StandardCharsets.UTF_8).split(","))
          .map(String::trim)
          .mapToInt(Integer::valueOf)
          .toArray();
    } catch (IOException e) {
      throw new IllegalStateException("Failed to read message", e);
    }
  }
}
