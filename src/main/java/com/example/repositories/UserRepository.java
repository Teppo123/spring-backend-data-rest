package com.example.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.repositories.entities.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	public static final String PARAM_ID = "id";
	public static final String PARAM_DATE = "date";
	public static final String PRAM_LAST_NAME = "lastName";
	public static final String PARAM_FIRST_NAME = "firstName";

	List<User> findAllByDeactivatedFalse();

	Optional<User> findByFirstNameAndLastNameAndDeactivatedFalse(@Param(PARAM_FIRST_NAME) String firstName,
			@Param(PRAM_LAST_NAME) String lastName);

	List<User> findByFirstNameAndDeactivatedFalse(@Param(PARAM_FIRST_NAME) String firstName);

	List<User> findByLastNameAndDeactivatedFalse(@Param(PRAM_LAST_NAME) String lastName);

	List<User> findByBirthDateBeforeAndDeactivatedFalse(@Param(PARAM_DATE) Date date);

	Optional<User> findByIdAndDeactivatedFalse(@Param(PARAM_ID) long id);

	@RestResource(exported = false)
	@Modifying
	@Query("UPDATE users u SET u.deactivated = true WHERE u.id = :" + PARAM_ID)
	int deactivateUserById(@Param(PARAM_ID) long id);

}
