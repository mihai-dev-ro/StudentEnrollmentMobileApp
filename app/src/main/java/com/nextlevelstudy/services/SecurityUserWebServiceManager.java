package com.nextlevelstudy.services;

import androidx.annotation.Nullable;

public class SecurityUserWebServiceManager {
  SecurityUserWebService service = null;

  @Nullable
  public SecurityUserWebService get() {
    return service;
  }

  public void set(SecurityUserWebService myService) {
    this.service = myService;
  }
}