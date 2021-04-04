package com.fun.club.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import com.fun.club.auth.config.AuthorizationConfig;

import javassist.NotFoundException;

@Service
public class FunClubAuthService {

  private static final Logger LOGGER = LoggerFactory.getLogger(FunClubAuthService.class);

  @Autowired
  private AuthorizationConfig authorizationConfig;
  @Autowired
  private CustomClientDetailService clientDetailsService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private TokenStore tokenStore;

  private DefaultTokenServices defaultTokenServices = null;

  public OAuth2AccessToken authorize(String username, String password) throws Exception {

    String clientId = authorizationConfig.getClientId();
    ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

    OAuth2Request authorizationRequest = new OAuth2Request(null, clientId, //String clientId,
          null, //Collection<? extends GrantedAuthority> authorities
          true, //boolean approved`
          clientDetails.getScope(), //Set<String> scope,
          null, //Set<String> resourceIds,
          null, //String redirectUri,
          null, //Set<String> responseTypes
          null //Map<String, Serializable> extensionProperties
    );

    OAuth2Authentication authenticationRequest = new OAuth2Authentication(authorizationRequest,
          authenticateUser(username, password));
    defaultTokenServices = tokenServices();

    OAuth2AccessToken oAuth2AccessToken = defaultTokenServices.createAccessToken(authenticationRequest);
    //    userService.updateUserLastLogin(username);
    return oAuth2AccessToken;
  }

  private Authentication authenticateUser(String username, String password) {
    Authentication usernamePswdAuthentication = new UsernamePasswordAuthenticationToken(username, password);
    usernamePswdAuthentication = authenticationManager.authenticate(usernamePswdAuthentication);
    return usernamePswdAuthentication;
  }

  private DefaultTokenServices tokenServices() throws Exception {
    if (defaultTokenServices == null) {
      defaultTokenServices = new DefaultTokenServices();

      defaultTokenServices.setSupportRefreshToken(true);
      defaultTokenServices.setTokenStore(tokenStore);
      try {
        defaultTokenServices.afterPropertiesSet();
      } catch (Exception e) {
        LOGGER.error(e.getMessage(), e);
        throw new Exception("Unable to set Token Store. See logs for more details", e);
      }
    }

    return defaultTokenServices;
  }

  /**
   * Remove Access token and respective Access token
   *
   * @param token - Access token needs to be removed from token store
   * @return true if the token was deleted successfully, throw exception otherwise
   * @throws NotFoundException 
   */
  public Boolean revokeToken(String token) throws NotFoundException {
    OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);

    if (oAuth2AccessToken == null) {
      throw new NotFoundException("Access token not found");
    }
    OAuth2RefreshToken oAuth2RefreshToken = oAuth2AccessToken.getRefreshToken();
    tokenStore.removeAccessToken(oAuth2AccessToken);
    LOGGER.debug("Token successfully revoked : " + token);
    if (oAuth2RefreshToken == null) {
      throw new NotFoundException("Refresh token not found");
    }
    tokenStore.removeRefreshToken(oAuth2RefreshToken);
    return true;
  }
}
