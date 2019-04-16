package com.ftn.authservice.services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.authservice.model.Permission;
import com.ftn.authservice.model.Role;

import com.ftn.authservice.model.User;
import com.ftn.authservice.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

		return getUserPrincipal(user);
	}

	public void changePassword(User user, String newPassword) {
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
		logger.info("User {} has changed their password.", user.getEmail());
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	private UserPrinciple getUserPrincipal(User user) {
		Stream<String> roles = user.getRoles().stream()
				.map(Role::getName)
				.map(Enum::name);

		Stream<String> permissions = user.getRoles().stream()
				.map(Role::getPermissions)
				.flatMap(Collection::stream)
				.map(Permission::getName);

		List<GrantedAuthority> authorities = Stream.concat(roles, permissions)
				.distinct()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		return new UserPrinciple(user.getId().toString(), user.getPassword(), user.getEmail(), authorities);
	}

	public List<String> getUserAuthorities(User user) {
		Stream<String> roles = user.getRoles().stream()
				.map(Role::getName)
				.map(Enum::name);

		Stream<String> permissions = user.getRoles().stream()
				.map(Role::getPermissions)
				.flatMap(Collection::stream)
				.map(Permission::getName);

		List<GrantedAuthority> authorities = Stream.concat(roles, permissions)
				.distinct()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		List<String> auts = new ArrayList<String>();
		for (GrantedAuthority nesto : authorities) {
			auts.add(nesto.getAuthority());
		}

		return auts;
	}
}