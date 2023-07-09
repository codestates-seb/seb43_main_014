package com.cv.domain.user.entity;

import com.cv.domain.cv.entity.Cv;
import com.cv.global.audit.Auditable;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "member") // 엔티티를 user -> member로 변경하는게 좋음 문제많이 터질수있음 감안 ㅜㅜ
@NoArgsConstructor
@AllArgsConstructor
//@Data // 왠만하면 ToString 리소스면에서 문제생길수있음 무한루프 돌수있음 쓰지말아라
@Getter
@Setter
@Component // test코드 때문에 작성을 했었음 -> 일반적으로는 엔티티에서 사용하지 않음
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column
    private String uuid = UUID.randomUUID().toString();
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 10, nullable = false)
    private String name;
    @Column(unique = true)
    private String phone;

    // 역할
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    // 회원상태
    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private UserStatus userStatus = UserStatus.USER_ACTIVE;

    // 프로필사진
    @Lob
    @Column(name = "profileImage")
    private String profileImage;

//     기술스택
//    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
//    private List<UserSkillStack> tagNames = new ArrayList<>();

    // 삭제여부
    @Column
    private boolean isDelete = false;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Cv> cvs = new ArrayList<>();


    public enum UserStatus {
        USER_ACTIVE,
        USER_WITHDRAWN
    }

    // User가 탈퇴상태인지 확인하는 메서드
    public void checkActiveUser(User user){
        if(user.getUserStatus() == UserStatus.USER_WITHDRAWN)
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
    }
}
