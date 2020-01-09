package com.nextlevelstudy.services.implementations;

import androidx.lifecycle.LiveData;

import com.nextlevelstudy.models.SecurityUser;
import com.nextlevelstudy.models.SecurityUserWithToken;
import com.nextlevelstudy.services.SecurityUserWebService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class SecurityUserWebServiceImpl implements SecurityUserWebService {


  @Override
  public LiveData<ApiResponse<SecurityUserWithToken>> authenticate() {
    return null;
  }
}
