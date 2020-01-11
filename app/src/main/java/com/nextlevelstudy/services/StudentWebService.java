package com.nextlevelstudy.services;

import androidx.lifecycle.LiveData;

import com.nextlevelstudy.models.CredentialsWrapper;
import com.nextlevelstudy.models.StudentWithToken;
import com.nextlevelstudy.services.utils.ApiResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface StudentWebService {

  @POST("/users/login")
  LiveData<ApiResponse<StudentWithToken>> authenticate(
    @Body CredentialsWrapper credentialsWrapper);

  @GET("/user")
  LiveData<ApiResponse<StudentWithToken>> getStudent();
}
