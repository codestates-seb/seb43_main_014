package com.cv.domain.user.entity;

import com.cv.domain.audit.Auditable;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
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
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserSkillStack> tagNames = new ArrayList<>();

    // 삭제여부
    @Column
    private boolean isDelete = false;


    public enum UserStatus {
        USER_ACTIVE,
        USER_WITHDRAWN
    }

    // 이미 존재하는 멤버가 있는지 확인
    public static void checkActiveUser(User user){
        if(user.getUserStatus() == UserStatus.USER_WITHDRAWN)
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
    }

    public boolean isMyself(long authenticatedMemberId){
            return this.userId == authenticatedMemberId;
    }

    public void checkIsMine(long authenticatedUserId) {
        if (this.userId != authenticatedUserId) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND); //fixme 사용자예외처리 문구 변경해야함
        }
    }


}
