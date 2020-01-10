package com.nextlevelstudy.view_models.login;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.nextlevelstudy.models.EmailAndPassword;
import com.nextlevelstudy.models.StudentWithToken;
import com.nextlevelstudy.repositories.StudentRepo;
import com.nextlevelstudy.services.utils.AbsentLiveData;
import com.nextlevelstudy.services.utils.Resource;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

  private LiveData<Resource<StudentWithToken>> loginResult;
  private MutableLiveData<EmailAndPassword> credentials = new MutableLiveData<>();
  private MutableLiveData<String> password;

  @Inject
  LoginViewModel(@NonNull StudentRepo studentRepo) {

    loginResult = AbsentLiveData.create();
    loginResult = Transformations.switchMap(credentials, (emailAndPassword) -> {
      if (emailAndPassword == null || emailAndPassword.email.trim().length() == 0
      || emailAndPassword.password.trim().length() == 0) {
        return AbsentLiveData.create();
      } else {
        return studentRepo.authenticateStudent(emailAndPassword.email, emailAndPassword.password);
      }
    });
  }

  public void authenticate(String email, String password){
    credentials.setValue(new EmailAndPassword(email, password));
  }

  public LiveData<Resource<StudentWithToken>> getStudent() { return loginResult; }
}
