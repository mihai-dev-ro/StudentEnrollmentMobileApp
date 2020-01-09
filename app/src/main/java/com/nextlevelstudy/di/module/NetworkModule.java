package com.nextlevelstudy.di.module;

import androidx.annotation.NonNull;

import com.nextlevelstudy.BuildConfig;
import com.nextlevelstudy.repositories.utils.LiveDataCallAdapterFactory;
import com.nextlevelstudy.services.base.AuthInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nextlevelstudy.AppConstants.*;

public class NetworkModule {

  @Provides
  @Singleton
  HttpLoggingInterceptor provideOkHttpInterceptors() {
    return new HttpLoggingInterceptor().
      setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
  }

  /**
   * Configure OkHttpClient. This helps us override some of the default configuration. Like the
   * connection timeout.
   *
   * @return OkHttpClient
   */
  @Provides
  @Singleton
  OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor,
                            AuthInterceptor jwtInterceptor) {

    return new OkHttpClient.Builder()
      .addInterceptor(jwtInterceptor)
      .addInterceptor(httpLoggingInterceptor)
      .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
      .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
      .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
      .build();
  }


  @Provides
  @Singleton
  Retrofit provideRetrofitClient(@NonNull OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create()) // Serialize Objects
      .addCallAdapterFactory(new LiveDataCallAdapterFactory())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Set call to return {@link Observable}
      .build();
  }
}
