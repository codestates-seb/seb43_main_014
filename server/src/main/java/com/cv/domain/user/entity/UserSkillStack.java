package com.cv.domain.user.entity;

import javax.persistence.*;

@Entity
public class UserSkillStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSkillStackId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User userId;

}
