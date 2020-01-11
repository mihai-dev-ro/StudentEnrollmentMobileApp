package com.nextlevelstudy.database.dao.helpers;

import androidx.room.TypeConverter;
import androidx.room.util.StringUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DbTypeConverters {

  @TypeConverter
  public static List<String> stringToListOfStrings(String data) {
    if (data == null) {
      return Collections.emptyList();
    }
    return Arrays.asList(data.split("\\|"));
  }

  @TypeConverter
  public static String listOfStringsToString(List<String> stringItems) {
    String value = "";

    for (String item :stringItems)
      value += item + "|";

    return value;
  }
}
