package com.nextlevelstudy.models;

public class CredentialsWrapper {
  EmailAndPassword user;

  public CredentialsWrapper(String email, String password) {
    this.user = new EmailAndPassword(email, password);
  }
}
