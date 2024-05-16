package com.example.transportationolap.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transportationolap.domain.entities.UserInformation;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {
}
