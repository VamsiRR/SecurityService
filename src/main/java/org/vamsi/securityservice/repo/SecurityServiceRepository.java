package org.vamsi.securityservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vamsi.securityservice.dto.UserEntity;

@Repository
public interface SecurityServiceRepository extends JpaRepository<UserEntity, String> {
}
