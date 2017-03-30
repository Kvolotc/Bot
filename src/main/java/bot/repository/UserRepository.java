package bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bot.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByMessagerId(long id);
	
}
