package com.trujidev.forohub.foro_hub_api.infrastructure.security;

import com.trujidev.forohub.foro_hub_api.domain.user.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthService implements UserDetailsService {

  @Autowired
  RepositoryUser repositoryUser;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repositoryUser.findByEmail(username);
  }

}
