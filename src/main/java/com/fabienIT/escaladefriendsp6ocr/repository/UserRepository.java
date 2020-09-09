package com.fabienIT.escaladefriendsp6ocr.repository;

import com.fabienIT.escaladefriendsp6ocr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByEmail(String email);
	 
	 List<User> findByName(String name);
}
