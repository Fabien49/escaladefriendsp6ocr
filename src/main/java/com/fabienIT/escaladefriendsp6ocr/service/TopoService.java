package com.fabienIT.escaladefriendsp6ocr.service;

import com.fabienIT.escaladefriendsp6ocr.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
