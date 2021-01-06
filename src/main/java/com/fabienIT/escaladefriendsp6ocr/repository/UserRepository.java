package com.fabienIT.escaladefriendsp6ocr.repository;

import com.fabienIT.escaladefriendsp6ocr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {


	User findByEmail(String email);

	 User findAllById(Long id);

	 User findUserById(Long id);
}
