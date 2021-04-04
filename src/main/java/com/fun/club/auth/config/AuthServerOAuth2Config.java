package com.fun.club.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.util.SocketUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fun.club.auth.web.AuthController;
import com.google.common.collect.ImmutableList;

/**
 * Created by daz on 27/06/2017.
 */
@EnableAuthorizationServer
@Configuration
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthServerOAuth2Config.class);

  private final AuthenticationManager authenticationManager;
  private final AppConfig appConfig;

  @Autowired
  public AuthServerOAuth2Config(AuthenticationManager authenticationManager, AppConfig appConfig) {
    this.authenticationManager = authenticationManager;
    this.appConfig = appConfig;
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.jdbc(appConfig.dataSource());
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    /*
     * Allow our tokens to be delivered from our token access point as well as for tokens
     * to be validated from this point
     */
    security.allowFormAuthenticationForClients();
    security.checkTokenAccess("permitAll()");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
          .authenticationManager(authenticationManager)
          .tokenServices(tokenServices());
    //          .tokenStore(appConfig.tokenStore()); // Persist the tokens in the database
  }

  @Configuration
  @EnableResourceServer
  protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Bean
    public Integer port() {
      return SocketUtils.findAvailableTcpPort();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests().antMatchers("/**/check_token").permitAll();
      http.authorizeRequests().antMatchers("/**" + AuthController.REFRESH_TOKEN_ACCESS_POINT).permitAll();
      http.authorizeRequests().antMatchers("/**" + AuthController.AUTHORIZE_ACCESS_POINT).permitAll();
      http.authorizeRequests().antMatchers("/**" + AuthController.REVOKE_TOKEN_ACCESS_POINT).permitAll();
      http.authorizeRequests().antMatchers("/**/forgot_password/").permitAll();
      http.authorizeRequests().antMatchers("/**").authenticated();
      http.cors();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
      final CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(ImmutableList.of("*"));
      configuration.setAllowedMethods(ImmutableList.of("HEAD",
            "GET", "POST", "PUT", "DELETE", "PATCH"));
      // setAllowCredentials(true) is important, otherwise:
      // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
      configuration.setAllowCredentials(true);
      // setAllowedHeaders is important! Without it, OPTIONS preflight request
      // will fail with 403 Invalid CORS request
      configuration.setAllowedHeaders(
            ImmutableList.of("Authorization", "Cache-Control", "Content-Type", "username", "password", "X-CSRF-TOKEN","token"));
      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
    }
  }

  //  @Configuration
  //  @EnableWebSecurity
  //  protected static class SecurityConfig extends WebSecurityConfigurerAdapter {
  //
  //    @Override
  //    protected void configure(HttpSecurity http) throws Exception {
  //      http.csrf().disable();
  //      //      http.authorizeRequests().antMatchers("/**/check_token").permitAll();
  //      //      http.authorizeRequests().antMatchers("/**" + AuthController.REFRESH_TOKEN_ACCESS_POINT).permitAll();
  //      //      http.authorizeRequests().antMatchers("/**" + AuthController.AUTHORIZE_ACCESS_POINT).permitAll();
  //      //      http.authorizeRequests().antMatchers("/**" + AuthController.REVOKE_TOKEN_ACCESS_POINT).permitAll();
  //      http.cors();
  //    }
  //
  //    @Override
  //    public void configure(WebSecurity web) throws Exception {
  //      web.ignoring().antMatchers("/**" + AuthController.REFRESH_TOKEN_ACCESS_POINT);
  //      web.ignoring().antMatchers("/**" + AuthController.AUTHORIZE_ACCESS_POINT);
  //      web.ignoring().antMatchers("/**" + AuthController.REVOKE_TOKEN_ACCESS_POINT);
  //      web.ignoring().antMatchers("/**/check_token");
  //    }
  //
  //    @Bean
  //    public CorsConfigurationSource corsConfigurationSource() {
  //      final CorsConfiguration configuration = new CorsConfiguration();
  //      configuration.setAllowedOrigins(ImmutableList.of("*"));
  //      configuration.setAllowedMethods(ImmutableList.of("HEAD",
  //            "GET", "POST", "PUT", "DELETE", "PATCH"));
  //      // setAllowCredentials(true) is important, otherwise:
  //      // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
  //      configuration.setAllowCredentials(true);
  //      // setAllowedHeaders is important! Without it, OPTIONS preflight request
  //      // will fail with 403 Invalid CORS request
  //      configuration.setAllowedHeaders(
  //            ImmutableList.of("Authorization", "Cache-Control", "Content-Type", "username", "password", "X-CSRF-TOKEN"));
  //      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
  //      source.registerCorsConfiguration("/**", configuration);
  //      return source;
  //    }
  //
  //  }

  private DefaultTokenServices tokenServices() throws Exception {
    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();

    defaultTokenServices.setSupportRefreshToken(true);
    defaultTokenServices.setTokenStore(appConfig.tokenStore());
    defaultTokenServices.setAccessTokenValiditySeconds(10);

    try {
      defaultTokenServices.afterPropertiesSet();
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new Exception("Unable to set Token Store. See logs for more details", e);
    }

    return defaultTokenServices;
  }

  @Configuration
  public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
            .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
    }
  }
}
