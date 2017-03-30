package bot.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bot.entity.PrivacyPolicy;
import bot.repository.PrivacyPolicyRepository;
import bot.service.PrivacyPolicyService;

@Service
public class PrivacyPolicyServiceImpl implements PrivacyPolicyService {

	@Autowired
	PrivacyPolicyRepository repository;
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public PrivacyPolicy savePrivacyPolicy(PrivacyPolicy privacyPolicy) {
		return repository.save(privacyPolicy);
	}

	@Modifying
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public PrivacyPolicy updatePrivacyPolicy(PrivacyPolicy privacyPolicy) {
		
		return repository.saveAndFlush(privacyPolicy);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public PrivacyPolicy getPrivacyPolicy(Integer id) {
		
		return repository.findOne(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public PrivacyPolicy findLastActivePrivacyPolicy(int id) {
		
		return repository.findBytokenIsNullAndIsActiveTrueAndUserId(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public PrivacyPolicy findByToken(String token) {
		
		return repository.findByToken(token);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<PrivacyPolicy> getAllActivePolicy(int id, int page) {
		
		PageRequest pageRequest = new PageRequest(page, 9);
		
		return repository.findBytokenIsNotNullAndIsActiveTrueAndUserId(id, pageRequest);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void removeActivePolicy(PrivacyPolicy policy) {

		if (policy != null) {
			policy.setActive(false);
			repository.saveAndFlush(policy);
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public PrivacyPolicy getEditPolicy(int id) {

		return repository.findByisEditTrueAndUserId(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public void checkForIsEdit(PrivacyPolicy policy) {
		
		if(policy != null) {
			policy.setEdit(false);
			repository.saveAndFlush(policy);
		}
		
	}

}
