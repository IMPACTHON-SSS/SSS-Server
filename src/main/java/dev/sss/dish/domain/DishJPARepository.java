package dev.sss.dish.domain;

import dev.sss.member.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DishJPARepository extends JpaRepository<Dish, Long> {

    default Dish getById(Long id) {
        return findById(id)
                .orElseThrow(() -> new RuntimeException("음식일기를 찾을 수 없음"));
    }

    boolean existsByMemberAndDate(Member member, LocalDate date);

    @EntityGraph(attributePaths = {"member"})
    List<Dish> findByMemberAndDateGreaterThanEqualAndDateLessThanEqual(Member member, LocalDate minDate, LocalDate maxDate);

}