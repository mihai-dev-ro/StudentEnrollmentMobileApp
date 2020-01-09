package com.nextlevelstudy.services;

import androidx.lifecycle.LiveData;

import com.nextlevelstudy.models.SecurityUser;
import com.nextlevelstudy.models.SecurityUserWithToken;
import com.nextlevelstudy.services.utils.ApiResponse;


public interface SecurityUserWebService {

  LiveData<ApiResponse<SecurityUserWithToken>> authenticate(SecurityUser);
}
