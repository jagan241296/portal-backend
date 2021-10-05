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
}
