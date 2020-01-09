package com.nextlevelstudy.view_models.university;

import android.graphics.Movie;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.nextlevelstudy.models.University;
import com.nextlevelstudy.repositories.UniversityRepo;
import com.nextlevelstudy.services.utils.AbsentLiveData;
import com.nextlevelstudy.services.utils.Resource;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import timber.log.Timber;

public class SearchViewModel extends ViewModel {

  private final MutableLiveData<String> query = new MutableLiveData<>();
  private final LiveData<Resource<List<University>>> searchResults;

  @Inject
  SearchViewModel(@NonNull UniversityRepo universityRepo) {
    searchResults = Transformations.switchMap(query, search -> {

      if (search == null || search.trim().length() == 0) {
        return AbsentLiveData.create();
      } else {
        return universityRepo.searchUniversity(search);
      }
    });
  }

  @VisibleForTesting
  public LiveData<Resource<List<University>>> getSearchResults() {
    return searchResults;
  }

  public void setSearchQuery(@NonNull String originalInput) {
    String input = originalInput.toLowerCase(Locale.getDefault()).trim();
    if (Objects.equals(input, query.getValue())) {
      return;
    }
    query.setValue(input);
  }


  @Override
  protected void onCleared() {
    super.onCleared();
    Timber.d("@onCleared called");
  }
}
