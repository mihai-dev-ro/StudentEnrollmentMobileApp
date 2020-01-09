package com.nextlevelstudy.repositories;

import android.graphics.Movie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.nextlevelstudy.database.StudentEnrollmentDb;
import com.nextlevelstudy.database.dao.UniversityDao;
import com.nextlevelstudy.models.University;
import com.nextlevelstudy.models.UniversityListWSResult;
import com.nextlevelstudy.repositories.utils.AppExecutors;
import com.nextlevelstudy.repositories.utils.NetworkBoundResource;
import com.nextlevelstudy.services.UniversityWebService;
import com.nextlevelstudy.services.utils.ApiResponse;
import com.nextlevelstudy.services.utils.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UniversityRepo {

  private UniversityWebService universityWebService;
  private UniversityDao universityDao;
  private AppExecutors appExecutors;

  @Inject
  UniversityRepo(AppExecutors appExecutors,
                 UniversityWebService universityWebService,
                 UniversityDao universityDao) {

    universityWebService = universityWebService;
    appExecutors = appExecutors;
    universityDao = universityDao;
  }

  public LiveData<Resource<List<University>>> getTopUniversities() {
    return new NetworkBoundResource<List<University>, UniversityListWSResult>(appExecutors) {
      @Override
      protected void saveCallResult(@NonNull UniversityListWSResult item) {
        universityDao.insertUniversities(item.getResults());
      }

      @Override
      protected boolean shouldFetch(@Nullable List<University> data) {
        return data == null || data.isEmpty();
      }

      @NonNull
      @Override
      protected LiveData<List<University>> loadFromDb() {
        return universityDao.findAll();

      }

      @NonNull
      @Override
      protected LiveData<ApiResponse<UniversityListWSResult>> createCall() {
        return universityWebService.getTopUniversities();
      }
    }.asLiveData();
  }

  public LiveData<Resource<List<University>>> searchUniversity(String query) {
    return new NetworkBoundResource<List<University>, UniversityListWSResult>(appExecutors) {
      @Override
      protected void saveCallResult(@NonNull UniversityListWSResult item) {
        universityDao.insertUniversities(item.getResults());
      }

      @Override
      protected boolean shouldFetch(@Nullable List<University> data) {
        return data == null || data.isEmpty();
      }

      @NonNull
      @Override
      protected LiveData<List<University>> loadFromDb() {
        //Fetch from the db
        return universityDao.searchUniversitiesByNameOrCountryName(query);
      }

      @NonNull
      @Override
      protected LiveData<ApiResponse<UniversityListWSResult>> createCall() {
        return universityWebService.searchMovies(query);
      }
    }.asLiveData();
  }

  public LiveData<Resource<Boolean>> searchNextPage(String query) {
    return null;
  }
}
