package spharos.msg.domain.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spharos.msg.domain.users.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
