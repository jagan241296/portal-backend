package app.portal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

	private String userId;
	private String userName;
	private String password;
	private String userType;
	private boolean loginValid;
	private String firstName;
	private String lastName;
}
