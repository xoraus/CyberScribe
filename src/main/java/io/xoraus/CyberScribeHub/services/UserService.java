package io.xoraus.CyberScribeHub.services;

import java.util.List;

import io.xoraus.CyberScribeHub.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto update(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
}
