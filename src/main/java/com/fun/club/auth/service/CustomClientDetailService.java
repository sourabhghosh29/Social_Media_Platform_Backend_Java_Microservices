package com.fun.club.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.fun.club.auth.domain.entity.ClientInformation;
import com.fun.club.auth.domain.repository.ClientInformationRepository;

/**
 * Custom Service Implementation of a service that provides the details about an OAuth2 client.
 *
 * @author esyeghu
 */
@Service
public class CustomClientDetailService implements ClientDetailsService {

  @Autowired
  ClientInformationRepository clientInformationRepo;

  /**
   * Load a client by the client id. This method must not return null.
   *
   * @param clientId The client id.
   * @return The client details (never null).
   * @throws ClientRegistrationException If the client account is locked, expired, disabled, or invalid for any other reason.
   */
  @Override
  public ClientDetails loadClientByClientId(String clientId) {

    ClientInformation clientInfo = clientInformationRepo.findByClientId(clientId);
    if (clientInfo == null) {
      throw new ClientRegistrationException("Client Id : " + clientId + " is either invalid or not available");
    }

    BaseClientDetails clientDetails = new BaseClientDetails(clientId, null, clientInfo.getClientScope(),
          clientInfo.getGrantType(), null, null);
    clientDetails.setClientSecret(clientInfo.getClientSecret());
    clientDetails.setAccessTokenValiditySeconds(clientInfo.getAccessTokenValidity());
    clientDetails.setRefreshTokenValiditySeconds(clientInfo.getRefreshTokenValidity());
    return clientDetails;
  }
}
