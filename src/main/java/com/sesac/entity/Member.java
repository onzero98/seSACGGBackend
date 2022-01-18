package com.sesac.entity;

import javax.persistence.*;

import com.sesac.dto.MemberDto;
import com.sesac.status.Role;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "members")
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, length = 50)
    private String nickName;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 멤버 테이블에 데이터 입력 받아 생성
    public static Member createMember(MemberDto memberDto) {
        memberDto.setUid(UUID.randomUUID().toString());

        Member member = new Member();
        member.setNickName(memberDto.getNickName());
        member.setEmail(memberDto.getEmail());
        member.setPassword(memberDto.getPassword());
        member.setUserId(memberDto.getUserId());
        member.setRole(Role.USER);
        return member;
    }
}
