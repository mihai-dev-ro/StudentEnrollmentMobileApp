package com.nextlevelstudy.models;

public class CredentialsWrapper {
  User user;

  public class User {
    public String email;
    public String password;

    public User(String email, String password) {
      this.email = email;
      this.password = password;
    }
  }

  public CredentialsWrapper(String email, String password) {
    this.user = new User(email, password);
  }
}
