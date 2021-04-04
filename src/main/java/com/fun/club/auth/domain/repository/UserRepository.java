package com.fun.club.auth.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fun.club.auth.domain.entity.UserDetails;

/**
 * Created by daz on 29/06/2017.
 */
public interface UserRepository extends JpaRepository<UserDetails, Long> {

  Optional<UserDetails> findByUsername(String username);
}
