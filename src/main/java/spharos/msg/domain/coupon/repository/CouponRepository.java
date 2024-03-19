package spharos.msg.domain.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spharos.msg.domain.coupon.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
}
