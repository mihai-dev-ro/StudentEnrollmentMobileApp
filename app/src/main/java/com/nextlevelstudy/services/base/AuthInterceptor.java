package com.nextlevelstudy.services.base;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.nextlevelstudy.R;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This class add information (API Key) to {@link okhttp3.OkHttpClient} which is passed in
 * {@link com.nextlevelstudy.di.module.NetworkModule#okHttpClient(HttpLoggingInterceptor)}
 * which is required when making a request. This will ensure that all requests are made with the API key
 *
 */

@Singleton
public class AuthInterceptor implements Interceptor {

  private String jwtToken;

  /**
   * Default constructor.
   */
  @Inject
  public AuthInterceptor() {}

  public void setJwtToken(String jwtToken) {
    this.jwtToken = jwtToken;
  }

  @Override
  public Response intercept(@NonNull Chain chain) throws IOException {
    Request original = chain.request();

    if (original.url().encodedPath().contains("/users/login")&& original.method().equals("post")
      || (original.url().encodedPath().contains("/users") && original.method().equals("post"))
    ) {
      return  chain.proceed(original);
    }

    // insert the bearer token
    HttpUrl originalHttpUrl = original.url();
    Request.Builder requestBuilder = original.newBuilder()
      .header("Authorization","Bearer " + jwtToken)
      .url(originalHttpUrl);

    return chain.proceed(requestBuilder.build());
  }
}

