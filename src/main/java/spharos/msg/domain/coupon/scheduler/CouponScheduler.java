package spharos.msg.domain.coupon.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spharos.msg.domain.coupon.service.CouponService;

@Component
@RequiredArgsConstructor
public class CouponScheduler {
    private final CouponService couponService;
    @Scheduled()
    public void deleteExpiredCoupon(){
        couponService.deleteExpiredCoupon();
    }
}
