package app.portal.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.portal.dto.UserDto;
import app.portal.helpers.UserHelper;
import app.portal.repositories.UserRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Service
@Getter
@Slf4j
public class UserService {

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private UserRepository userRepository;

	public UserDto validateLogin(UserDto userDto) {
		var responseDto = new UserDto();

		if (userDto == null || StringUtils.isBlank(userDto.getUserName())
				|| StringUtils.isBlank(userDto.getPassword())) {
			log.error("User Details are empty.");
			return responseDto;
		}

		// Check if credentials are correct
		var existingUserOpt = getUserRepository().findByUserNameAndPassword(userDto.getUserName(),
				userDto.getPassword());
		if (existingUserOpt.isEmpty()) {
			return responseDto;
		}

		var response = getUserHelper().convertUserEntityToDto(existingUserOpt.get());
		response.setLoginValid(true);
		return response;
	}

	public boolean updateProfileDetails(UserDto userDto) {
		if (userDto == null || StringUtils.isBlank(userDto.getUserId()))
			return false;

		var existingUser = getUserRepository().findById(userDto.getUserId());
		if (existingUser.isEmpty()) {
			log.error("Cannot find user with given ID. Unable to update the profile details");
			return false;
		}

		try {
			var userDao = existingUser.get();
			userDao.setFirstName(userDto.getFirstName());
			userDao.setLastName(userDto.getLastName());
			getUserRepository().save(userDao);
		} catch (RuntimeException e) {
			log.error("Error while updating the profile details: " + e);
			return false;
		}
		return true;
	}

	public UserDto getProfileDetails(String employeeId) {
		if (StringUtils.isBlank(employeeId))
			return null;

		var employeeDetailsOpt = getUserRepository().findById(employeeId);
		if (employeeDetailsOpt.isEmpty()) {
			log.error("Cannot find profile details for given ID: " + employeeId);
			return null;
		}
		return getUserHelper().convertUserEntityToDto(employeeDetailsOpt.get());
	}
}
