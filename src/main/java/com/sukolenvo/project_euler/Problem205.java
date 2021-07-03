package com.sukolenvo.project_euler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Problem205 {

  BigDecimal run() {
    Map<Integer, Long> peterOutcomes = diceOutcomes(9, 4);
    Map<Integer, Long> colinOutcomes = diceOutcomes(6, 6);
    int totalPeterOutcomes = peterOutcomes.values().stream().mapToInt(Long::intValue).sum();
    int totalColinOutcomes = colinOutcomes.values().stream().mapToInt(Long::intValue).sum();
    BigInteger numerator = BigInteger.ZERO;
    BigInteger denominator = BigInteger.ONE;
    for (Entry<Integer, Long> peterOutcome : peterOutcomes.entrySet()) {
      Fraction winOutcome = new Fraction(colinOutcomes.entrySet().stream()
          .filter(colinOutcome -> colinOutcome.getKey() < peterOutcome.getKey())
          .mapToLong(Entry::getValue)
          .sum(),
          totalColinOutcomes);
      Fraction winChance = new Fraction(peterOutcome.getValue(), totalPeterOutcomes).mul(winOutcome);
      numerator = numerator.multiply(BigInteger.valueOf(winChance.getDenominator())).add(denominator.multiply(
          BigInteger.valueOf(winChance.getNumerator())));
      denominator = denominator.multiply(BigInteger.valueOf(winChance.getDenominator()));
    }
    return new BigDecimal(numerator).divide(new BigDecimal(denominator), 7, RoundingMode.HALF_UP);
  }

  /**
   * @return map of outcome to change
   */
  private Map<Integer, Long> diceOutcomes(int count, int diceSize) {
    List<Integer> result = new ArrayList<>((int) Math.pow(diceSize, count));
    for (int i = 0; i < Math.pow(diceSize, count); i++) {
      result.add(getDiceOutcome(i, count, diceSize));
    }
    return result.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
  }

  private int getDiceOutcome(int sequence, int count, int diceSize) {
    int sum = 0;
    for (int i = 0; i < count; i++) {
      sum += sequence % diceSize + 1;
      sequence /= diceSize;
    }
    return sum;
  }
}
