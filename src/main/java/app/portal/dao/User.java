package app.portal.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

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
	@Column
	private String password;

}
