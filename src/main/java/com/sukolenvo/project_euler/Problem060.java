package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Data;
import lombok.RequiredArgsConstructor;

public class Problem060 {

  private Map<Integer, Boolean> primeCache = new HashMap<>();

  int run(int groupSize) {
    Set<Group> groups = new HashSet<>();
    for (int i = 2; ; i++) {
      if (isPrime(i)) {
        List<Group> newGroups = new ArrayList<>();
        for (Group group : groups) {
          if (isConcatenatePrimes(group, i)) {
            newGroups.add(group.with(i));
          }
        }
        for (Group newGroup : newGroups) {
          if (newGroup.members.size() == groupSize) {
            return newGroup.members.stream().mapToInt(Integer::intValue).sum();
          }
        }
        groups.addAll(newGroups);
        groups.add(new Group(i));
      }
    }
  }

  private boolean isPrime(int candidate) {
    return primeCache.compute(candidate, (key, value) -> value == null
        ? BigInteger.valueOf(candidate).isProbablePrime(100) : value);
  }

  private boolean isConcatenatePrimes(Group group, int prime) {
    return group.members.stream().allMatch(member -> isConcatenatePrime(member, prime));
  }

  boolean isConcatenatePrime(int first, int second) {
    return isPrime((int) Math.pow(10, Common.getDigitCount(first)) * second + first)
        && isPrime((int) Math.pow(10, Common.getDigitCount(second)) * first + second);
  }

  @Data
  @RequiredArgsConstructor
  private static class Group {
    private final Set<Integer> members;

    Group(int prime) {
      members = Set.of(prime);
    }

    private Group with(int prime) {
      return new Group(Stream.concat(members.stream(), Stream.of(prime)).collect(Collectors.toSet()));
    }
  }
}
