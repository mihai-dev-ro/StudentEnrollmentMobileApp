package com.nextlevelstudy.models;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;


@Entity(indices = {@Index("email")},
  primaryKeys = {"email"})
public class StudentWithToken {

  @NonNull
  public String email;
  public String name;
  public String education;
  public String token;

  public StudentWithToken(String name, String email, String education, String token) {
    this.name = name;
    this.email = email;
    this.education = education;
    this.token = token;
  }

}
