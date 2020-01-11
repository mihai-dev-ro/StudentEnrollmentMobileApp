package com.nextlevelstudy.services.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

import timber.log.Timber;

public class ApiResponse<T> {


  private static final Pattern PAGE_PATTERN = Pattern.compile("\\bpage=(\\d+)");
  public final int code;
  @Nullable
  public final T body;
  @Nullable
  public final String errorMessage;

  public ApiResponse(Throwable error) {
    code = 500;
    body = null;
    errorMessage = error.getMessage();
  }

  public ApiResponse(retrofit2.Response<T> response) {
    code = response.code();
    if(response.isSuccessful()) {
      body = response.body();
      errorMessage = null;
    } else {
      String message = null;
      if (response.errorBody() != null) {
        try {
          message = response.errorBody().string();
        } catch (IOException ignored) {
          Timber.e(ignored, "error while parsing response");
        }
      }
      if (message == null || message.trim().length() == 0) {
        message = response.message();
      }
      errorMessage = message;
      body = null;
    }
  }

  public boolean isSuccessful() {
    return code >= 200 && code < 300;
  }

}
