package app.portal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.portal.dao.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUserNameAndPassword(String userName, String password);
}
