package com.nextlevelstudy.services.base;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nextlevelstudy.AppConstants.BASE_URL;

public class WebServiceManager {
  private static Retrofit retrofit;

  public static Retrofit getRetrofitInstance() {
    if (retrofit == null) {
      retrofit = new retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    }
    return retrofit;
  }
}
