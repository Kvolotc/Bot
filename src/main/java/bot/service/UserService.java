package bot.service;

import java.util.List;

import bot.entity.User;
import bot.entity.constans.State;

public interface UserService {

	public User saveUser(User user);
	
	public User updateUser(User user);
	
	public void deleteUser(User user);
	
	public void deleteUser(Integer id);
	
	public User getUser(Integer id);
	
	public List<User> getAll();
	
	public User findByMessagerId(long id);
	
	public void updateState(User user, State state);
	
}
