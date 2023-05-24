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

class UserTest extends Specification{
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


}
