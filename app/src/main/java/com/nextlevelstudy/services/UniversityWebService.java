package com.nextlevelstudy.services;

import androidx.lifecycle.LiveData;

import com.nextlevelstudy.models.University;
import com.nextlevelstudy.services.utils.ApiResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UniversityWebService {

  @GET("universities_public_api?")
  LiveData<ApiResponse<List<University>>> getTopUniversities();

  @GET("universities_public_api?")
  LiveData<ApiResponse<List<University>>> searchMovies(@Query("query") String query);
}
