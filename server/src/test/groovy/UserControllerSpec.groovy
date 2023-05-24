//import com.cv.domain.user.controller.UserController
//import com.cv.domain.user.dto.sign.SignUpResponseDto
//import com.cv.domain.user.dto.sign.UserPostDto
//import com.cv.domain.user.repository.UserRepository
//import com.cv.domain.user.service.DefaultUserService
//import com.cv.domain.user.service.ReadOnlyUserService
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.mock.mockito.MockBean
//import spock.lang.Specification
//import spock.lang.Subject
//import spock.lang.Unroll
//import com.cv.domain.user.entity.User
//
//@SpringBootTest
//class UserControllerSpec extends Specification {
//
//    @Subject
//    UserController userController
//
//    @Autowired
//    UserRepository userRepository
//
//    @MocK()
//    DefaultUserService defaultUserService
//
//    @MockBean
//    ReadOnlyUserService readOnlyUserService
//
//    def setup() {
//        userController = new UserController(defaultUserService, readOnlyUserService)
//    }
//
//    @Unroll
//    def "createUser_ValidUserPostDto_ReturnsCreatedResponse"() {
//        given:
//        UserPostDto userPostDto = new UserPostDto(
//                name: "홍길동",
//                email: "asd@google.com",
//                password: "a1234567!",
//                phone: "010-1234-5678"
//        )
//        User createdUser = new User(
//                name: userPostDto.name,
//                email: userPostDto.email,
//                password: userPostDto.password,
//                phone: userPostDto.phone
//        )
//        SignUpResponseDto signUpResponseDto = new SignUpResponseDto(
//                userId: 1L,
//                createdAt: LocalDate.of(2023, 5, 23)
//        )
//
//        userRepository.save(_) >> createdUser
//        defaultUserService.createUser(_) >> signUpResponseDto
//    }
//}
//
