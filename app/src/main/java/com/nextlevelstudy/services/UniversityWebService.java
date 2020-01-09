package com.nextlevelstudy.services;

import android.graphics.Movie;

import androidx.lifecycle.LiveData;

import com.nextlevelstudy.models.UniversityListWSResult;
import com.nextlevelstudy.services.utils.ApiResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UniversityWebService {

  @GET("universities_public_api?")
  LiveData<ApiResponse<UniversityListWSResult>> getTopUniversities();

  @GET("universities_public_api/search?")
  LiveData<ApiResponse<UniversityListWSResult>> searchMovies(@Query("query") String query);
}
