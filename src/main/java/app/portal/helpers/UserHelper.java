package app.portal.helpers;

import org.springframework.stereotype.Component;

import app.portal.dao.User;
import app.portal.dto.UserDto;
import lombok.Getter;

@Component
@Getter
public class UserHelper {

	public UserDto convertUserEntityToDto(User user) {
		if (user == null)
			return new UserDto();

		var userDto = new UserDto();
		userDto.setUserName(user.getUserName());
		userDto.setUserId(user.getUserId());
		userDto.setUserType(user.getUserType());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		
		return userDto;
	}
}
