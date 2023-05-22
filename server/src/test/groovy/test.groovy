import com.cv.domain.cv.entity.Cv
import com.cv.domain.cv.service.CvService
import com.cv.domain.user.entity.User
import spock.lang.Specification
import spock.lang.Unroll

class test extends Specification{

    def setup(){

    }

    @Unroll
    def "CreateCv 테스트"(){
        given:
        def mockCvService = Mock(CvService.class)
        def mockUser = Mock(User.class)
        def mockCv = Mock(Cv.class)

        when:
        def createCv = mockCvService.createCv(mockCv)

        then:
        createCv == mockCv
    }
    
}
