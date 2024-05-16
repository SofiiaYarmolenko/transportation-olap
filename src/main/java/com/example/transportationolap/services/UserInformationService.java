package com.example.transportationolap.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.transportationolap.domain.entities.UserInformation;
import com.example.transportationolap.domain.repositories.UserInformationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInformationService {

	private final UserInformationRepository userRepository;
	
	public Optional<UserInformation> getUserInformationById(Integer id) {
		return userRepository.findById(Integer.toUnsignedLong(id));
	}
	
	public UserInformation saveUserInformation(UserInformation userInformation) {
		return userRepository.save(userInformation);
	}
	
	public void deleteUserInformation(Integer id) {
		userRepository.deleteById(Integer.toUnsignedLong(id));
	}
}
