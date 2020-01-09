package com.nextlevelstudy.di.module;

import com.nextlevelstudy.services.StudentWebService;
import com.nextlevelstudy.services.UniversityWebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {
  ViewModelModule.class,
  NetworkModule.class
})
public class AppModule {

  @Provides
  @Singleton
  StudentWebService provideStudentWebService(Retrofit retrofit) {
    return retrofit.create(StudentWebService.class);
  }

  @Provides
  @Singleton
  UniversityWebService provideUniversityWebService(Retrofit retrofit) {
    return retrofit.create(UniversityWebService.class);
  }



}
