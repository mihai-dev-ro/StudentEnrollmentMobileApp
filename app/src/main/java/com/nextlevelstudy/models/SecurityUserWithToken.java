package com.nextlevelstudy.models;

public class SecurityUserWithToken extends SecurityUser {
  public String authenticationToken;

  public String getAuthenticationToken() { return authenticationToken; }
  public String setAuthentciationToken(String value) { this.authenticationToken = value; }
}
