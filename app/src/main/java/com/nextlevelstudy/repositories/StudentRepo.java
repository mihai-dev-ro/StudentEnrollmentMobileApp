package com.nextlevelstudy.repositories;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.nextlevelstudy.database.dao.StudentDao;
import com.nextlevelstudy.models.CredentialsWrapper;
import com.nextlevelstudy.models.StudentWithToken;
import com.nextlevelstudy.repositories.utils.AppExecutors;
import com.nextlevelstudy.repositories.utils.NetworkBoundResource;
import com.nextlevelstudy.services.StudentWebService;
import com.nextlevelstudy.services.utils.ApiResponse;
import com.nextlevelstudy.services.utils.Resource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StudentRepo {

  private AppExecutors appExecutors;
  private StudentWebService studentWebService;
  private StudentDao studentDao;

  @Inject
  public StudentRepo(AppExecutors appExecutors,
                     StudentWebService studentWebService,
                     StudentDao studentDao) {

    this.appExecutors = appExecutors;
    this.studentDao = studentDao;
    this.studentWebService = studentWebService;
  }

  public LiveData<Resource<StudentWithToken>> authenticateStudent(String email, String password) {
    return new NetworkBoundResource<StudentWithToken, StudentWithToken>(appExecutors){

      @Override
      protected void saveCallResult(@NonNull StudentWithToken item) {
        studentDao.insertOrUpdateStudent(item);
      }

      @Override
      protected boolean shouldFetch(@Nullable StudentWithToken data) {
        return true;
      }

      @NonNull
      @Override
      protected LiveData<StudentWithToken> loadFromDb() {
        return studentDao.findMyEmail(email);
      }

      @NonNull
      @Override
      protected LiveData<ApiResponse<StudentWithToken>> createCall() {
        return studentWebService.authenticate(new CredentialsWrapper(email, password));
      }
    }.asLiveData();
  }

  public LiveData<Resource<StudentWithToken>> getStudent() {
    return new NetworkBoundResource<StudentWithToken, StudentWithToken>(appExecutors){

      @Override
      protected void saveCallResult(@NonNull StudentWithToken item) {
        studentDao.insertOrUpdateStudent(item);
      }

      @Override
      protected boolean shouldFetch(@Nullable StudentWithToken data) {
        return true;
      }

      @NonNull
      @Override
      protected LiveData<StudentWithToken> loadFromDb() {
        return null;
      }

      @NonNull
      @Override
      protected LiveData<ApiResponse<StudentWithToken>> createCall() {
        return studentWebService.getStudent();
      }
    }.asLiveData();
  }

}

