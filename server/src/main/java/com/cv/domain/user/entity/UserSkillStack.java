package com.cv.domain.user.entity;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class UserSkillStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSkillStackId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

}
