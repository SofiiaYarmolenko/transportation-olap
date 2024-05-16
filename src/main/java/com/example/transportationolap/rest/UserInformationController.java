package com.example.transportationolap.rest;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transportationolap.domain.entities.UserInformation;
import com.example.transportationolap.services.UserInformationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user-information")
@RequiredArgsConstructor
public class UserInformationController {

	private final UserInformationService userInformationService;

	@GetMapping("/{id}")
	public ResponseEntity<UserInformation> getUserInformationById(@PathVariable Integer id) {
		Optional<UserInformation> userInformation = userInformationService.getUserInformationById(id);
		return userInformation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<UserInformation> createUserInformation(@RequestBody UserInformation userInformation) {
		UserInformation savedUserInformation = userInformationService.saveUserInformation(userInformation);
		return new ResponseEntity<>(savedUserInformation, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserInformation> updateUserInformation(@PathVariable Integer id, @RequestBody UserInformation userInformation) {
		Optional<UserInformation> existingUserInformation = userInformationService.getUserInformationById(id);
		if (existingUserInformation.isPresent()) {
			userInformation.setId(id);
			UserInformation updatedUserInformation = userInformationService.saveUserInformation(userInformation);
			return new ResponseEntity<>(updatedUserInformation, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserInformation(@PathVariable Integer id) {
		Optional<UserInformation> existingUserInformation = userInformationService.getUserInformationById(id);
		if (existingUserInformation.isPresent()) {
			userInformationService.deleteUserInformation(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
