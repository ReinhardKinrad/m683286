package com.rdlab.marketplace.util;

public class StringUtil {

  public static String putPercentCharAtTheBeginningAndEndOfTheLine(String line) {
    var stringBuilder = new StringBuilder(line);
    stringBuilder.insert(0, '%').insert(stringBuilder.length(), '%');
    return stringBuilder.toString();
  }

}
