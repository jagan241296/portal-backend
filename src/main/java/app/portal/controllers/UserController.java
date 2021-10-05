package app.portal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.portal.dto.UserDto;
import app.portal.service.UserService;
import lombok.Getter;

@RestController
@CrossOrigin
@Getter
@RequestMapping("rest/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/validatelogin")
	public UserDto validateLogin(@RequestBody UserDto userDto) {
		return getUserService().validateLogin(userDto);
	}
}
