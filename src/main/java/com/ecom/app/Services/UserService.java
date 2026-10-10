package com.ecom.app.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.app.entity.User;
import com.ecom.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	// private static List<User> userList = new ArrayList<User>();
	// private Long nextId = 1L;

	@Autowired
	private final UserRepository userRepository;

	public List<User> fetchAllUsers() {

		return userRepository.findAll();

	}

	public void addUser(User user) {
		// user.setId(nextId++);

		userRepository.save(user);

		// userList.add(user);

	}

	public Optional<User> fetchUsers(Long id) {

		// return userList.stream().filter(user -> user.getId().equals(id)).findFirst();

		return userRepository.findById(id);

	}

	public boolean updatedUser(Long id, User updateduser) {

		// return userList.stream().filter(user ->
		// user.getId().equals(id)).findFirst().map(

		return userRepository.findById(id).map(existingUser -> {

			existingUser.setFirstName(updateduser.getFirstName());
			existingUser.setLastName(updateduser.getLastName());

			userRepository.save(existingUser);
			return true;

		}).orElse(false);

	}

	public String deleteUser(Long id) {

		if (id >= 0)

			userRepository.deleteById(id);
		else {
			return "Data not found.....";
		}

		return "";
	}

}
