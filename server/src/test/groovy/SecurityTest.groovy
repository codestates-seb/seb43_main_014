import com.cv.domain.user.dto.sign.SignUpResponseDto
import com.cv.domain.user.dto.sign.UserPostDto
import com.cv.domain.user.entity.User
import com.cv.domain.user.mapper.UserMapper
import com.cv.domain.user.repository.UserRepository
import com.cv.domain.user.service.DefaultUserService
import com.cv.domain.user.service.ReadOnlyUserService
import com.cv.global.auth.jwt.tokenizer.JwtTokenizer
import com.cv.global.auth.utils.UserAuthorityUtils
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

class SecurityTest extends Specification {
    UserRepository userRepository = Mock(UserRepository)
    PasswordEncoder passwordEncoder = Mock(PasswordEncoder)
    UserAuthorityUtils authorityUtils = Mock(UserAuthorityUtils)
    JavaMailSender javaMailSender = Mock(JavaMailSender)
    UserMapper mapper = Mock(UserMapper)
    ReadOnlyUserService readOnlyUserService = Mock(ReadOnlyUserService)
    JwtTokenizer jwtTokenizer = Mock(JwtTokenizer)
    RedisTemplate redisTemplate = Mock(RedisTemplate)
    String adminEmail = "admin@gmail.com"

    DefaultUserService defaultUserService

    def setup() {
        defaultUserService = new DefaultUserService(
                userRepository,
                passwordEncoder,
                authorityUtils,
                javaMailSender,
                mapper,
                readOnlyUserService,
                jwtTokenizer,
                redisTemplate
        )
        defaultUserService.adminEmail = adminEmail
    }

    def "createUser 회원가입 메서드"() {
        given:
        def userPostDto = new UserPostDto("홍길동", "hgd@gmai.com", "Apple1234!", "010-1111-2222")
        def user = new User()
        def encryptedPassword = "encryptedPassword"
        def signupResponseDto = new SignUpResponseDto()
        signupResponseDto.userId = 1L

        mapper.userPostDtoToUser(_) >> user
        passwordEncoder.encode(_) >> encryptedPassword
        authorityUtils.createRoles(_) >> List.of()
        userRepository.save(_) >> new User()
        mapper.userPostToSignUpResponse(_) >> signupResponseDto

        when:
        def result = defaultUserService.createUser(userPostDto)

        then:
        result.userId == 1L
        1 * userRepository.save(user)
    }
}
