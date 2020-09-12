package com.sukolenvo.project_euler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Data;

public class Problem038 {

  int run() {
    ConcatenatedProducts result = new ConcatenatedProducts(List.of(9, 18, 27, 36, 45));
    for (int i = 1; i < 4; i++) {
      int level = (int) Math.pow(10, i);
      int number = 9 * level;
      for (int j = 0; j < Math.pow(10, i); j++) {
        Optional<ConcatenatedProducts> concatenatedProducts = getConcatenatedProducts(j + number);
        if (concatenatedProducts.isPresent() && concatenatedProducts.get().compareTo(result) > 0) {
          result = concatenatedProducts.get();
        }
      }
    }
    return Integer.parseInt(result.getDigits().map(String::valueOf).collect(Collectors.joining()));
  }

  Optional<ConcatenatedProducts> getConcatenatedProducts(int number) {
    List<Integer> products = new ArrayList<>();
    int totalDigits = 0;
    for (int i = 1; totalDigits < 9; i++) {
      int product = number * i;
      totalDigits += Common.getDigitCount(product);
      products.add(product);
    }
    if (totalDigits > 9) {
      return Optional.empty();
    }
    return Optional.of(new ConcatenatedProducts(products))
        .filter(item -> item.getDigits()
            .filter(digit -> digit != 0)
            .distinct()
            .count() == 9);
  }

  @Data
  private static class ConcatenatedProducts implements Comparable<ConcatenatedProducts> {

    private final List<Integer> products;

    Stream<Integer> getDigits() {
      return products.stream()
          .flatMap(product -> {
            List<Integer> digits = Common.getDigitsList(product);
            Collections.reverse(digits);
            return digits.stream();
          });
    }

    @Override
    public int compareTo(ConcatenatedProducts other) {
      Iterator<Integer> otherDigits = other.getDigits().iterator();
      Iterator<Integer> thisDigits = getDigits().iterator();
      while (thisDigits.hasNext()) {
        int result = thisDigits.next().compareTo(otherDigits.next());
        if (result != 0) {
          return result;
        }
      }
      return 0;
    }
  }
}
