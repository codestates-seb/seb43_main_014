import com.cv.domain.user.dto.sign.SignUpResponseDto
import com.cv.domain.user.dto.sign.UserPostDto
import com.cv.domain.user.entity.User
import com.cv.domain.user.mapper.UserMapper
import com.cv.domain.user.repository.UserRepository
import com.cv.domain.user.service.UserServiceUtilsInterface
import com.cv.domain.user.service.UserSignupService
import com.cv.global.auth.utils.UserAuthorityUtils
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

class UserSignupServiceTestSpec extends Specification {
    UserServiceUtilsInterface userServiceUtilsInterface = Mock(UserServiceUtilsInterface)
    UserRepository userRepository = Mock(UserRepository)
    PasswordEncoder passwordEncoder = Mock(PasswordEncoder)
    UserAuthorityUtils authorityUtils = Mock(UserAuthorityUtils)
    UserMapper mapper = Mock(UserMapper)

    UserSignupService userSignupService

    def setup() {
        userSignupService = new UserSignupService(
                userServiceUtilsInterface,
                userRepository,
                passwordEncoder,
                authorityUtils,
                mapper
        )
    }

    def "createUser 회원가입 메서드"() {
        given:
        // UserPostDto의 예시 데이터
        def userPostDto = new UserPostDto("홍길동", "hgd@gmail.com", "Apple1234!", "010-1111-2222")

        // User 객체 초기화
        def user = new User()

        // 암호화된 비밀번호의 예시
        def encryptedPassword = "encryptedPassword"

        // Mock 객체들의 행동 정의
        mapper.userPostDtoToUser(userPostDto) >> user
        passwordEncoder.encode(user.getPassword()) >> encryptedPassword
        authorityUtils.createRoles(user.getEmail()) >> ["ROLE_USER"]
        userRepository.save(user) >> user

        // SignUpResponseDto의 예시
        def signupResponseDto = new SignUpResponseDto()
        signupResponseDto.uuid = "550e8400-e29b-41d4-a716-446655440000"
        mapper.userPostToSignUpResponse(user) >> signupResponseDto

        when:
        def result = userSignupService.createUser(userPostDto)

        then:
        assert result != null
        result.uuid == "550e8400-e29b-41d4-a716-446655440000"
        result instanceof SignUpResponseDto


        1 * userRepository.save(user) >> user
        1 * mapper.userPostToSignUpResponse(_) >> signupResponseDto
    }
}
