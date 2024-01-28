package dev.sss.dish.domain;

import dev.sss.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String dairy;

    @NotNull
    private String url;

    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_member_id", nullable = false)
    private Member member;

    @Builder
    public Dish(String dairy, String url, Member member) {
        this.dairy = dairy;
        this.url = url;
        this.date = LocalDate.now();
        this.member = member;
    }

    public void modify(Member member, String dairy, String url) {
        isUploader(member);

        this.dairy = dairy==null ? this.dairy : dairy;
        this.url = url==null ? this.url : url;
    }

    private void isUploader(Member member) {
        if(!Objects.equals(this.member.getId(), member.getId())) {
            throw new IllegalArgumentException();
        }
    }

}