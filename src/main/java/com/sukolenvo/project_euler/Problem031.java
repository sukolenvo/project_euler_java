package com.sukolenvo.project_euler;

public class Problem031 {

  int run() {
    int[] coinValue = {200, 100, 50, 20, 10, 5, 2, 1};
    return countWays(coinValue, 200);
  }

  public static int countWays(int[] coinTypes, int target) {
    assert coinTypes[0] == target;
    int[] coinCount = new int[coinTypes.length];
    coinCount[0] = 1;
    int result = 1;
    while (coinCount[coinCount.length - 1] != target) {
      for (int i = coinCount.length - 2; ; i--) {
        if (coinCount[i] > 0) {
          coinCount[i]--;
          coinCount[coinCount.length - 1] += coinTypes[i];
          normalise(coinTypes, coinCount, i + 1);
          result++;
          break;
        }
      }
    }
    return result;
  }

  private static void normalise(int[] coinValue, int[] coinCount, int from) {
    int sum = 0;
    for (int i = from; i < coinCount.length; i++) {
      sum += coinValue[i] * coinCount[i];
      coinCount[i] = 0;
    }
    for (int i = from; i < coinCount.length && sum > 0; i++) {
      coinCount[i] = sum / coinValue[i];
      sum -= coinCount[i] * coinValue[i];
    }
  }
}
