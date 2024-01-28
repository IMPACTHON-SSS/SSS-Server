package dev.sss.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {

    MEMBER("ROLE_MEMBER");

    private final String key;

}