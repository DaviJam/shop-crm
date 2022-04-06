package eu.ensup.shopcrm.repository;

import eu.ensup.shopcrm.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByusername(String name);
}
