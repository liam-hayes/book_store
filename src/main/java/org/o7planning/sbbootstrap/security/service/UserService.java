package org.o7planning.sbbootstrap.security.service;

import org.o7planning.sbbootstrap.security.model.User;
import org.o7planning.sbbootstrap.security.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}