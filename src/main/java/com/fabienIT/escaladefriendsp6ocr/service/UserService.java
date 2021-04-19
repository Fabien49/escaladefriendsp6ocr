package com.fabienIT.escaladefriendsp6ocr.service;

import com.fabienIT.escaladefriendsp6ocr.model.User;

public interface UserService {

	User findUserByEmail(String email);
	void saveUser(User user);
	void saveMembre(User user);

}
