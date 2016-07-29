package com.randy073.findmehere;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigHelper {



  public List<Float> getSearchDistanceAvailableOptions() {
    final List<Float> defaultOptions = Arrays.asList(250.0f, 1000.0f, 2000.0f, 5000.0f);

    return defaultOptions;
  }

}
