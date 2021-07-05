package com.sukolenvo.project_euler;

import Jama.Matrix;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class Problem084 {

  private static final int GO_POS = 0;
  private static final int JAIL_POS = 10;
  private static final int CC1_POS = 2;
  private static final int CC2_POS = 17;
  private static final int CC3_POS = 33;
  private static final int CH1_POS = 7;
  private static final int CH2_POS = 22;
  private static final int CH3_POS = 36;
  private static final int GO_2_JAIL_POS = 30;

  String run(int diceSize) {
    List<DiceOutcome> diceOutcomes = getDiceOutcomes(diceSize);
    Map<StateTransition, Fraction> stateToStateTransitions = new HashMap<>();
    for (int i = 0; i < 40; i++) {
      for (DiceOutcome diceOutcome : diceOutcomes) {
        stateToStateTransitions.compute(new StateTransition(i, JAIL_POS),
            (k, v) -> v == null ? diceOutcome.getGoToJail() : v.add(diceOutcome.getGoToJail()).normalise());
        for (Entry<Integer, Fraction> finalPosition : processLanding((diceOutcome.getStepsForward() + i) % 40).entrySet()) {
          Fraction prob = finalPosition.getValue().mul(diceOutcome.getStepsForwardProb());
          stateToStateTransitions.compute(new StateTransition(i, finalPosition.getKey()),
              (k, v) -> v == null ? prob : v.add(prob).normalise());
        }
      }
    }
    Matrix matrix = new Matrix(41, 40);
    for (int i = 0; i < 40; i++) {
      for (int j = 0; j < 40; j++) {
        Fraction prob = stateToStateTransitions.getOrDefault(new StateTransition(i, j), new Fraction(0, 1));
        matrix.set(j, i, (double) prob.getNumerator() / prob.getDenominator());
      }
      matrix.set(40, i, 1.0);
      matrix.set(i, i, matrix.get(i, i) - 1);
    }
    Matrix l = new Matrix(41, 1, 0.0);
    l.set(40, 0, 1.0);
    Matrix steadyState = matrix.solve(l);
    String result = IntStream.range(0, 40)
        .boxed()
        .sorted(Comparator.<Integer, Double>comparing(i -> steadyState.get(i, 0)).reversed())
        .limit(3)
        .map(i -> String.format("%02d", i))
        .collect(Collectors.joining(""));
    return result;
  }

  /**
   * @return map final square to probability
   */
  private Map<Integer, Fraction> processLanding(int position) {
    return switch (position) {
      case GO_2_JAIL_POS -> Map.of(JAIL_POS, new Fraction(1, 1));
      case CC1_POS, CC2_POS, CC3_POS -> Map.of(GO_POS, new Fraction(1, 16),
          JAIL_POS, new Fraction(1, 16),
          position, new Fraction(7, 8));
      case CH1_POS, CH2_POS, CH3_POS -> processChance(position);
      default -> Map.of(position, new Fraction(1, 1));
    };
  }

  private Map<Integer, Fraction> processChance(int position) {
    int nextRail = switch (position) {
      case CH1_POS -> 15;
      case CH2_POS -> 25;
      case CH3_POS -> 5;
      default -> throw new IllegalStateException("Unexpected position " + position);
    };
    int nextUtility = switch (position) {
      case CH1_POS, CH3_POS -> 12;
      case CH2_POS -> 28;
      default -> throw new IllegalStateException("Unexpected position " + position);
    };
    Map<Integer, Fraction> result = new HashMap<>();
    result.compute(GO_POS, (k, v) -> v == null ? new Fraction(1, 16) : v.add(new Fraction(1, 16)));
    result.compute(JAIL_POS, (k, v) -> v == null ? new Fraction(1, 16) : v.add(new Fraction(1, 16)));
    result.compute(11, (k, v) -> v == null ? new Fraction(1, 16) : v.add(new Fraction(1, 16)));
    result.compute(24, (k, v) -> v == null ? new Fraction(1, 16) : v.add(new Fraction(1, 16)));
    result.compute(39, (k, v) -> v == null ? new Fraction(1, 16) : v.add(new Fraction(1, 16)));
    result.compute(5, (k, v) -> v == null ? new Fraction(1, 16) : v.add(new Fraction(1, 16)));
    result.compute(nextRail, (k, v) -> v == null ? new Fraction(1, 8) : v.add(new Fraction(1, 8)));
    result.compute(nextUtility, (k, v) -> v == null ? new Fraction(1, 16) : v.add(new Fraction(1, 16)));
    if (position == 36) {
      result.compute(GO_POS, (k, v) -> v == null ? new Fraction(1, 16 * 16) : v.add(new Fraction(1, 16 * 16)));
      result.compute(JAIL_POS, (k, v) -> v == null ? new Fraction(1, 16 * 16) : v.add(new Fraction(1, 16 * 16)));
      result.compute(position - 3, (k, v) -> v == null ? new Fraction(7, 16 * 8) : v.add(new Fraction(7, 16 * 8)));
    } else {
      result.compute(position - 3, (k, v) -> v == null ? new Fraction(1, 16) : v.add(new Fraction(1, 16)));
    }
    result.compute(position, (k, v) -> v == null ? new Fraction(3, 8) : v.add(new Fraction(3, 8)));
    return result;
  }

  /**
   * Map of number of moves forward to probability of outcome.
   */
  private List<DiceOutcome> getDiceOutcomes(int diceSize) {
    Map<DiceOutcome, Integer> rolls = new HashMap<>();
    for (int f = 1; f <= diceSize; f++) {
      for (int s = 1; s <= diceSize; s++) {
        rolls.compute(DiceOutcome.builder()
            .setStepsForward(f + s)
            .setDoubles(f == s)
            .build(), (k, v) -> v == null ? 1 : v + 1);
      }
    }
    int total = rolls.values().stream().mapToInt(Integer::intValue).sum();
    List<DiceOutcome> outcomes = new ArrayList<>(rolls.keySet());
    outcomes.forEach(it -> it.setRollProb(new Fraction(rolls.get(it), total).normalise()));
    Fraction doubleProbability = outcomes.stream()
        .filter(DiceOutcome::isDoubles)
        .map(DiceOutcome::getRollProb)
        .reduce(Fraction::add)
        .orElseThrow()
        .normalise();
    Fraction doubleGoToJailProb = doubleProbability.mul(doubleProbability).normalise();
    for (DiceOutcome diceOutcome : outcomes) {
      if (diceOutcome.isDoubles()) {
        diceOutcome.setGoToJail(doubleGoToJailProb.mul(diceOutcome.getRollProb()).normalise());
        diceOutcome.setStepsForwardProb(diceOutcome.getRollProb()
            .add(diceOutcome.getGoToJail().mul(new Fraction(-1, 1)))
            .normalise());
      } else {
        diceOutcome.setGoToJail(new Fraction(0, 1));
        diceOutcome.setStepsForwardProb(diceOutcome.getRollProb());
      }
    }
    return outcomes;
  }

  @Data
  @Builder(setterPrefix = "set")
  @AllArgsConstructor
  static class DiceOutcome {

    private int stepsForward;
    private boolean doubles;
    private Fraction rollProb;
    private Fraction stepsForwardProb;
    private Fraction goToJail;
  }

  @Data
  @AllArgsConstructor
  static class StateTransition {

    private int from;
    private int to;
  }
}
