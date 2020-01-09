package com.nextlevelstudy.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.nextlevelstudy.data.Result;
import com.nextlevelstudy.data.model.LoggedInUser;
import com.nextlevelstudy.models.SecurityUser;
import com.nextlevelstudy.models.SecurityUserWithToken;
import com.nextlevelstudy.services.SecurityUserWebService;
import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.response.ArticleResponse;
import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.retrofit.ApiRequest;
import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.retrofit.RetrofitRequest;

import java.util.Date;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecurityUserRepo {

  private static int FRESH_TIMEOUT_IN_MINUTES = 1;

  private final SecurityUserWebService webservice;
  //private final SecurityUserDao securityUserDao;
  private final Executor executor;


  private static final String TAG = ArticleRepository.class.getSimpleName();
  private ApiRequest apiRequest;

  public SecurityUserRepo() {
    apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
  }

  public LiveData<ArticleResponse> getMovieArticles(String query, String key) {
    final MutableLiveData<ArticleResponse> data = new MutableLiveData<>();
    apiRequest.getMovieArticles(query, key)
      .enqueue(new Callback<ArticleResponse>() {


        @Override
        public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
          Log.d(TAG, "onResponse response:: " + response);



          if (response.body() != null) {
            data.setValue(response.body());

            Log.d(TAG, "articles total result:: " + response.body().getTotalResults());
            Log.d(TAG, "articles size:: " + response.body().getArticles().size());
            Log.d(TAG, "articles title pos 0:: " + response.body().getArticles().get(0).getTitle());
          }
        }

        @Override
        public void onFailure(Call<ArticleResponse> call, Throwable t) {
          data.setValue(null);
        }
      });
    return data;
  }

  public LiveData<SecurityUserWithToken> login(String email, final String password) {
    // handle login
//    Result<LoggedInUser> result = dataSource.login(username, password);
//    if (result instanceof Result.Success) {
//      setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
//    }
//    return result;

    executor.execute(() -> {
      // Check if user was fetched recently
      boolean userExists = (userSecurityDao.hasUser(email, getMaxRefreshTime(new Date())) != null);
      // If user have to be updated
      if (!userExists) {
        webservice.authenticateUser(email, password).enqueue(new Callback<SecurityUserWithToken>() {
          @Override
          public void onResponse(Call<User> call, Response<User> response) {
            Log.e("TAG", "DATA REFRESHED FROM NETWORK");
            Toast.makeText(App.context, "Data refreshed from network !", Toast.LENGTH_LONG).show();
            executor.execute(() -> {
              User user = response.body();
              user.setLastRefresh(new Date());
              userDao.save(user);
            });
          }

          @Override
          public void onFailure(Call<User> call, Throwable t) { }
        });
      }
    });
  }

}

