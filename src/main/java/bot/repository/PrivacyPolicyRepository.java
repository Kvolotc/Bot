package bot.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bot.entity.PrivacyPolicy;

@Repository
public interface PrivacyPolicyRepository extends JpaRepository<PrivacyPolicy, Integer> {

	public PrivacyPolicy findBytokenIsNullAndIsActiveTrueAndUserId(int id);
	
	public PrivacyPolicy findByToken(String token);
	
	public List<PrivacyPolicy> findBytokenIsNotNullAndIsActiveTrueAndUserId(int id, Pageable pageable);
	
	public PrivacyPolicy findByisEditTrueAndUserId(int id);
}
