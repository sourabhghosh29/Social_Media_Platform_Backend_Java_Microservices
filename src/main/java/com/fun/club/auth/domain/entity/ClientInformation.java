package com.fun.club.auth.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_client_details")
public class ClientInformation {

  @Id
  @Column(name = "client_id", nullable = false, unique = true)
  private String clientId;

  @Column(name = "client_secret", nullable = false)
  private String clientSecret;

  @Column(name = "access_token_validity", nullable = false)
  private Integer accessTokenValidity;

  @Column(name = "refresh_token_validity", nullable = false)
  private Integer refreshTokenValidity;

  @Column(name = "authorized_grant_types")
  private String grantType;

  @Column(name = "scope")
  private String clientScope;

  /*package-private*/ ClientInformation() {
    super();
  }

  public ClientInformation(String clientId, String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public Integer getAccessTokenValidity() {
    return accessTokenValidity;
  }

  public void setAccessTokenValidity(Integer accessTokenValidity) {
    this.accessTokenValidity = accessTokenValidity;
  }

  public Integer getRefreshTokenValidity() {
    return refreshTokenValidity;
  }

  public void setRefreshTokenValidity(Integer refreshTokenValidity) {
    this.refreshTokenValidity = refreshTokenValidity;
  }

  public String getGrantType() {
    return grantType;
  }

  public void setGrantType(String grantType) {
    this.grantType = grantType;
  }

  public String getClientScope() {
    return clientScope;
  }

  public void setClientScope(String clientScope) {
    this.clientScope = clientScope;
  }

}
