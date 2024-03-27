package spharos.msg.domain.users.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Objects;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import spharos.msg.domain.users.dto.in.EmailAuthRequestDto;
import spharos.msg.domain.users.dto.in.EmailSendRequestDto;
import spharos.msg.domain.users.dto.out.EmailOutDto;
import spharos.msg.domain.users.repository.UsersRepository;
import spharos.msg.global.api.code.status.ErrorStatus;
import spharos.msg.global.api.exception.UsersException;
import spharos.msg.global.redis.RedisService;


@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {

    private final JavaMailSender mailSender;
    private final RedisService redisService;
    private final UsersRepository userRepository;

    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.expiration}")
    private long expiration;
    @Value("${spring.mail.stringSize}")
    private int stringSize;

    public EmailOutDto sendMail(EmailSendRequestDto emailSendRequestDto){
        MimeMessage message = mailSender.createMimeMessage();
        String secretKey = createKey();
        try{
            String email = emailSendRequestDto.getEmail();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
            mimeMessageHelper.setFrom(username);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("MSG.COM 회원 가입 인증 메일 입니다");
            mimeMessageHelper.setText("인증번호 : " + secretKey);
            mailSender.send(message);

            if(redisService.isEmailSecretKeyExist(email)){
                redisService.deleteEmailSecretKey(email);
            }
            redisService.saveEmailSecretKey(email, secretKey, expiration);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return EmailOutDto.builder().secretKey(secretKey).build();
    }

    private String createKey() {
        int leftLimit = '0';
        int rightLimit = 'z';
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(stringSize)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public void authenticateEmail(EmailAuthRequestDto emailAuthRequestDto){
        String findSecretKey = redisService.getEmailSecretKey(emailAuthRequestDto.getEmail());
        if(!Objects.equals(findSecretKey, emailAuthRequestDto.getSecretKey())){
            throw new UsersException(ErrorStatus.EMAIL_VALIDATE_FAIL);
        }
        redisService.deleteEmailSecretKey(emailAuthRequestDto.getEmail());
    }

    //Email 중복 확인
    public void duplicateCheckEmail(EmailSendRequestDto emailRequestDto)
    {
        if(userRepository.existsByEmail(emailRequestDto.getEmail())){
            throw new UsersException(ErrorStatus.ALREADY_EXIST_EMAIL);
        }
    }



//
//    public void duplicateCheckLoginId(){
//    }


//    public void createTokenAndCreateHeaders(HttpServletResponse response, Users users) {
//
//        //refreshToken 확인 후, 있을 시, Delete 처리
//        if (Boolean.TRUE.equals(redisService.isRefreshTokenExist(users.getUuid()))) {
//            log.info("Delete Token is Complete={}", redisService.getRefreshToken(users.getUuid()));
//            redisService.deleteRefreshToken(users.getUuid());
//        }
//
//        String accessToken = createAccessToken(users);
//        String refreshToken = createRefreshToken(users);
//
//        doResponseCookieAndHeader(response, refreshToken, accessToken);
//    }
//
//    private void doResponseCookieAndHeader(
//            HttpServletResponse response, String refreshToken, String accessToken) {
//        response.addHeader(ACCESS_TOKEN, accessToken);
//        Cookie cookie = new Cookie(REFRESH_TOKEN, refreshToken);
//        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
//        cookie.setPath("/");
//        cookie.setMaxAge((int) refreshTokenExpiration);
//        response.addCookie(cookie);
//    }
//
//    @Transactional(readOnly = true)
//    public Users getUsersByUuid(String uuid){
//        return usersRepository.findByUuid(uuid).orElseThrow(
//                () -> new JwtTokenException(ErrorStatus.REISSUE_TOKEN_FAIL));
//    }
//
//    public void userLogout(String uuid) {
//        if (Boolean.TRUE.equals(redisService.isRefreshTokenExist(uuid))) {
//            redisService.deleteRefreshToken(uuid);
//        }
//    }
//
//    @Transactional
//    public void deleteUsers(String uuid) {
//        Users users = usersRepository.findByUuid(uuid).orElseThrow();
//        usersRepository.deleteById(users.getId());
//    }
//
//    @Transactional(readOnly = true)
//    public Boolean isUsers(SignUpRequestDto signUpRequestDto) {
//        return usersRepository.findByUserNameAndEmail(
//                signUpRequestDto.getUsername(),
//                signUpRequestDto.getEmail()).isPresent();
//    }
//
//    @Transactional
//    public void createEasyAndUnionUsers(SignUpRequestDto signUpRequestDto) {
//        //KakaoService 어떻게 해결하지..?
//        if (!isUsers(signUpRequestDto)) {
//            log.info("간편회원가입 중, 가입된 회원이 아니라 통합회원가입 진행");
//            this.createUsers(signUpRequestDto);
//        }
//        Users users = usersRepository.findByUserNameAndEmail(signUpRequestDto.getUsername(),
//                        signUpRequestDto.getEmail())
//                .orElseThrow(() -> new UsersException(ErrorStatus.SIGN_UP_EASY_FAIL));
//
//        if (kakaoUsersRepository.findKakaoUsersByUserUuid(users.getUuid()).isPresent()) {
//            throw new UsersException(ErrorStatus.ALREADY_EASY_USER);
//        }
//        kakaoUsersService.createKakaoUsers(users.getUuid());
//    }
//
//    @Transactional(readOnly = true)
//    public Users easyLogin(KakaoLoginRequestDto kakaoLoginRequestDto) {
//        Users findUser = usersRepository.findByUserNameAndEmail(
//                        kakaoLoginRequestDto.getUsername(),
//                        kakaoLoginRequestDto.getEmail())
//                .orElseThrow(() -> new UsersException(ErrorStatus.EASY_LOGIN_ID_NOT_FOUND));
//
//        kakaoUsersRepository.findKakaoUsersByUserUuid(findUser.getUuid())
//                .orElseThrow(() -> new UsersException(ErrorStatus.IS_NOT_EASY_USER));
//
//        log.info("간편 로그인 Login={}", findUser.getId());
//
//        return findUser;
//    }
}
