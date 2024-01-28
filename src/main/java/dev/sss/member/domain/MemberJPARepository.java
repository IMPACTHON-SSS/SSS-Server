package dev.sss.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJPARepository extends JpaRepository<Member, Long> {

    default Member getById(Long id) {
        return findById(id)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없음"));
    }

    Optional<Member> findByOauth(String oauth);

    default Member getByOauth(String oauth) {
        return findByOauth(oauth)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없음"));
    }

}