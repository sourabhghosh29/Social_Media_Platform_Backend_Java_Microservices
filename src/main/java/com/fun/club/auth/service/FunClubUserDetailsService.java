package com.fun.club.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fun.club.auth.domain.repository.UserRepository;

/**
 * Created by daz on 29/06/2017.
 */
@Service
public class FunClubUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  public FunClubUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository
          .findByUsername(username)
          .map(user -> new User(user.getUsername(), user.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_USER")))
          .orElseThrow(() -> new UsernameNotFoundException("Could not find " + username));
  }
}
