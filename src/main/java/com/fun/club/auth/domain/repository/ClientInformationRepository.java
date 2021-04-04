package com.fun.club.auth.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fun.club.auth.domain.entity.ClientInformation;

public interface ClientInformationRepository extends JpaRepository<ClientInformation, Integer> {

  public ClientInformation findByClientId(String clientId);

}
