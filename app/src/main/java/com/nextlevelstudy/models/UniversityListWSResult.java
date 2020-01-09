package com.nextlevelstudy.models;

import android.graphics.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UniversityListWSResult {

  private List<University> results = new ArrayList<>();

  /**
   * @return The results
   */
  public List<University> getResults() {
    return results;
  }

  /**
   * @param results The results
   */
  public void setResults(List<University> results) {
    this.results = results;
  }
}
