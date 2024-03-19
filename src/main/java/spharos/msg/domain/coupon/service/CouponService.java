package spharos.msg.domain.coupon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spharos.msg.domain.coupon.entity.Coupon;
import spharos.msg.domain.coupon.repository.CouponRepository;
import spharos.msg.domain.users.repository.UsersRepository;
import spharos.msg.global.api.ApiResponse;
import spharos.msg.global.api.code.status.SuccessStatus;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final UsersRepository usersRepository;
    public ApiResponse<?> getCoupon() {
        return ApiResponse.of(SuccessStatus.COUPON_LIST_GET_SUCCESS ,
                new ArrayList<>(couponRepository.findAll()));
    }

    public ApiResponse<?> downloadCoupon(String username, Long couponId) {

        return null;
    }

    public ApiResponse<?> getUsersCoupon(String username) {
        return null;
    }
}
