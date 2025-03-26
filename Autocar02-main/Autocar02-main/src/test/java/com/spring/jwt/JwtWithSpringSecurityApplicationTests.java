package com.spring.jwt.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.spring.jwt.model.User;
import com.spring.jwt.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	private User testUser;

	@BeforeEach
	void setUp() {
		testUser = new User(1L, "testuser", "password123");
	}

	@Test
	void testFindUserByUsername() {
		when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(testUser));

		Optional<User> foundUser = userService.findUserByUsername("testuser");

		assertTrue(foundUser.isPresent());
		assertEquals("testuser", foundUser.get().getUsername());
	}

	@Test
	void testUserNotFound() {
		when(userRepository.findByUsername("unknown")).thenReturn(Optional.empty());

		Optional<User> foundUser = userService.findUserByUsername("unknown");

		assertFalse(foundUser.isPresent());
	}
}
