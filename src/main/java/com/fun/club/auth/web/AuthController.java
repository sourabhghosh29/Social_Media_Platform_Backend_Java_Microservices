package com.fun.club.auth.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fun.club.auth.service.FunClubAuthService;
import com.fun.club.dto.EmployeeDto;
import com.fun.club.dto.TokenDetailsDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = { "/token/v1" })
public class AuthController {

  /**
   * Specific access point path to authorize
   */
  public static final String AUTHORIZE_ACCESS_POINT = "/authorize";

  /**
   * Specific access point path to refresh token
   */
  public static final String REFRESH_TOKEN_ACCESS_POINT = "/refresh_token";

  /**
   * Specific access point path to revoke token
   */
  public static final String REVOKE_TOKEN_ACCESS_POINT = "/revoke_token";

  @Autowired
  FunClubAuthService funClubAuthService;

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

  /**
   * Authenticates and authorizes the user based on the provided credentials
   *
   * @param username The user unique identifier
   * @param password The password for the user
   * @return token and refresh token
   * @throws Exception 
   */
  @RequestMapping(value = AUTHORIZE_ACCESS_POINT, method = RequestMethod.POST)
  @ApiOperation(value = "Returns the token if the user is authenticated")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful authorization. Returns the OAuth token", response = OAuth2AccessToken.class),
        @ApiResponse(code = 401, message = "Unauthorized to use the service") })
  public ResponseEntity<OAuth2AccessToken> authorize(
        @ApiParam(name = "username", value = "user", required = true) @RequestHeader(value = "username", required = true) String username,
        @ApiParam(name = "password", value = "password of the user", required = true) @RequestHeader(value = "password", required = true) String password)
        throws Exception {

    LOGGER.debug("Authenticating username: {}", username);
    OAuth2AccessToken accessToken;

    accessToken = funClubAuthService.authorize(username, password);
    return new ResponseEntity<OAuth2AccessToken>(accessToken, HttpStatus.OK);

  }

  @ApiOperation(value = "Revoke the security token", notes = "Revoke the security token using the supplied token")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful authorization revoked", response = String.class),
        @ApiResponse(code = 401, message = "Not possible to revoke the token", response = String.class) })
  @RequestMapping(value = REVOKE_TOKEN_ACCESS_POINT, method = RequestMethod.POST)
  public ResponseEntity<String> revokeToken(
    //    @ApiParam(name = "token", value = "security token to revoke", required = true) @RequestHeader("token") String token)
		  @ApiParam(name = "token", value = "security token to revoke", required = true) @Valid @RequestBody final TokenDetailsDto tokenDetailsDto)
        throws NotFoundException {

    LOGGER.debug("Revoking Token: {}", tokenDetailsDto.getAccess_token());
    System.out.println("Revoking Token: {}"+ tokenDetailsDto.getAccess_token());
    if (funClubAuthService.revokeToken(tokenDetailsDto.getAccess_token())) {
    	System.out.println("Token Revoked successfully");
      return new ResponseEntity<String>(HttpStatus.OK);
    }
    return new ResponseEntity<String>("The token could not be revoked as it does not exist", HttpStatus.UNAUTHORIZED);
  }
}
