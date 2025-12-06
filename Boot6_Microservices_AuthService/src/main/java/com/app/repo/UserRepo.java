package com.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.AppUser;

public interface UserRepo  extends JpaRepository<AppUser,Long>{
	
	Optional<AppUser>findByUsername(String username);

}
