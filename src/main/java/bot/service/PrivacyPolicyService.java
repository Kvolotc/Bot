package bot.service;

import java.util.List;

import bot.entity.PrivacyPolicy;

public interface PrivacyPolicyService {

	public PrivacyPolicy savePrivacyPolicy(PrivacyPolicy privacyPolicy);
	
	public PrivacyPolicy updatePrivacyPolicy(PrivacyPolicy privacyPolicy);
	
	public PrivacyPolicy getPrivacyPolicy(Integer id);
	
	public PrivacyPolicy findLastActivePrivacyPolicy(int id);
	
	public PrivacyPolicy findByToken(String token);
	
	public List<PrivacyPolicy> getAllActivePolicy(int id, int page);
	
	public void removeActivePolicy(PrivacyPolicy policy);
	
	public PrivacyPolicy getEditPolicy(int id);
	
	public void checkForIsEdit(PrivacyPolicy policy);
	
	
	
}
