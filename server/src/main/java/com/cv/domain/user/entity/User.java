package com.cv.domain.user.entity;

import com.cv.global.audit.Auditable;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "member")
@NoArgsConstructor
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 20, nullable = false, unique = true)
    private String phone;

    // 역할
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    // 회원상태
    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private UserStatus userStatus = UserStatus.USER_ACTIVE;

    // 프로필사진 //TODO path를 저장?
    @Column(name = "profileImage")
    private String profileImage;

    // 기술스택
//    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
//    private List<UserSkillStack> tagNames = new ArrayList<>();

    // 삭제여부
    @Column
    private boolean isDelete = false;


    public enum UserStatus {
        USER_ACTIVE,
        USER_WITHDRAWN
    }

    // User가 탈퇴상태인지 확인하는 메서드
    public void checkActiveUser(User user){
        if(this.getUserStatus() == UserStatus.USER_WITHDRAWN)
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
    }


    // 회원정보를 수정,삭제요청 하는 user가 자신인지 확인
    public static boolean isMyself(long authenticatedUserId,Long userId) {
        if(userId != authenticatedUserId){
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
        }
        return true;
    }
}
