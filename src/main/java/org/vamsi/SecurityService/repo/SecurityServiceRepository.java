package org.vamsi.SecurityService.repo;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vamsi.SecurityService.dto.UserCredentials;

@Repository
public interface SecurityServiceRepository extends JpaRepository<UserCredentials, String> {
}
