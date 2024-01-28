package dev.sss.dish.application.dto;

import dev.sss.dish.domain.Dish;
import dev.sss.member.domain.Member;

import java.time.LocalDate;

public record DishResponse(
        Long id,
        String dairy,
        String url,
        LocalDate date,
        Member member
) {
    public static DishResponse of(Dish dish) {
        return new DishResponse(
                dish.getId(),
                dish.getDairy(),
                dish.getUrl(),
                dish.getDate(),
                dish.getMember()
        );
    }
}