package org.vamsi.SecurityService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vamsi.SecurityService.dto.UserEntity;

@Repository
public interface SecurityServiceRepository extends JpaRepository<UserEntity, String> {
}
