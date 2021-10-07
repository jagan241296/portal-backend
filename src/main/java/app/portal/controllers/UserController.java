package app.portal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@PostMapping("/login")
	public UserDto validateLogin(@RequestBody UserDto userDto) {
		return getUserService().validateLogin(userDto);
	}

	@PutMapping("/update-profile")
	public boolean updateProfileDetails(@RequestBody UserDto userDto) {
		return getUserService().updateProfileDetails(userDto);
	}

	@GetMapping("/getprofile")
	public UserDto getProfileDetails(@RequestParam("id") String employeeId) {
		return getUserService().getProfileDetails(employeeId);
	}
}
