package com.nextlevelstudy.view_models.login;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nextlevelstudy.models.StudentWithToken;
import com.nextlevelstudy.repositories.StudentRepo;
import com.nextlevelstudy.services.utils.AbsentLiveData;
import com.nextlevelstudy.services.utils.Resource;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

  private final StudentRepo studentRepo;

  private LiveData<Resource<StudentWithToken>> loginResult = AbsentLiveData.create();

  @Inject
  LoginViewModel(@NonNull StudentRepo studentRepo) {
    this.studentRepo = studentRepo;
  }

  public void authenticate(String email, String password){
    loginResult = this.studentRepo.authenticateStudent(email, password);
  }

  public LiveData<Resource<StudentWithToken>> getStudent() { return loginResult; }
}
