package af.gov.anar.dck.useradministration.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import af.gov.anar.dck.useradministration.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
   public PasswordResetToken findByToken(String token);
}