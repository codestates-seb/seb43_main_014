//package com.cv.domain.user.controller
//
//import com.cv.domain.cv.dto.PageLatestCvDto
//import com.cv.domain.user.dto.UserDto
//import com.cv.domain.user.entity.User
//import com.cv.domain.user.service.DefaultUserService
//import com.cv.domain.user.service.ReadOnlyUserService
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.mock.mockito.MockBean
//import org.springframework.http.MediaType
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
//import spock.lang.Specification
//
//import java.time.LocalDateTime
//
//import static org.mockito.ArgumentMatchers.*
//import static org.mockito.Mockito.*
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//class UserControllerSpec extends Specification {
//
//    @Autowired
//    MockMvc mockMvc
//
//    @MockBean
//    DefaultUserService defaultUserService
//
//    @MockBean
//    ReadOnlyUserService readOnlyUserService
//
//    @BeforeEach
//    void setup() {
//        // 테스트 전에 필요한 설정 및 Mock 객체 초기화
//    }
//
//    // createUser API 테스트
//    def "createUser API should return createdUser and HTTP 201"() {
//        given:
//        UserDto.Post userPostDto = new UserDto.Post() // 적절한 값으로 초기화
//
//        UserDto.SignUpResponse createdUser = new UserDto.SignUpResponse() // 적절한 값으로 초기화
//        defaultUserService.createUser(userPostDto) >> createdUser
//
//        when:
//        def result = mockMvc.perform(
//                post("/user")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userPostDto as String)
//        )
//
//        then:
//        result.andExpect(status().isCreated())
//                .andExpect(content().json(createdUser as String))
//    }
//
//    // changePassword API 테스트
//    def "changePassword API should return LocalDateTime and HTTP 200"() {
//        given:
//        Long userId = 1
//        UserDto.PasswordPatch userPasswordPatchDto = new UserDto.PasswordPatch() // 적절한 값으로 초기화
//        LocalDateTime localDateTime = LocalDateTime.now()
//        defaultUserService.changePassword(userId, userPasswordPatchDto) >> localDateTime
//
//        when:
//        def result = mockMvc.perform(
//                patch("/user/my-page/password/{userId}", userId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userPasswordPatchDto as String)
//        )
//
//        then:
//        result.andExpect(status().isOk())
//                .andExpect(content().string(localDateTime as String))
//    }
//
//    // updateUser API 테스트
//    def "updateUser API should return updatedUser and HTTP 200"() {
//        given:
//        Long userId = 1
//        UserDto.Patch userInfoPatchDto = new UserDto.Patch() // 적절한 값으로 초기화
//        UserDto.UserPatchResponse updatedUser = new UserDto.UserPatchResponse() // 적절한 값으로 초기화
//
//        defaultUserService.updateUserInfo(userId, userInfoPatchDto) >> updatedUser
//
//        when:
//        def result = mockMvc.perform(
//                patch("/user/my-page/{userId}", userId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userInfoPatchDto as String)
//        )
//
//        then:
//        result.andExpect(status().isOk())
//                .andExpect(content().json(updatedUser as String))
//    }
//
//    // deleteUser API 테스트
//    def "deleteUser API should return HTTP 204"() {
//        given:
//        Long userId = 1
//
//        when:
//        def result = mockMvc.perform(
//                delete("/user/my-page/{userId}", userId)
//        )
//
//        then:
//        result.andExpect(status().isNoContent())
//    }
//
//    // getUserProfile API 테스트
//    def "getUserProfile API should return userProfile and HTTP 200"() {
//        given:
//        Long userId = 1
//        int page = 1
//        User user = new User() // 적절한 값으로 초기화
//        PageLatestCvDto latestCvDto = new PageLatestCvDto() // 적절한 값으로 초기화
//
//        readOnlyUserService.findUser(userId) >> user
//        cvService.findLatestCvsByUser(userId, page) >> latestCvDto
//
//        when:
//        def result = mockMvc.perform(
//                get("/user/my-page/{userId}", userId)
//                        .param("page", String.valueOf(page))
//        )
//
//        then:
//        result.andExpect(status().isOk())
//                .andExpect(jsonPath("$.profileImage").value(user.getProfileImage()))
//                .andExpect(jsonPath("$.name").value(user.getName()))
//                .andExpect(jsonPath("$.email").value(user.getEmail()))
//                .andExpect(jsonPath("$.phone").value(user.getPhone()))
//                .andExpect(jsonPath("$.cvs").value(latestCvDto as String))
//    }
//
//    // getLatestCvsByUser API 테스트
//    def "getLatestCvsByUser API should return latestCvDto and HTTP 200"() {
//        given:
//        Long userId = 1
//        int page = 1
//        PageLatestCvDto latestCvDto = new PageLatestCvDto() // 적절한 값으로 초기화
//
//        cvService.findLatestCvsByUser(userId, page) >> latestCvDto
//
//        when:
//        def result = mockMvc.perform(
//                get("/user/my-page/{userId}/cvs", userId)
//                        .param("page", String.valueOf(page))
//        )
//
//        then:
//        result.andExpect(status().isOk())
//                .andExpect(content().json(latestCvDto as String))
//    }
//
//    // uploadProfileImage API 테스트
//    def "uploadProfileImage API should return updatedUser and HTTP 200"() {
//        given:
//        Long userId = 1
//        UserDto.ProfileImage profileImageDto = new UserDto.ProfileImage() // 적절한 값으로 초기화
//        UserDto.UserPatchResponse updatedUser = new UserDto.UserPatchResponse() // 적절한 값으로 초기화
//
//        defaultUserService.uploadProfile(userId, profileImageDto) >> updatedUser
//
//        when:
//        def result = mockMvc.perform(
//                post("/user/my-page/{userId}/profile-image", userId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(profileImageDto as String)
//        )
//
//        then:
//        result.andExpect(status().isOk())
//                .andExpect(content().json(updatedUser as String))
//    }
//
//    // forgotPassword API 테스트
//    def "forgotPassword API should return HTTP 200"() {
//        given:
//        UserDto.PasswordGet passwordGet = new UserDto.PasswordGet() // 적절한 값으로 초기화
//
//        when:
//        def result = mockMvc.perform(
//                post("/user/forgot-password")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(passwordGet as String)
//        )
//
//        then:
//        result.andExpect(status().isOk())
//    }
//
//    // isEmailDuplicated API 테스트
//    def "isEmailDuplicated API should return true or false"() {
//        given:
//        UserDto.Email userEmailDto = new UserDto.Email() // 적절한 값으로 초기화
//        boolean isDuplicated = true // 중복 여부에 따라 초기화
//
//        readOnlyUserService.isEmailDuplicated(userEmailDto) >> isDuplicated
//
//        when:
//        def result = mockMvc.perform(
//                post("/user/sign/email")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userEmailDto as String)
//        )
//
//        then:
//        result.andExpect(status().isOk())
//                .andExpect(content().string(isDuplicated.toString()))
//    }
//
//    // isPhoneDuplicated API 테스트
//    def "isPhoneDuplicated API should return true or false"() {
//        given:
//        UserDto.Phone userPhoneDto = new UserDto.Phone() // 적절한 값으로 초기화
//        boolean isDuplicated = true // 중복 여부에 따라 초기화
//
//        readOnlyUserService.isPhoneDuplicated(userPhoneDto) >> isDuplicated
//
//        when:
//        def result = mockMvc.perform(
//                post("/user/sign/phone")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userPhoneDto as String)
//        )
//
//        then:
//        result.andExpect(status().isOk())
//                .andExpect(content().string(isDuplicated.toString()))
//    }
//}
