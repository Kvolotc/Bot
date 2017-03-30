package bot.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bot.entity.User;
import bot.entity.constans.State;
import bot.repository.UserRepository;
import bot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User saveUser(User user) {
		
		return repository.save(user);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Modifying
	@Override
	public User updateUser(User user) {
		
		return repository.saveAndFlush(user);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void deleteUser(User user) {
		
		repository.delete(user);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public User getUser(Integer id) {
		
		return repository.getOne(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void deleteUser(Integer id) {
		
		repository.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<User> getAll() {
		
		return repository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public User findByMessagerId(long id) {
		
		return repository.findByMessagerId(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Modifying
	@Override
	public void updateState(User user, State state) {
		user.setState(state);
		repository.saveAndFlush(user);
		
	}

	
}
