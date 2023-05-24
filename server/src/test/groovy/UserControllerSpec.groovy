//import com.cv.domain.user.controller.UserController
//import com.cv.domain.user.repository.UserRepository
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.mock.mockito.MockBean
//import spock.lang.Specification
//import spock.lang.Subject
//
//@SpringBootTest
//class UserControllerSpec extends Specification {
//
//    @Subject
//    UserController userController
//
//    @MockBean
//    UserRepository userRepository
//
//    def setup() {
//        userController = new UserController(userRepository)
//    }
//
//    def "createUser should return created user when valid user data is provided"() {
//        given:
//        def userData = [name: "eunhee", email: "asd@google.com",
//        password:"a1234567!", phone:"010-1234-5678"]
//
//        when:
//        def result = userController.createUser(userData)
//
//        then:
//        result != null
//        result.name == "eunhee"
//        result.email == "asd@google.com"
//        result.password == "a1234567!"
//        result.phone == "010-1234-5678"
//    }
//}
