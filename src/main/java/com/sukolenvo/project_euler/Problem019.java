package com.sukolenvo.project_euler;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class Problem019 {

  int run() {
    int result = 0;
    LocalDate date = LocalDate.of(1901, Month.JANUARY, 1);
    while (date.isBefore(LocalDate.of(2000, Month.DECEMBER, 31))) {
      if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
        result++;
      }
      date = date.plusMonths(1);
    }
    return result;
  }
}
