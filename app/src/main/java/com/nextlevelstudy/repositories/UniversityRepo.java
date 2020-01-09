package com.nextlevelstudy.repositories;

import android.graphics.Movie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

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

  private UniversityWebService mUniversityWebService;
  private TmdbDb mTmdbDb;
  private UniversityDao mUniversityDao;
  private final AppExecutors mAppExecutors;

  @Inject
  UniversityRepo(AppExecutors appExecutors, UniversityWebService universityWebService,
                 UniversityDao universityDao) {

    mUniversityWebService = universityWebService;
    mAppExecutors = appExecutors;
    mUniversityDao = universityDao;
  }

  public LiveData<Resource<List<University>>> getTopUniversities() {
    return new NetworkBoundResource<List<University>, UniversityListWSResult>(mAppExecutors) {
      @Override
      protected void saveCallResult(@NonNull UniversityListWSResult item) {
        mUniversityDao.insertUniversities(item.getResults());
      }

      @Override
      protected boolean shouldFetch(@Nullable List<University> data) {
        return data == null || data.isEmpty();
      }

      @NonNull
      @Override
      protected LiveData<List<University>> loadFromDb() {
        return mUniversityDao.findAll();

      }

      @NonNull
      @Override
      protected LiveData<ApiResponse<UniversityListWSResult>> createCall() {
        return mUniversityWebService.getTopUniversities();
      }
    }.asLiveData();
  }

  public LiveData<Resource<List<University>>> searchUniversity(String query) {
    return new NetworkBoundResource<List<University>, UniversityListWSResult>(mAppExecutors) {
      @Override
      protected void saveCallResult(@NonNull UniversityListWSResult item) {
        mUniversityDao.insertUniversities(item.getResults());
      }

      @Override
      protected boolean shouldFetch(@Nullable List<University> data) {
        return data == null || data.isEmpty();
      }

      @NonNull
      @Override
      protected LiveData<List<University>> loadFromDb() {
        //Fetch from the db
        return mUniversityDao.searchUniversitiesByNameOrCountryName(query);
      }

      @NonNull
      @Override
      protected LiveData<ApiResponse<UniversityListWSResult>> createCall() {
        return mUniversityWebService.searchMovies(query);
      }
    }.asLiveData();
  }

  public LiveData<Resource<Boolean>> searchNextPage(String query) {
    return null;
  }
}
