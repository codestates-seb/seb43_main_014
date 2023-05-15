//package com.cv.domain.user.entity;
//
//import com.cv.domain.skillStack.entity.SkillStack;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//
//import javax.persistence.*;
//
//@NoArgsConstructor
//@Data
//@Entity
//public class UserSkillStack {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userSkillStackId;
//
//    @ManyToOne
//    @JoinColumn(name = "USERID")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "SKILLSTACKID")
//    private SkillStack skillStack;
//}
