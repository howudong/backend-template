package spharos.msg.domain.users.repository;

import java.lang.reflect.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import spharos.msg.domain.users.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
     Optional<Users> findByUuid(String uuid);
     Optional<Users> findByLoginId(String loginId);
}
