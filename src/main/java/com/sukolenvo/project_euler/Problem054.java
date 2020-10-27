package com.sukolenvo.project_euler;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Problem054 {
  
  int run() throws IOException  {
    List<String> hands = IOUtils
        .readLines(getClass().getClassLoader().getResourceAsStream("problem054.txt"), StandardCharsets.UTF_8);
    int firstPlayerWIns = 0;
    for (String hand : hands) {
      String[] cards = hand.split(" ");
      if (cards.length != 10) {
        throw new IllegalArgumentException("Invalid line " + hand);
      }
      List<Card> firstPlayer = Arrays.stream(cards)
          .limit(5)
          .map(Card::parse)
          .collect(Collectors.toList());
      List<Card> secondPlayer = Arrays.stream(cards)
          .skip(5)
          .map(Card::parse)
          .collect(Collectors.toList());
      if (Combination.fromHand(firstPlayer).compareTo(Combination.fromHand(secondPlayer)) > 0) {
        firstPlayerWIns++;
      }
    }
    return firstPlayerWIns;
  }
  
  @Data
  static class Card {
    private final CardValue cardValue;
    private final Suit suit;
    
    static Card parse(String card) {
      if (card.length() != 2) {
        throw new IllegalArgumentException("Ivalid card " + card);
      }
      CardValue cardValue = Arrays.stream(CardValue.values())
          .filter(v -> v.getCode() == card.charAt(0))
          .findAny()
          .orElseThrow();
      Suit suit = Suit.valueOf(card.substring(1));
      return new Card(cardValue, suit);
    }
  }

  @Getter
  @RequiredArgsConstructor
  private static class Combination implements Comparable<Combination> {
    private final CombinationType combinationType;
    private final List<Card> combination;
    private final List<Card> offCombination;

    static Combination fromHand(List<Card> hand) {
      CombinationType combinationType = Arrays.stream(CombinationType.values())
          .sorted(Comparator.comparingInt(CombinationType::ordinal).reversed())
          .filter(type -> !type.getCards(hand).isEmpty())
          .findFirst()
          .orElseThrow();
      List<Card> combination = combinationType.getCards(hand);
      List<Card> offCombination = hand.stream()
          .filter(card -> !combination.contains(card))
          .collect(Collectors.toList());
      return new Combination(combinationType, combination, offCombination);
    }

    @Override
    public int compareTo(Combination o) {
      return Comparator.comparing(Combination::getCombinationType)
          .thenComparing((f, s) -> f.combinationType.compareCombinations(f.combination, s.combination))
          .thenComparing((f, s) -> CombinationType.HIGH.compareCombinations(f.offCombination, s.offCombination))
          .compare(this, o);
    }
  }

  enum CombinationType {
    HIGH {
      @Override
      List<Card> getCards(List<Card> hand) {
        return hand;
      }

      @Override
      int compareCombinations(List<Card> firstCombination, List<Card> secondCombination) {
        List<CardValue> first = firstCombination.stream()
            .map(Card::getCardValue)
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        List<CardValue> second = secondCombination.stream()
            .map(Card::getCardValue)
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        for (int i = 0; i < first.size(); i++) {
          int result = first.get(i).compareTo(second.get(i));
          if (result != 0) {
            return result;
          }
        }
        throw new IllegalStateException("Not expecting tie");
      }
    },
    ONE_PAIR {
      @Override
      List<Card> getCards(List<Card> hand) {
        Map<CardValue, List<Card>> byCardValue = hand.stream().collect(Collectors.groupingBy(Card::getCardValue));
        return byCardValue.values().stream()
            .filter(group -> group.size() == 2)
            .findAny()
            .orElse(List.of());
      }

      @Override
      int compareCombinations(List<Card> firstCombination, List<Card> secondCombination) {
        return firstCombination.get(0).getCardValue().compareTo(secondCombination.get(0).getCardValue());
      }
    },
    TWO_PAIRS {
      @Override
      List<Card> getCards(List<Card> hand) {
        Map<CardValue, List<Card>> byCardValue = hand.stream().collect(Collectors.groupingBy(Card::getCardValue));
        List<List<Card>> pairs = byCardValue.values().stream()
            .filter(group -> group.size() == 2)
            .collect(Collectors.toList());
        if (pairs.size() == 2) {
          return pairs.stream().flatMap(Collection::stream).collect(Collectors.toList());
        }
        return List.of();
      }

      @Override
      int compareCombinations(List<Card> firstCombination, List<Card> secondCombination) {
        List<CardValue> first = firstCombination.stream()
            .map(Card::getCardValue)
            .distinct()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        List<CardValue> second = secondCombination.stream()
            .map(Card::getCardValue)
            .distinct()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        for (int i = 0; i < first.size(); i++) {
          int result = first.get(i).compareTo(second.get(i));
          if (result != 0) {
            return result;
          }
        }
        return 0;
      }
    },
    SET {
      @Override
      List<Card> getCards(List<Card> hand) {
        Map<CardValue, List<Card>> byCardValue = hand.stream().collect(Collectors.groupingBy(Card::getCardValue));
        return byCardValue.values().stream()
            .filter(group -> group.size() == 3)
            .findAny()
            .orElse(List.of());
      }

      @Override
      int compareCombinations(List<Card> firstCombination, List<Card> secondCombination) {
        return firstCombination.get(0).getCardValue().compareTo(secondCombination.get(0).getCardValue());
      }
    },
    STRAIGHT {
      @Override
      List<Card> getCards(List<Card> hand) {
        Set<CardValue> values = hand.stream().map(Card::getCardValue).collect(Collectors.toSet());
        if (values.size() != 5) {
          return List.of();
        }
        int max = values.stream().mapToInt(CardValue::ordinal).max().orElseThrow();
        int min = values.stream().mapToInt(CardValue::ordinal).min().orElseThrow();
        if (max - min == 4) {
          return hand;
        }
        return isFiveToAce(values) ? hand : List.of();
      }

      private boolean isFiveToAce(Set<CardValue> values) {
        return values.equals(Set.of(CardValue.ACE, CardValue.TWO, CardValue.THREE, CardValue.FOUR, CardValue.FIVE));
      }

      @Override
      int compareCombinations(List<Card> firstCombination, List<Card> secondCombination) {
        return getStraightStart(firstCombination).compareTo(getStraightStart(secondCombination));
      }

      private CardValue getStraightStart(List<Card> straight) {
        Set<CardValue> values = straight.stream().map(Card::getCardValue).collect(Collectors.toSet());
        if (isFiveToAce(values)) {
          return CardValue.FIVE;
        }
        return values.stream().max(CardValue::compareTo).orElseThrow();
      }
    },
    FLUSH {
      @Override
      List<Card> getCards(List<Card> hand) {
        Map<Suit, List<Card>> bySuit = hand.stream().collect(Collectors.groupingBy(Card::getSuit));
        if (bySuit.size() == 1) {
          return hand;
        }
        return List.of();
      }

      @Override
      int compareCombinations(List<Card> firstCombination, List<Card> secondCombination) {
        return firstCombination.stream().map(Card::getCardValue).max(CardValue::compareTo).orElseThrow()
            .compareTo(secondCombination.stream().map(Card::getCardValue).max(CardValue::compareTo).orElseThrow());
      }
    },
    FULL_HOUSE {
      @Override
      List<Card> getCards(List<Card> hand) {
        if (!ONE_PAIR.getCards(hand).isEmpty() && !SET.getCards(hand).isEmpty()) {
          return hand;
        }
        return List.of();
      }

      @Override
      int compareCombinations(List<Card> firstCombination, List<Card> secondCombination) {
        return Comparator.<List<Card>, CardValue>comparing(combination -> SET.getCards(combination).get(0).getCardValue())
            .thenComparing(combination -> ONE_PAIR.getCards(combination).get(0).getCardValue())
            .compare(firstCombination, secondCombination);
      }
    },
    FOUR {
      @Override
      List<Card> getCards(List<Card> hand) {
        Map<CardValue, List<Card>> byCardValue = hand.stream().collect(Collectors.groupingBy(Card::getCardValue));
        return byCardValue.values().stream()
            .filter(group -> group.size() == 4)
            .findAny()
            .orElse(List.of());
      }

      @Override
      int compareCombinations(List<Card> firstCombination, List<Card> secondCombination) {
        return firstCombination.get(0).getCardValue().compareTo(secondCombination.get(0).getCardValue());
      }
    },
    STRAIGHT_FLUSH {
      @Override
      List<Card> getCards(List<Card> hand) {
        if (!STRAIGHT.getCards(hand).isEmpty() && !FLUSH.getCards(hand).isEmpty()) {
          return hand;
        }
        return List.of();
      }

      @Override
      int compareCombinations(List<Card> firstCombination, List<Card> secondCombination) {
        return STRAIGHT_FLUSH.compareCombinations(firstCombination, secondCombination);
      }
    },
    ROYAL_FLUSH {
      @Override
      List<Card> getCards(List<Card> hand) {
        if (!STRAIGHT_FLUSH.getCards(hand).isEmpty()
            && hand.stream().anyMatch(card -> card.getCardValue() == CardValue.ACE)
            && hand.stream().anyMatch(card -> card.getCardValue() == CardValue.TEN)) {
          return hand;
        }
        return List.of();
      }

      @Override
      int compareCombinations(List<Card> firstCombination, List<Card> secondCombination) {
        return 0;
      }
    };

    abstract List<Card> getCards(List<Card> hand);

    abstract int compareCombinations(List<Card> firstCombination, List<Card> secondCombination);
  }

  @Getter
  @RequiredArgsConstructor
  enum CardValue {
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K'),
    ACE('A');

    private final char code;
  }

  enum Suit {
    S, D, H, C
  }
}
