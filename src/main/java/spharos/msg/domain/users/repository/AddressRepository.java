package spharos.msg.domain.users.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import spharos.msg.domain.users.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Override
    Optional<Address> findById(Long addressId);
}
