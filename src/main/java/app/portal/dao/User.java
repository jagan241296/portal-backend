package app.portal.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "user_details")
public class User {

	@Id
	@Column
	private String userId;
	@Column
	private String userName;
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column
	private String password;
	@Column
	private String userType;
	@Column
	private String firstName;
	@Column
	private String lastName;
}
