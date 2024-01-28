package dev.sss.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String oauth;
    private MemberRole role;

    @Builder
    public Member(String oauth) {
        this.oauth = oauth;
        this.role = MemberRole.MEMBER;
    }

}