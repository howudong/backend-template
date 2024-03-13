package spharos.msg.domain.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spharos.msg.domain.users.entity.Users;

public interface UserRepository extends JpaRepository<Users,Long> {
}
