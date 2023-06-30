import com.cv.domain.user.dto.mypage.UserPasswordPatchDto
import com.cv.domain.user.entity.User
import com.cv.domain.user.mapper.UserMapper
import com.cv.domain.user.repository.UserRepository
import com.cv.domain.user.service.UserInfoService
import com.cv.global.auth.jwt.tokenizer.JwtTokenizer
import com.cv.global.auth.utils.UserAuthorityUtils
import com.cv.global.exception.BusinessLogicException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate
import java.time.LocalDateTime

class DefaultUserServiceTest extends Specification {
    UserRepository userRepository = Mock(UserRepository)
    PasswordEncoder passwordEncoder = Mock(PasswordEncoder)
    UserAuthorityUtils authorityUtils = Mock(UserAuthorityUtils)
    JavaMailSender javaMailSender = Mock(JavaMailSender)
    UserMapper mapper = Mock(UserMapper)
    JwtTokenizer jwtTokenizer = Mock(JwtTokenizer)
    RedisTemplate<String, String> redisTemplate = Mock(RedisTemplate)

    @Subject
    UserInfoService userInfoService
    /*
      def "changePassword 메서드"() {
        given:
        def userId = 1L
        def currentPassword = "1234567a!"
        def newPassword = "1234567a!!"

        def userPasswordPatchDto = new UserPasswordPatchDto(currentPassword, newPassword)
        def loggedInUser = new User(
                1L,
                "hgd@gmai.com",
                "1234567a!",
                "홍길동",
                "010-1111-2222",
                List.of(),
                User.UserStatus.USER_ACTIVE,
                null,
                false,
                List.of()
        ) // (1)
        loggedInUser.setModifiedAt(LocalDateTime.of(2023, 5, 25, 19, 8)) // (2)
        def loggedInUserPassword = "1234567a!"

        readOnlyUserService.findUser(_) >> loggedInUser
        loggedInUser.checkActiveUser(_) >> false
        loggedInUser.getPassword(_) >> loggedInUserPassword
        passwordEncoder.matches(_, _) >> true // (3)
        loggedInUser.setPassword(_) >> newPassword
        passwordEncoder.encode(_) >> newPassword


        when:
        def result = defaultUserService.changePassword(userId, userPasswordPatchDto)

        then:
        result == LocalDate.of(2023, 5, 25)
    }*/

    void setup() {
        defaultUserService = new UserInfoService(userRepository, passwordEncoder, authorityUtils,
                javaMailSender, mapper, readOnlyUserService, jwtTokenizer, redisTemplate)
    }

    def "Change password"() {
        given:
        def userId = 1L
        def passwordPatchDto = new UserPasswordPatchDto("pw", "newPw")
        def loggedInUser = new User(
                1L,
                "hgd@gmai.com",
                "pw",
                "홍길동",
                "010-1111-2222",
                List.of(),
                User.UserStatus.USER_ACTIVE,
                null,
                false,
                List.of()
            )
        loggedInUser.setModifiedAt(LocalDateTime.of(2023,5,25,19,8))

        readOnlyUserService.findUser(_) >> loggedInUser
        loggedInUser.getPassword(_) >> "pw"
        loggedInUser.checkActiveUser(new User) = false
        passwordEncoder.matches(_, _) >> true
        loggedInUser.setPassword(_) >> "pw"
        passwordEncoder.encode(_) >> "newPw"

        when:
        def result = defaultUserService.changePassword(userId, passwordPatchDto)

        then:
        result == LocalDate.of(2023,5,25)

        1 * readOnlyUserService.findUser(userId)
        1 * loggedInUser.getPassword(_)
        1 * passwordEncoder.matches(_, _)
        1 * loggedInUser.setPassword(_)
        1 * passwordEncoder.encode(_)
        1 * userRepository.save(_)
    }




    // user가 탈퇴상태일 때
    def "Don't change pw if user is invalid"() {
        given:
        def passwordPatchDto = new UserPasswordPatchDto("pw", "newPw")
        def userId = 1L
        def user = new User()
        user.setUserStatus(User.UserStatus.USER_WITHDRAWN)

        when:
        defaultUserService.changePassword(userId, passwordPatchDto)

        then:
        1 * readOnlyUserService.findUser(userId) >> user
        thrown(BusinessLogicException.class)
    }

    //
    def "Don't change pw if password isn't matched"() {
        given:
        def passwordPatchDto = new UserPasswordPatchDto("pw", "newPw")
        def userId = 1L
        def user = new User()

        when:
        defaultUserService.changePassword(userId, passwordPatchDto)

        then:
        1 * readOnlyUserService.findUser(userId) >> user
        1 * passwordEncoder.matches(_, _) >> false
        thrown(BusinessLogicException.class)
    }
}
/*

 */