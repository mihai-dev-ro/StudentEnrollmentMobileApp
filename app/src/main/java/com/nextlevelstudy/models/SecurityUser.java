package com.nextlevelstudy.models;

public class SecurityUser {
  public String email;
  public String password;
  public String authenticationToken;

  public String getEmail() { return email; }
  public String setEmail(String value) { this.email = value; }

  public String getPassword() { return password; }
  public String setPassword(String value) { this.password = value; }
}

